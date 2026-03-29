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

onMounted(async () => {
  fetchImages();
});

const fetchImages = async () => {
  try {
    const response = await http.get("/images");
    images.value = response.data.map((img: any) => ({
      ...img,
      keywords: img.keywords || [], // ensure array
    }));

    // Check status for each image
    images.value.forEach(img => {
      if (!statusCache[img.id] || statusCache[img.id] === 'PENDING') {
        fetchStatus(img.id).then(status => {
          if (status && status !== 'COMPLETE' && status !== 'FAILED') {
            pollStatus(img.id);
          }
        });
      }
    });
  } catch (error) {
    console.error("Error fetching images for gallery:", error);
  }
};

const getImageUrl = (image: Image) => {
  return `/images/${image.id}`;
};

const addKeyword = async (image: Image) => {
  const tag = keywordInputs.value[image.id];
  if (!tag || !tag.trim()) return;

  try {
    await http.put(`/images/${image.id}/keywords`, null, {
      params: { tag: tag.trim() },
    });
    // Update local state
    if (!image.keywords.includes(tag.trim())) {
      image.keywords.push(tag.trim());
    }
    keywordInputs.value[image.id] = ""; // Clear input
  } catch (error) {
    console.error(`Error adding keyword to image ${image.id}:`, error);
    alert("Failed to add keyword.");
  }
};

const deleteImage = async (id: number) => {
  if (!confirm("Are you sure you want to permanently delete this file?")) {
    return;
  }

  try {
    await http.delete(`/images/${id}`);
    // Remove from the local array so the UI updates instantly
    images.value = images.value.filter((img) => img.id !== id);
  } catch (error) {
    console.error(`Error deleting image ${id}:`, error);
    alert("Failed to delete file.");
  }
};
</script>

<template>
  <div>
    <h2>Gallery</h2>
    <div class="gallery-container">
      <div v-for="image in images" :key="image.id" class="image-card">
        <img :src="getImageUrl(image)" :alt="image.name" />
        <p class="image-name">{{ image.name }}</p>

        <div v-if="statusCache?.[image.id]" :class="['status-badge', statusCache[image.id]!.toLowerCase()]">
          Status: {{ statusCache[image.id] }}
        </div>

        <div class="keywords-section">
          <div class="tags">
            <span v-for="tag in image.keywords" :key="tag" class="tag">{{ tag }}</span>
          </div>
          <div class="add-keyword">
            <input
              v-model="keywordInputs[image.id]"
              @keyup.enter="addKeyword(image)"
              placeholder="Add tag"
              class="keyword-input"
            />
            <button class="add-btn" @click="addKeyword(image)">+</button>
          </div>
        </div>

        <button class="delete-btn" @click="deleteImage(image.id)">Delete</button>
      </div>
    </div>
    <p v-if="images.length === 0">No data found.</p>
  </div>
</template>

<style scoped>
.gallery-container {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  justify-content: center;
}

.image-card {
  border: 1px solid var(--border-color);
  padding: 16px;
  border-radius: var(--radius-lg, 12px);
  text-align: center;
  background: var(--bg-secondary);
  display: flex;
  flex-direction: column;
  align-items: center;
  transition:
    transform 0.2s,
    box-shadow 0.2s;
  width: 260px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.image-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
}

.image-card img {
  max-width: 100%;
  max-height: 200px;
  object-fit: contain;
  display: block;
  margin-bottom: 12px;
  border-radius: var(--radius-sm, 4px);
  background-color: var(--bg-primary); /* visible if transparent png */
}

.keywords-section {
  width: 100%;
  margin-bottom: 12px;
}

.tags {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
  justify-content: center;
  margin-bottom: 10px;
}

.tag {
  background: var(--bg-tertiary);
  color: var(--text-secondary);
  font-size: 0.75em;
  padding: 3px 8px;
  border-radius: 12px; /* Pill shape */
  font-weight: 500;
  border: 1px solid var(--border-color);
}

.add-keyword {
  display: flex;
  gap: 6px;
  justify-content: center;
}

.keyword-input {
  width: 60%;
  padding: 6px 10px;
  border: 1px solid var(--border-color);
  border-radius: var(--radius-sm, 6px);
  background: var(--bg-primary);
  color: var(--text-primary);
  font-size: 0.85em;
}

.add-btn {
  padding: 6px 10px;
  background: var(--color-primary);
  color: var(--text-on-primary);
  border: none;
  border-radius: var(--radius-sm, 6px);
  cursor: pointer;
  font-size: 0.9em;
  line-height: 1;
}

.image-name {
  margin: 0 0 12px 0;
  font-weight: 600;
  color: var(--text-primary);

  max-width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.status-badge {
  font-size: 0.75rem;
  padding: 4px 8px;
  border-radius: 12px;
  margin-bottom: 12px;
  font-weight: bold;
  text-transform: uppercase;
  background-color: var(--bg-tertiary);
  color: var(--text-secondary);
}

.status-badge.complete {
  background-color: #e6f4ea;
  color: #2e7d32;
}

.status-badge.pending {
  background-color: #fff3e0;
  color: #f57c00;
}

.status-badge.failed {
  background-color: #ffebee;
  color: #c62828;
}

.delete-btn {
  background-color: var(--color-danger);
  color: var(--text-on-primary);
  border: none;
  padding: 6px 12px;
  border-radius: 4px;
  cursor: pointer;
  width: 100%;
  transition: background-color 0.2s;
}

.delete-btn:hover {
  background-color: var(--color-danger-hover);
}
</style>
