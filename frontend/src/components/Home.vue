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
    <h2>System Inspector</h2>
    <p class="view-description">Select an ingested record to view current status and optics.</p>
  </div>

  <div v-if="isLoading" class="empty-state">Initializing datalink...</div>
  <div v-else-if="images.length === 0" class="empty-state">
    No records available. Navigate to INGEST to process a new image.
  </div>
  
  <div v-else class="inspector-layout">
    <div class="control-panel panel">
      <label for="image-selector">Target Record</label>
      <select id="image-selector" v-model="selectedImageId" @change="onSelectChange">
        <option disabled :value="null">-- SELECT RECORD --</option>
        <option v-for="image in images" :key="image.id" :value="image.id">
          ID:{{ String(image.id).padStart(4, '0') }} - {{ image.name }}
        </option>
      </select>
    </div>

    <div v-if="selectedImageId !== null" class="display-panel panel">
      <div class="display-header">
        <div class="record-info">
          <h3>{{ selectedImageName }}</h3>
          <span class="record-id">REC_{{ String(selectedImageId).padStart(4, '0') }}</span>
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
  font-family: var(--font-mono);
  font-size: 0.875rem;
  margin-top: -0.5rem;
}

.inspector-layout {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
}

.control-panel {
  max-width: 400px;
}

.display-panel {
  display: flex;
  flex-direction: column;
  gap: var(--space-md);
}

.display-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  border-bottom: 1px solid var(--border-color);
  padding-bottom: var(--space-sm);
}

.record-info h3 {
  margin: 0 0 4px 0;
  font-family: var(--font-mono);
  font-size: 1.1rem;
}

.record-id {
  font-family: var(--font-mono);
  font-size: 0.75rem;
  color: var(--text-muted);
}

.image-frame {
  background: var(--bg-primary);
  border: 1px solid var(--border-color);
  padding: var(--space-sm);
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 300px;
}

.image-frame img {
  max-width: 100%;
  max-height: 60vh;
  object-fit: contain;
}

select {
  background-image: var(--arrow-icon);
  background-repeat: no-repeat;
  background-position: right 12px center;
  background-size: 16px;
  appearance: none;
}
</style>