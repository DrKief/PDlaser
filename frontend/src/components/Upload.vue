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
    message.value = "ERR: NO_FILE_SELECTED";
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
    message.value = "UPLOADING...";
    const response = await http.post("/images", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });

    if (response.status === 202) {
      const id = response.data.id;
      message.value = `ACCEPTED. ASSIGNED ID_${id}`;
      lastUploadedId.value = id;

      statusCache[id] = "PENDING";
      pollStatus(id);

      resetForm();
    } else if (response.status === 200 || response.status === 201) {
      message.value = "UPLOAD SUCCESSFUL.";
      resetForm();
    }
  } catch (error: any) {
    console.error(error);
    if (error.response) {
      const status = error.response.status;
      const data = error.response.data;
      if (status === 413) message.value = "ERR: PAYLOAD_TOO_LARGE";
      else if (status === 415) message.value = "ERR: UNSUPPORTED_MEDIA_TYPE";
      else message.value = `ERR: ${data.error || "UPLOAD_FAILED"}`;
    } else {
      message.value = "ERR: NETWORK_FAILURE";
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
    <h2>Ingest Protocol</h2>
    <p class="view-description">Submit visual data for processing and storage.</p>
  </div>

  <div class="upload-container panel">
    <div class="form-layout">
      
      <!-- Custom File Input -->
      <div class="file-drop-zone" :class="{ 'has-file': selectedFile }">
        <label for="file-input" class="file-label">
          <span class="icon">⇪</span>
          <span class="text" v-if="!selectedFile">SELECT TARGET FILE</span>
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
          placeholder="e.g. classification, target, scan"
          :disabled="!selectedFile"
        />
      </div>

      <button class="btn btn-primary btn-large" @click="handleAction" :disabled="!selectedFile">
        {{ mode === "search" ? "EXECUTE QUERY" : "INITIALIZE INGEST" }}
      </button>

      <!-- Feedback Area -->
      <div class="feedback-area" v-if="message || lastUploadedId">
        <div class="system-message" :class="{'error': message.startsWith('ERR')}">
          > {{ message }}
        </div>
        
        <div v-if="lastUploadedId !== null && statusCache?.[lastUploadedId]" class="status-tracker">
          <span>PROCESS_STATE:</span>
          <span :class="['status-badge', statusCache[lastUploadedId]!.toLowerCase()]">
            {{ statusCache[lastUploadedId] }}
          </span>
        </div>
      </div>

    </div>

    <!-- Preview -->
    <div v-if="previewUrl" class="preview-panel">
      <div class="preview-header">OPTICAL_PREVIEW</div>
      <div class="image-frame">
        <img :src="previewUrl" alt="Target preview" />
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

.upload-container {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-xl);
  max-width: 800px;
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
  border: 2px dashed var(--border-color);
  border-radius: var(--radius-sm);
  background: var(--bg-primary);
  transition: all 0.2s var(--ease-out-expo);
  text-align: center;
}

.file-drop-zone:hover {
  border-color: var(--color-primary);
  background: color-mix(in oklch, var(--color-primary) 5%, var(--bg-primary));
}

.file-drop-zone.has-file {
  border-style: solid;
  border-color: var(--color-success);
}

.file-label {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: var(--space-xl);
  cursor: pointer;
  margin: 0;
}

.file-label .icon {
  font-size: 2rem;
  margin-bottom: var(--space-sm);
  color: var(--text-muted);
}

.file-drop-zone:hover .icon {
  color: var(--color-primary);
}

.file-drop-zone.has-file .icon {
  color: var(--color-success);
}

.file-label .text {
  font-family: var(--font-mono);
  font-size: 0.875rem;
  font-weight: bold;
}

.file-label .success {
  color: var(--color-success);
}

.btn-large {
  padding: 1rem;
  font-size: 1rem;
}

.feedback-area {
  background: var(--bg-primary);
  border: 1px solid var(--border-color);
  padding: var(--space-md);
  font-family: var(--font-mono);
  font-size: 0.875rem;
}

.system-message {
  color: var(--text-secondary);
  margin-bottom: var(--space-sm);
}

.system-message.error {
  color: var(--color-danger);
}

.status-tracker {
  display: flex;
  align-items: center;
  gap: var(--space-sm);
  color: var(--text-muted);
}

.preview-panel {
  border: 1px solid var(--border-color);
  background: var(--bg-primary);
  display: flex;
  flex-direction: column;
}

.preview-header {
  font-family: var(--font-mono);
  font-size: 0.75rem;
  padding: var(--space-xs) var(--space-sm);
  background: var(--bg-tertiary);
  border-bottom: 1px solid var(--border-color);
  color: var(--text-secondary);
}

.image-frame {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: var(--space-md);
}

.image-frame img {
  max-width: 100%;
  max-height: 300px;
  object-fit: contain;
}
</style>