<script setup lang="ts">
import { ref, watch, computed } from "vue";
import http from "../api/http-client";

const props = defineProps<{
  initialSourceId?: number | null;
  initialSourceIds?: number[];
}>();
const emit = defineEmits(["close", "executeSimilarity", "sourceSelected"]);

const MAX_FILES = 5;

const uploadMode = ref<"db" | "ephemeral">("db");
const selectedDbIds = ref<number[]>(
  props.initialSourceIds && props.initialSourceIds.length > 0
    ? [...props.initialSourceIds]
    : props.initialSourceId
      ? [props.initialSourceId]
      : [],
);
const newDbIdInput = ref<number | null>(null);
const uploadTargets = ref<File[]>([]);
const similarityAlgorithm = ref("semantic");
const similarityCount = ref(10);
const errorMsg = ref("");

const createObjectUrl = (file: any) => URL.createObjectURL(file);

watch(
  () => props.initialSourceId,
  (newId) => {
    if (newId) {
      if (!selectedDbIds.value.includes(newId)) {
        selectedDbIds.value = [newId];
      }
      uploadMode.value = "db";
    }
  },
);

watch(
  () => props.initialSourceIds,
  (newIds) => {
    if (newIds && newIds.length > 0) {
      selectedDbIds.value = [...newIds];
      uploadMode.value = "db";
    }
  },
);

const isSourceSelected = computed(() => {
  if (uploadMode.value === "db") return selectedDbIds.value.length > 0;
  return uploadTargets.value.length > 0;
});

const addDbId = () => {
  if (newDbIdInput.value === null) return;
  if (selectedDbIds.value.length >= MAX_FILES) {
    errorMsg.value = `Maximum ${MAX_FILES} images allowed`;
    return;
  }
  if (selectedDbIds.value.includes(newDbIdInput.value)) {
    errorMsg.value = "Image ID already added";
    return;
  }
  selectedDbIds.value = [...selectedDbIds.value, newDbIdInput.value];
  newDbIdInput.value = null;
  errorMsg.value = "";
};

const removeDbId = (index: number) => {
  selectedDbIds.value = selectedDbIds.value.filter((_, i) => i !== index);
};

const clearDbIds = () => {
  selectedDbIds.value = [];
  errorMsg.value = "";
};

const onFileSelect = (e: Event) => {
  const target = e.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    const newFiles = Array.from(target.files);
    const totalFiles = uploadTargets.value.length + newFiles.length;

    if (totalFiles > MAX_FILES) {
      errorMsg.value = `Maximum ${MAX_FILES} images allowed`;
      return;
    }

    uploadTargets.value = [...uploadTargets.value, ...newFiles];
    if (uploadTargets.value.length > 0) {
      const fileUrl = createObjectUrl(uploadTargets.value[0]);
      emit("sourceSelected", { fileUrl, isEphemeral: true });
    }
    errorMsg.value = "";
  }
};

const removeFile = (index: number) => {
  uploadTargets.value = uploadTargets.value.filter((_, i) => i !== index);
  if (uploadTargets.value.length === 0) {
    emit("sourceSelected", { fileUrl: null, isEphemeral: true });
  } else {
    const fileUrl = createObjectUrl(uploadTargets.value[0]);
    emit("sourceSelected", { fileUrl, isEphemeral: true });
  }
};

const clearFiles = () => {
  uploadTargets.value = [];
  errorMsg.value = "";
  emit("sourceSelected", { fileUrl: null, isEphemeral: true });
};

watch(
  () => selectedDbIds.value,
  (newIds) => {
    if (newIds.length > 0) {
      emit("sourceSelected", { sourceId: newIds[0], isEphemeral: false });
    }
  },
);

