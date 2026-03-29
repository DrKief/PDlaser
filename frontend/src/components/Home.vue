<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import http from "../http-api";
import { useImageStatus } from "../composables/useImageStatus";

interface Image {
  id: number;
  name: string;
}

const images = ref<Image[]>([]);
const selectedImageId = ref<number | null>(null);
const { statusCache, fetchStatus, pollStatus } = useImageStatus();

onMounted(async () => {
  try {
    const response = await http.get("/images");
    images.value = response.data;
  } catch (error) {
    console.error(error);
  }
});

const getImageUrl = (id: number) => {
  return `/images/${id}`;
};

const selectedImageName = computed(() => {
  const img = images.value.find((i) => i.id === selectedImageId.value);
  return img ? img.name : "";
});

const onSelectChange = async () => {
  if (selectedImageId.value !== null) {
    try {
      await http.get(`/images/${selectedImageId.value}`);
      console.log(`Requested image ${selectedImageId.value}`);

      const id = selectedImageId.value;
      fetchStatus(id).then((status) => {
        if (status && status !== "COMPLETE" && status !== "FAILED") {
          pollStatus(id);
        }
      });
    } catch (e) {
      console.error(e);
    }
  }
};
</script>

<template>
  <div class="home-container">
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
      <div
        v-if="selectedImageId !== null && statusCache?.[selectedImageId]"
        :class="['status-badge', statusCache[selectedImageId]!.toLowerCase()]"
      >
        Status: {{ statusCache[selectedImageId] }}
      </div>
      <img :src="getImageUrl(selectedImageId)" :alt="selectedImageName" />
    </div>
  </div>
</template>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.selection-container {
  margin: 20px 0;
  border: 1px solid var(--border-color);
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
  color: var(--text-primary);
  border: 1px solid var(--border-color);
  padding: 8px 36px 8px 16px;
  border-radius: 4px;
  font-family: inherit;
  font-size: 0.9rem;
  cursor: pointer;
  transition:
    background 0.2s,
    border-color 0.2s,
    color 0.2s;

  /* Use variable for arrow to switch between light/dark versions */
  background-image: var(--arrow-icon);
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 16px;
  min-width: 250px;
}

select:hover {
  background-color: var(--bg-tertiary);
}

select:focus {
  outline: none;
  border-color: var(--color-primary);
}

option {
  background-color: var(--bg-secondary);
  color: var(--text-primary);
}

.image-display {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
  width: 100%;
}

.image-display img {
  max-width: 100%;
  max-height: 500px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  margin-top: 10px;
}

.status-badge {
  font-size: 0.75rem;
  padding: 4px 8px;
  border-radius: 12px;
  margin-top: 8px;
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
</style>
