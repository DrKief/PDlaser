<script setup lang="ts">
import { ref } from "vue";
import http from "../http-api";
import { useImageStatus } from "../composables/useImageStatus";

const props = defineProps({
  mode: {
    type: String,
    default: "upload",
  },
});

const emit = defineEmits(["file-selected"]);

const selectedFile = ref<File | null>(null);
const previewUrl = ref<string | null>(null);
const keywords = ref("");
const message = ref("");
const lastUploadedId = ref<number | null>(null);
const { statusCache, pollStatus } = useImageStatus();

const onFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    if (previewUrl.value) {
      URL.revokeObjectURL(previewUrl.value);
    }
    selectedFile.value = target.files[0] as File;
    previewUrl.value = URL.createObjectURL(selectedFile.value);

    lastUploadedId.value = null;
    message.value = "";
  }
};

const handleAction = async () => {
  if (!selectedFile.value) {
    message.value = "Please select a file first.";
    return;
  }

  if (props.mode === "search") {
    emit("file-selected", selectedFile.value);
    return;
  }

  const formData = new FormData();
  formData.append("file", selectedFile.value);
  if (keywords.value) {
    formData.append("keywords", keywords.value);
  }

  try {
    message.value = "Uploading...";
    const response = await http.post("/images", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });

    if (response.status === 202) {
      const id = response.data.id;
      message.value = `Upload complete. Processing image...`;
      lastUploadedId.value = id;

      statusCache[id] = "PENDING";
      pollStatus(id);

      resetForm();
    } else if (response.status === 200 || response.status === 201) {
      message.value = "Upload successful.";
      resetForm();
    }
  } catch (error: any) {
    console.error(error);
    if (error.response) {
      const status = error.response.status;
      const data = error.response.data;
      if (status === 413) message.value = "File is too large.";
      else if (status === 415) message.value = "Unsupported media type. Please upload an image.";
      else message.value = data.error || "Upload failed.";
    } else {
      message.value = "Network error. Upload failed.";
    }
  }
};

const resetForm = () => {
  selectedFile.value = null;
  keywords.value = "";
  if (previewUrl.value) {
    URL.revokeObjectURL(previewUrl.value);
    previewUrl.value = null;
  }
  const input = document.getElementById("file-input") as HTMLInputElement;
  if (input) input.value = "";
}
</script>

<template>
  <div class="view-header" v-if="mode === 'upload'">
    <h2>Upload</h2>
    <p class="view-description">Add new images to the database.</p>
  </div>

  <div class="upload-container">
    <div class="form-layout panel">
      
      <!-- Minimalist File Input -->
      <div class="file-drop-zone" :class="{ 'has-file': selectedFile }">
        <label for="file-input" class="file-label">
          <svg class="icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path><polyline points="17 8 12 3 7 8"></polyline><line x1="12" y1="3" x2="12" y2="15"></line></svg>
          <span class="text" v-if="!selectedFile">Choose a file</span>
          <span class="text success" v-else>{{ selectedFile.name }} ({{ (selectedFile.size / 1024).toFixed(1) }}KB)</span>
        </label>
        <input type="file" id="file-input" class="sr-only" @change="onFileChange" accept="image/*" />
      </div>

      <div class="input-group" v-if="mode === 'upload'">
        <label for="keywords-input">Initial Tags (Comma separated)</label>
        <input
          id="keywords-input"
          v-model="keywords"
          type="text"
          placeholder="e.g. landscape, architecture"
          :disabled="!selectedFile"
        />
      </div>

      <button class="btn btn-primary btn-large" @click="handleAction" :disabled="!selectedFile">
        {{ mode === "search" ? "Select for Search" : "Upload Image" }}
      </button>

      <!-- Feedback Area -->
      <div class="feedback-area" v-if="message || lastUploadedId">
        <p class="system-message" :class="{'error': message.toLowerCase().includes('fail') || message.toLowerCase().includes('large')}">
          {{ message }}
        </p>
        
        <div v-if="lastUploadedId !== null && statusCache?.[lastUploadedId]" class="status-tracker">
          <span :class="['status-badge', statusCache[lastUploadedId]!.toLowerCase()]">
            {{ statusCache[lastUploadedId] }}
          </span>
        </div>
      </div>
    </div>

    <!-- Preview -->
    <div v-if="previewUrl" class="preview-panel panel">
      <div class="image-frame">
        <img :src="previewUrl" alt="Image preview" />
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

.upload-container {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-lg);
  max-width: 900px;
}

@media (min-width: 768px) {
  .upload-container {
    grid-template-columns: 1fr 1fr;
  }
}

.form-layout {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
}

.file-drop-zone {
  border-radius: var(--radius-md);
  background: var(--bg-element);
  transition: all 0.2s var(--ease-standard);
  text-align: center;
  box-shadow: var(--shadow-inset);
}

.file-drop-zone:hover {
  background: var(--bg-element-hover);
}

.file-drop-zone.has-file {
  background: color-mix(in oklch, var(--color-success) 10%, var(--bg-surface));
  box-shadow: none;
  border: 1px solid color-mix(in oklch, var(--color-success) 30%, transparent);
}

.file-label {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: var(--space-2xl) var(--space-md);
  cursor: pointer;
  margin: 0;
}

.file-label .icon {
  width: 32px;
  height: 32px;
  margin-bottom: var(--space-sm);
  color: var(--text-muted);
  transition: color 0.2s;
}

.file-drop-zone:hover .icon {
  color: var(--color-accent);
}

.file-drop-zone.has-file .icon {
  color: var(--color-success);
}

.file-label .text {
  font-size: 0.875rem;
  font-weight: 500;
}

.file-label .success {
  color: var(--color-success);
}

.btn-large {
  padding: 0.875rem;
  font-size: 1rem;
}

.feedback-area {
  margin-top: var(--space-sm);
  text-align: center;
}

.system-message {
  color: var(--text-secondary);
  font-size: 0.875rem;
  margin-bottom: var(--space-sm);
}

.system-message.error {
  color: var(--color-danger);
}

.status-tracker {
  display: flex;
  justify-content: center;
}

.preview-panel {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: var(--space-md);
}

.image-frame {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: var(--bg-element);
  border-radius: var(--radius-sm);
  padding: var(--space-md);
  box-shadow: var(--shadow-inset);
}

.image-frame img {
  max-width: 100%;
  max-height: 400px;
  object-fit: contain;
  border-radius: 4px;
}
</style>