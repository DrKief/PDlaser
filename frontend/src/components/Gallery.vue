<script setup lang="ts">
import { ref, onMounted } from "vue";
import http from "../http-api";
import { useImageStatus } from "../composables/useImageStatus";

interface Image {
  id: number;
  name: string;
  keywords: string[];
}

const images = ref<Image[]>([]);
const keywordInputs = ref<Record<number, string>>({});
const { statusCache, fetchStatus, pollStatus } = useImageStatus();
const isLoading = ref(true);

onMounted(async () => {
  await fetchImages();
});

const fetchImages = async () => {
  try {
    const response = await http.get("/images");
    images.value = response.data.map((img: any) => ({
      ...img,
      keywords: img.keywords || [],
    }));

    images.value.forEach((img) => {
      if (!statusCache[img.id] || statusCache[img.id] === "PENDING") {
        fetchStatus(img.id).then((status) => {
          if (status && status !== "COMPLETE" && status !== "FAILED") {
            pollStatus(img.id);
          }
        });
      }
    });
  } catch (error) {
    console.error("Error fetching images for gallery:", error);
  } finally {
    isLoading.value = false;
  }
};

const getImageUrl = (image: Image) => `/images/${image.id}`;

const addKeyword = async (image: Image) => {
  const tag = keywordInputs.value[image.id];
  if (!tag || !tag.trim()) return;

  try {
    await http.put(`/images/${image.id}/keywords`, null, {
      params: { tag: tag.trim() },
    });
    if (!image.keywords.includes(tag.trim())) {
      image.keywords.push(tag.trim());
    }
    keywordInputs.value[image.id] = "";
  } catch (error) {
    console.error(`Error adding keyword to image ${image.id}:`, error);
    alert("Failed to assign keyword to record.");
  }
};

const removeKeyword = async (image: Image, tag: string) => {
  try {
    await http.delete(`/images/${image.id}/keywords`, {
      params: { tag },
    });
    image.keywords = image.keywords.filter((t: string) => t !== tag);
  } catch (error) {
    console.error(`Error removing keyword from image ${image.id}:`, error);
    alert("Failed to remove keyword.");
  }
};

const deleteImage = async (id: number) => {
  if (!confirm("WARNING: Purge operation is irreversible. Proceed?")) return;

  try {
    await http.delete(`/images/${id}`);
    images.value = images.value.filter((img) => img.id !== id);
  } catch (error) {
    console.error(`Error deleting image ${id}:`, error);
    alert("Purge operation failed.");
  }
};
</script>

<template>
  <div class="view-header">
    <h2>Data Archive</h2>
    <p class="view-description">Stored visual records and associated metadata tags.</p>
  </div>

  <div v-if="isLoading" class="empty-state">Accessing storage volume...</div>
  <div v-else-if="images.length === 0" class="empty-state">
    Archive is empty. Run INGEST protocol to populate.
  </div>

  <div v-else class="gallery-grid">
    <article v-for="image in images" :key="image.id" class="record-card panel">
      
      <div class="card-header">
        <h3 class="record-name" :title="image.name">{{ image.name }}</h3>
        <span class="record-id">#{{ String(image.id).padStart(4, '0') }}</span>
      </div>

      <div class="image-wrapper">
        <img :src="getImageUrl(image)" :alt="image.name" loading="lazy" />
        <div
          v-if="statusCache?.[image.id]"
          :class="['status-badge', 'overlay-badge', statusCache[image.id]!.toLowerCase()]"
        >
          {{ statusCache[image.id] }}
        </div>
      </div>

      <div class="metadata-section">
        <div class="tags-container" v-if="image.keywords.length > 0">
          <span v-for="tag in image.keywords" :key="tag" class="tech-tag">
            {{ tag }}
            <button class="tag-remove" @click="removeKeyword(image, tag)" :aria-label="`Remove tag ${tag}`">
              <svg width="10" height="10" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" stroke-linecap="square"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
            </button>
          </span>
        </div>
        
        <div class="tag-input-group">
          <input
            v-model="keywordInputs[image.id]"
            @keyup.enter="addKeyword(image)"
            placeholder="Assign new tag..."
            aria-label="New keyword"
          />
          <button class="btn btn-primary btn-icon" @click="addKeyword(image)" aria-label="Add tag">
            +
          </button>
        </div>
      </div>

      <div class="card-footer">
        <button class="btn btn-danger full-width" @click="deleteImage(image.id)">
          Purge Record
        </button>
      </div>

    </article>
  </div>
</template>

<style scoped>
.view-header {
  margin-bottom: var(--space-xl);
}

.view-description {
  color: var(--text-secondary);
  font-family: var(--font-mono);
  font-size: 0.875rem;
  margin-top: -0.5rem;
}

.gallery-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: var(--space-lg);
}

.record-card {
  display: flex;
  flex-direction: column;
  padding: 0;
  overflow: hidden;
  transition: transform 0.2s var(--ease-out-expo), box-shadow 0.2s var(--ease-out-expo);
}

.record-card:hover {
  transform: translateY(-2px);
  border-color: var(--color-primary);
  box-shadow: 4px 4px 0 var(--bg-tertiary);
}

.card-header {
  padding: var(--space-md);
  border-bottom: 1px solid var(--border-color);
  display: flex;
  justify-content: space-between;
  align-items: baseline;
  gap: var(--space-sm);
  background: var(--bg-primary);
}

.record-name {
  margin: 0;
  font-family: var(--font-mono);
  font-size: 0.875rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.record-id {
  font-family: var(--font-mono);
  font-size: 0.75rem;
  color: var(--text-muted);
}

.image-wrapper {
  position: relative;
  background: var(--bg-primary);
  border-bottom: 1px solid var(--border-color);
  aspect-ratio: 4/3;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: var(--space-sm);
}

.image-wrapper img {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

.overlay-badge {
  position: absolute;
  top: var(--space-sm);
  right: var(--space-sm);
  background: var(--bg-primary) !important; /* solid background for readability over image */
}

.metadata-section {
  padding: var(--space-md);
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: var(--space-md);
}

.tags-container {
  display: flex;
  flex-wrap: wrap;
  gap: var(--space-xs);
}

.tech-tag {
  display: inline-flex;
  align-items: center;
  gap: 4px;
  background: var(--bg-primary);
  color: var(--text-secondary);
  border: 1px solid var(--border-color);
  padding: 2px 6px 2px 8px;
  font-family: var(--font-mono);
  font-size: 0.75rem;
  text-transform: uppercase;
  border-radius: var(--radius-sm);
}

.tag-remove {
  background: none;
  border: none;
  color: var(--text-muted);
  padding: 2px;
  display: flex;
  align-items: center;
  cursor: pointer;
  box-shadow: none;
}

.tag-remove:hover {
  color: var(--color-danger);
  transform: scale(1.1);
  box-shadow: none;
}

.tag-input-group {
  display: flex;
  gap: var(--space-xs);
}

.btn-icon {
  padding: 0 1rem;
  font-size: 1.2rem;
}

.card-footer {
  padding: var(--space-md);
  padding-top: 0;
}

.full-width {
  width: 100%;
}
</style>