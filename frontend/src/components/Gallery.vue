<script setup lang="ts">
import { ref, onMounted } from 'vue';
import http from '../http-api';

interface Image {
  id: number;
  name: string;
}

const images = ref<Image[]>([]);

onMounted(async () => {
  fetchImages();
});

const fetchImages = async () => {
  try {
    const response = await http.get('/images');
    images.value = response.data;
  } catch (error) {
    console.error("Error fetching images for gallery:", error);
  }
};

const getImageUrl = (image: Image) => {
  return `/images/${image.id}`;
};

const deleteImage = async (id: number) => {
  if (!confirm("Are you sure you want to permanently delete this file?")) {
    return;
  }
  
  try {
    await http.delete(`/images/${id}`);
    // Remove from the local array so the UI updates instantly
    images.value = images.value.filter(img => img.id !== id);
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
      <div 
        v-for="image in images" 
        :key="image.id" 
        class="image-card"
      >
        <img :src="getImageUrl(image)" :alt="image.name" />
        <p class="image-name">{{ image.name }}</p>
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
  padding: 12px;
  border-radius: 6px;
  text-align: center;
  background: var(--bg-secondary);
  display: flex;
  flex-direction: column;
  align-items: center;
  transition: background-color 0.3s, border-color 0.3s;
}

.image-card img {
  max-width: 200px;
  max-height: 200px;
  object-fit: contain;
  display: block;
  margin-bottom: 10px;
}

.image-name {
  margin: 0 0 10px 0;
  font-size: 0.9em;
  color: var(--text-secondary);
  max-width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
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