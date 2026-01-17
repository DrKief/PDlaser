<script setup lang="ts">
  import { ref, onMounted, computed } from 'vue';
  import http from '../http-api';

  interface Image {
    id: number;
    name: string;
  }

  const images = ref<Image[]>([]);
  const selectedImageId = ref<number | null>(null);

  onMounted(async () => {
    try {
      const response = await http.get('/images');
      images.value = response.data;
    } catch (error) {
      console.error(error);
    }
  });

  const getImageUrl = (id: number) => {
    return `/images/${id}`;
  };

  const selectedImageName = computed(() => {
    const img = images.value.find(i => i.id === selectedImageId.value);
    return img ? img.name : '';
  });

  const onSelectChange = async () => {
    if (selectedImageId.value !== null) {
      try {
        await http.get(`/images/${selectedImageId.value}`);
        console.log(`Requested image ${selectedImageId.value}`);
      } catch (e) {
        console.error(e);
      }
    }
  };
</script>

<template>
  <div>
    <h2>Available images:</h2>
    <div class="selection-container">
      <select v-model="selectedImageId" @change="onSelectChange">
        <option disabled :value="null">Please select an image</option>
        <option v-for="image in images" :key="image.id" :value="image.id">
          {{ image.name }}
        </option>
      </select>
    </div>

    <div v-if="selectedImageId !== null" class="image-display">
      <h3>Selected: {{ selectedImageName }}</h3>
      <img :src="getImageUrl(selectedImageId)" :alt="selectedImageName" />
    </div>
  </div>
</template>

<style scoped>
.selection-container {
  margin: 20px 0;
  border: 1px solid #555;
  padding: 8px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  width: fit-content;
}

select {
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  background-color: transparent;
  color: #e0e0e0;
  border: 1px solid #555;
  padding: 8px 36px 8px 16px;
  border-radius: 4px;
  font-family: inherit;
  font-size: 0.9rem;
  cursor: pointer;
  transition: background 0.2s;
  
  /* Custom Arrow */
  background-image: url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='%23e0e0e0' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 16px;
  min-width: 250px;
}

select:hover {
  background-color: #333;
}

select:focus {
  outline: none;
  border-color: #33a06f;
}

option {
  background-color: #222;
  color: #fff;
}

.image-display img {
  max-width: 100%;
  max-height: 500px;
  border: 1px solid #555;
  border-radius: 4px;
  margin-top: 10px;
}
</style>
