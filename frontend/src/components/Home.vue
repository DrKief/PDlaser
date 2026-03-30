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
const isLoading = ref(true);

onMounted(async () => {
  try {
    const response = await http.get("/images");
    images.value = response.data;
  } catch (error) {
    console.error(error);
  } finally {
    isLoading.value = false;
  }
});

const getImageUrl = (id: number) => `/images/${id}`;

const selectedImageName = computed(() => {
  const img = images.value.find((i) => i.id === selectedImageId.value);
  return img ? img.name : "";
});

const onSelectChange = async () => {
  if (selectedImageId.value !== null) {
    try {
      await http.get(`/images/${selectedImageId.value}`);
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
  <div class="view-header">
    <h2>Overview</h2>
    <p class="view-description">Select an image to inspect its status and details.</p>
  </div>

  <div v-if="isLoading" class="empty-state">Loading database...</div>
  <div v-else-if="images.length === 0" class="empty-state">
    The database is empty. Proceed to Upload to add images.
  </div>

  <div v-else class="inspector-layout">
    <div class="control-panel">
      <label for="image-selector">Selected Image</label>
      <div class="select-wrapper">
        <select id="image-selector" v-model="selectedImageId" @change="onSelectChange">
          <option disabled :value="null">Choose an image...</option>
          <option v-for="image in images" :key="image.id" :value="image.id">
            {{ image.name }}
          </option>
        </select>
      </div>
    </div>

    <div v-if="selectedImageId !== null" class="display-panel panel">
      <div class="display-header">
        <div class="record-info">
          <h3>{{ selectedImageName }}</h3>
          <span class="record-id">ID: {{ selectedImageId }}</span>
        </div>

        <div
          v-if="statusCache?.[selectedImageId]"
          :class="['status-badge', statusCache[selectedImageId]!.toLowerCase()]"
        >
          {{ statusCache[selectedImageId] }}
        </div>
      </div>

      <div class="image-frame">
        <img :src="getImageUrl(selectedImageId)" :alt="selectedImageName" loading="lazy" />
      </div>
    </div>
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

.inspector-layout {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
}

.control-panel {
  max-width: 400px;
}

.select-wrapper {
  position: relative;
}

select {
  background-image: var(--arrow-icon);
  background-repeat: no-repeat;
  background-position: right 12px center;
  background-size: 16px;
  appearance: none;
}

.display-panel {
  display: flex;
  flex-direction: column;
  gap: var(--space-md);
  padding: var(--space-lg); /* Slightly tighter padding for the display */
}

.display-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  padding-bottom: var(--space-sm);
}

.record-info h3 {
  margin: 0 0 4px 0;
  font-size: 1rem;
}

.record-id {
  font-size: 0.75rem;
  color: var(--text-muted);
}

.image-frame {
  background: var(--bg-element);
  border-radius: var(--radius-sm);
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
  padding: var(--space-md);
  box-shadow: var(--shadow-inset);
}

.image-frame img {
  max-width: 100%;
  max-height: 60vh;
  object-fit: contain;
  border-radius: 4px;
}
</style>
