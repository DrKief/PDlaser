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
    alert("Failed to assign tag.");
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
    alert("Failed to remove tag.");
  }
};

const deleteImage = async (id: number) => {
  if (!confirm("Delete this image? This action cannot be undone.")) return;

  try {
    await http.delete(`/images/${id}`);
    images.value = images.value.filter((img) => img.id !== id);
  } catch (error) {
    console.error(`Error deleting image ${id}:`, error);
    alert("Failed to delete image.");
  }
};
</script>

<template>
  <div class="view-header">
    <h2>Gallery</h2>
    <p class="view-description">Browse and manage all uploaded images and their tags.</p>
  </div>

  <div v-if="isLoading" class="empty-state">Loading images...</div>
  <div v-else-if="images.length === 0" class="empty-state">
    The gallery is empty.
  </div>

  <div v-else class="gallery-grid">
    <article v-for="image in images" :key="image.id" class="image-card panel">
      
      <div class="image-wrapper">
        <img :src="getImageUrl(image)" :alt="image.name" loading="lazy" />
        <div v-if="statusCache?.[image.id]" :class="['status-badge', 'overlay-badge', statusCache[image.id]!.toLowerCase()]">
          {{ statusCache[image.id] }}
        </div>
      </div>

      <div class="card-content">
        <div class="card-header">
          <h3 class="image-name" :title="image.name">{{ image.name }}</h3>
          <button class="btn-icon-delete" @click="deleteImage(image.id)" aria-label="Delete image">
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
          </button>
        </div>

        <div class="tags-container">
          <span v-for="tag in image.keywords" :key="tag" class="tag-pill">
            {{ tag }}
            <button class="tag-remove" @click="removeKeyword(image, tag)" aria-label="Remove tag">
              &times;
            </button>
          </span>
        </div>
        
        <div class="tag-input-group">
          <input v-model="keywordInputs[image.id]" @keyup.enter="addKeyword(image)" placeholder="Add tag..." />
          <button class="btn btn-primary btn-icon" @click="addKeyword(image)" aria-label="Add tag">+</button>
        </div>
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
  font-size: 0.875rem;
}

.gallery-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: var(--space-lg);
}

.image-card {
  display: flex;
  flex-direction: column;
  padding: 0;
  overflow: hidden;
  border-radius: var(--radius-lg);
}

.image-wrapper {
  position: relative;
  background: var(--bg-element);
  aspect-ratio: 4/3;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: var(--space-sm);
  border-bottom: 1px solid var(--border-subtle);
}

.image-wrapper img {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
  border-radius: var(--radius-sm);
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
}

.overlay-badge {
  position: absolute;
  top: var(--space-sm);
  right: var(--space-sm);
  background: var(--bg-surface) !important;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.card-content {
  padding: var(--space-md);
  display: flex;
  flex-direction: column;
  gap: var(--space-md);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: var(--space-sm);
}

.image-name {
  margin: 0;
  font-size: 0.875rem;
  font-weight: 500;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.btn-icon-delete {
  background: none;
  border: none;
  color: var(--text-muted);
  padding: 4px;
  cursor: pointer;
  border-radius: 50%;
  display: flex;
  align-items: center;
}

.btn-icon-delete:hover {
  color: var(--color-danger);
  background: color-mix(in oklch, var(--color-danger) 10%, transparent);
}

.tags-container {
  display: flex;
  flex-wrap: wrap;
  gap: var(--space-xs);
  min-height: 24px; /* Maintain layout if empty */
}

.tag-pill {
  display: inline-flex;
  align-items: center;
  gap: 4px;
  background: var(--bg-element);
  color: var(--text-secondary);
  padding: 2px 8px;
  font-size: 0.75rem;
  border-radius: var(--radius-pill);
}

.tag-remove {
  background: none;
  border: none;
  color: inherit;
  opacity: 0.5;
  padding: 0;
  font-size: 1rem;
  line-height: 1;
  cursor: pointer;
}

.tag-remove:hover {
  opacity: 1;
  color: var(--color-danger);
}

.tag-input-group {
  display: flex;
  gap: var(--space-xs);
}

.tag-input-group input {
  padding: 0.5rem 0.75rem;
}

.btn-icon {
  width: 36px;
  height: 36px;
  padding: 0;
  border-radius: 50%;
  flex-shrink: 0;
}
</style>