const triggerSimilaritySearch = async () => {
  errorMsg.value = "";

  try {
    let results = [];
    if (uploadMode.value === "db") {
      if (selectedDbIds.value.length === 0) {
        return (errorMsg.value = "Please add at least one image ID.");
      }

      if (selectedDbIds.value.length === 1) {
        const res = await http.get(`/images/${selectedDbIds.value[0]}/similar`, {
          params: { number: similarityCount.value, descriptor: similarityAlgorithm.value },
        });
        results = res.data;
      } else {
        const res = await http.post(
          `/images/search/batch?number=${similarityCount.value}&descriptor=${similarityAlgorithm.value}`,
          selectedDbIds.value,
          { headers: { "Content-Type": "application/json" } },
        );
        results = res.data;
      }
    } else {
      if (uploadTargets.value.length === 0)
        return (errorMsg.value = "Please select at least one image to upload.");
      const formData = new FormData();
      uploadTargets.value.forEach((file) => {
        formData.append("files", file);
      });
      formData.append("number", similarityCount.value.toString());
      formData.append("descriptor", similarityAlgorithm.value);

      const res = await http.post("/images/search/ephemeral", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      results = res.data;
    }

    const fileUrl = uploadTargets.value.length > 0 ? createObjectUrl(uploadTargets.value[0]) : null;
    emit("executeSimilarity", {
      results: results,
      sourceId:
        uploadMode.value === "db" && selectedDbIds.value.length > 0 ? selectedDbIds.value[0] : null,
      isEphemeral: uploadMode.value === "ephemeral",
      fileUrl: fileUrl,
    });

    emit("close");
  } catch (e) {
    errorMsg.value = "Similarity scan failed.";
  }
};
</script>

<template>
  <aside class="search-sidebar">
    <div class="sidebar-header">
      <h3>Visual Match</h3>
      <button
        @click="$emit('close')"
        class="btn-icon material-symbols-outlined"
        title="Close Filters"
      >
        close
      </button>
    </div>

    <div class="sidebar-content">
      <p v-if="!isSourceSelected" class="help-text warning-text">
        <span class="material-symbols-outlined" style="font-size: 1.2rem">info</span>
        Select a gallery image or upload 1-5 images to enable similarity settings.
      </p>

      <div class="form-group mb-4">
        <label class="label-text">Source Target</label>
        <select v-model="uploadMode" style="margin-top: 0.5rem">
          <option value="db">From Database (ID)</option>
          <option value="ephemeral">Upload Temporary Image</option>
        </select>
      </div>

      <div class="form-group" v-if="uploadMode === 'db'">
        <label class="label-text">Image IDs (1-{{ MAX_FILES }})</label>
        <div class="db-id-input-row">
          <input
            type="number"
            v-model="newDbIdInput"
            placeholder="e.g., 15"
            @keydown.enter="addDbId"
          />
          <button type="button" @click="addDbId" class="btn-add">Add</button>
        </div>
        <div v-if="selectedDbIds.length > 0" class="db-id-list">
          <div v-for="(id, index) in selectedDbIds" :key="id" class="db-id-item">
            <span class="db-id-badge">ID: {{ id }}</span>
            <button type="button" @click="removeDbId(index)" class="btn-remove" title="Remove">
              <span class="material-symbols-outlined">close</span>
            </button>
          </div>
          <button type="button" @click="clearDbIds" class="btn-clear">Clear All</button>
        </div>
      </div>

      <div class="form-group" v-if="uploadMode === 'ephemeral'">
        <label class="label-text">Upload Images (1-{{ MAX_FILES }})</label>
        <input
          type="file"
          @change="onFileSelect"
          accept="image/*"
          multiple
          style="margin-top: 0.5rem; padding-bottom: 0"
        />
        <div v-if="uploadTargets.length > 0" class="file-previews">
          <div v-for="(file, index) in uploadTargets" :key="index" class="file-preview-item">
            <img :src="createObjectUrl(file)" :alt="file.name" class="preview-thumbnail" />
            <span class="preview-name">{{ file.name }}</span>
            <button type="button" @click="removeFile(index)" class="btn-remove" title="Remove">
              <span class="material-symbols-outlined">close</span>
            </button>
          </div>
          <button type="button" @click="clearFiles" class="btn-clear">Clear All</button>
        </div>
      </div>

      <fieldset :disabled="!isSourceSelected" style="border: none; padding: 0; margin: 0">
        <div class="form-group mt-4" :class="{ 'disabled-group': !isSourceSelected }">
          <label class="label-text">Algorithm</label>
          <select v-model="similarityAlgorithm" style="margin-top: 0.5rem">
            <option value="semantic">Semantic (AI)</option>
            <option value="cielab">CIELAB (Human Vision)</option>
            <option value="gradient">HOG (Shape/Edges)</option>
          </select>
        </div>

        <div class="form-group mt-4" :class="{ 'disabled-group': !isSourceSelected }">
          <label class="label-text">Result Limit</label>
          <input type="number" v-model.number="similarityCount" min="1" max="50" />
        </div>

        <button
          class="btn w-full mt-4"
          @click="triggerSimilaritySearch"
          :disabled="!isSourceSelected"
        >
          Find Matches
        </button>
      </fieldset>

      <p v-if="errorMsg" class="error-text">{{ errorMsg }}</p>
    </div>
  </aside>
</template>

<style scoped>
.search-sidebar {
  position: fixed;
  inset: 0;
  width: 100vw;
  height: 100vh;
  z-index: 100;
  background: var(--bg-surface);
  padding: max(1.5rem, env(safe-area-inset-top)) max(1.5rem, env(safe-area-inset-right))
    max(1.5rem, env(safe-area-inset-bottom)) max(1.5rem, env(safe-area-inset-left));
  overflow-y: auto;
  box-shadow: var(--shadow-subtle);
  border: none;
  border-radius: 0;
}

@media (min-width: 768px) {
  .search-sidebar {
    position: sticky;
    top: 120px;
    width: 320px;
    height: calc(100vh - 140px);
    border: 1px solid var(--border-subtle);
    border-radius: 8px;
    z-index: auto;
    padding: 1.5rem;
  }
}
.sidebar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1rem;
}
.sidebar-header h3 {
  margin: 0;
  font-size: 1.25rem;
  font-family: var(--font-headline);
}
.mb-4 {
  margin-bottom: 1rem;
}
.mt-4 {
  margin-top: 1rem;
}
.w-full {
  width: 100%;
}

