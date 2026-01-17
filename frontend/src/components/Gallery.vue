<script setup lang="ts">
import { ref, onMounted } from 'vue';
import http from '../http-api';

interface Image {
  id: number;
  name: string;
}

const images = ref<Image[]>([]);

onMounted(async () => {
  try {
    const response = await http.get('/images');
    images.value = response.data;
  } catch (error) {
    console.error("Error fetching images for gallery:", error);
  }
});

const getImageUrl = (image: Image) => {
  return `/images/${image.id}`;
};

const selectImage = async (image: Image) => {
  try {
    await http.get(`/images/${image.id}`);
    console.log(`Selected and requested image ${image.id}`);
  } catch (error) {
    console.error(`Error requesting image ${image.id}:`, error);
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
        @click="selectImage(image)"
      >
        <img :src="getImageUrl(image)" :alt="image.name" />
        <p>{{ image.name }}</p>
      </div>
    </div>
    <p v-if="images.length === 0">No images found.</p>
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
  border: 1px solid #ddd;
  padding: 8px;
  border-radius: 4px;
  text-align: center;
  cursor: pointer;
  transition: transform 0.2s;
}

.image-card:hover {
  transform: scale(1.05);
  border-color: #33a06f;
}

.image-card img {
  max-width: 200px;
  max-height: 200px;
  display: block;
}

.image-card p {
  margin-top: 8px;
  font-size: 0.9em;
  color: #555;
}
</style>