.btn-icon {
  background: none;
  border: none;
  cursor: pointer;
  color: var(--text-secondary);
  padding: 0;
}
.btn-icon:hover {
  color: var(--color-danger);
}

.disabled-group {
  opacity: 0.5;
  pointer-events: none;
  transition: opacity 0.2s;
}
.help-text {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.85rem;
  padding: 0.75rem;
  border-radius: 4px;
  background: var(--bg-element);
  color: var(--text-secondary);
  margin-bottom: 1rem;
}
.warning-text {
  border-left: 3px solid var(--color-accent);
}
.file-previews {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-top: 0.75rem;
}
.file-preview-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.375rem;
  background: var(--bg-element);
  border-radius: 4px;
  border: 1px solid var(--border-subtle);
}
.preview-thumbnail {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 4px;
}
.preview-name {
  flex: 1;
  font-size: 0.75rem;
  color: var(--text-secondary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 120px;
}
.btn-remove {
  background: none;
  border: none;
  padding: 0.25rem;
  cursor: pointer;
  color: var(--text-secondary);
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 4px;
}
.btn-remove:hover {
  background: var(--bg-surface);
  color: var(--color-danger);
}
.btn-remove .material-symbols-outlined {
  font-size: 1.25rem;
}
.btn-clear {
  align-self: flex-start;
  font-size: 0.75rem;
  color: var(--color-danger);
  background: none;
  border: none;
  cursor: pointer;
  padding: 0.25rem 0;
  text-decoration: underline;
}
.btn-clear:hover {
  color: var(--color-danger-hover);
}
.db-id-input-row {
  display: flex;
  gap: 0.5rem;
  margin-top: 0.5rem;
}
.db-id-input-row input {
  flex: 1;
}
.btn-add {
  padding: 0.5rem 1rem;
  background: var(--bg-element);
  border: 1px solid var(--border-subtle);
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.875rem;
  color: var(--text-primary);
}
.btn-add:hover {
  background: var(--bg-surface);
}
.db-id-list {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-top: 0.75rem;
}
.db-id-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0.5rem;
  background: var(--bg-element);
  border-radius: 4px;
  border: 1px solid var(--border-subtle);
}
.db-id-badge {
  font-size: 0.875rem;
  font-weight: 500;
  color: var(--text-primary);
}
</style>
