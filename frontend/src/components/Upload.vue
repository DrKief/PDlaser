<script setup lang="ts">
import { ref } from "vue";
import http from "../http-api";
import { useImageStatus } from "../composables/useImageStatus";

const props = defineProps({
  mode: {
    type: String,
    default: "upload", // 'upload' or 'search'
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

    // Hide previous status and messages when a new image is being previewed
    lastUploadedId.value = null;
    message.value = "";
  }
};

const handleAction = async () => {
  if (!selectedFile.value) {
    message.value = "Please select a file first!";
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
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    if (response.status === 202) {
      const id = response.data.id;
      message.value = `Upload accepted! Processing started...`;
      lastUploadedId.value = id;

      statusCache[id] = "PENDING";
      pollStatus(id);

      selectedFile.value = null;
      keywords.value = "";
      if (previewUrl.value) {
        URL.revokeObjectURL(previewUrl.value);
        previewUrl.value = null;
      }
      // Reset input if needed
      const input = document.getElementById("file-input") as HTMLInputElement;
      if (input) input.value = "";
    } else if (response.status === 200 || response.status === 201) {
      // Fallback in case it somehow still returns 200/201 without async
      message.value = "Upload successful!";
      selectedFile.value = null;
      keywords.value = "";
      if (previewUrl.value) {
        URL.revokeObjectURL(previewUrl.value);
        previewUrl.value = null;
      }
      const input = document.getElementById("file-input") as HTMLInputElement;
      if (input) input.value = "";
    }
  } catch (error: any) {
    console.error(error);
    if (error.response) {
      const status = error.response.status;
      const data = error.response.data;
      if (status === 413) message.value = "File too large.";
      else if (status === 415) message.value = "Unsupported Media Type. Must be an image.";
      else if (status === 400) message.value = data.error || "Bad Request.";
      else message.value = data.error || "Upload failed.";
    } else {
      message.value = "Upload failed.";
    }
  }
};
</script>

<template>
  <div>
    <div class="upload-form">
      <div class="input-group">
        <input type="file" @change="onFileChange" id="file-input" accept="image/*" />
      </div>
      <div class="input-group" v-if="mode === 'upload'">
        <input
          v-model="keywords"
          type="text"
          placeholder="Keywords (e.g. nature, travel)"
          class="keyword-input"
          :disabled="!selectedFile"
        />
      </div>
      <button @click="handleAction" :disabled="!selectedFile" class="upload-btn">
        {{ mode === "search" ? "Search" : "Upload Image" }}
      </button>
    </div>
    <div v-if="previewUrl" class="image-preview">
      <img :src="previewUrl" alt="Selected image" />
    </div>
    <div class="message-container" v-if="message || lastUploadedId">
      <p v-if="message">{{ message }}</p>
      <div v-if="lastUploadedId !== null && statusCache?.[lastUploadedId]" class="status-indicator">
        Processing Status:
        <span :class="['status-badge', statusCache[lastUploadedId]!.toLowerCase()]">{{
          statusCache[lastUploadedId]
        }}</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.upload-form {
  margin: 20px 0;
  border: 1px solid var(--border-color);
  padding: 8px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  gap: 12px;
  width: fit-content;
}

input[type="file"] {
  font-family: inherit;
  font-size: 0.9rem;
  color: var(--text-primary);
}

input[type="file"]::file-selector-button {
  margin-right: 12px;
  border: 1px solid var(--border-color);
  padding: 8px 16px;
  border-radius: 4px;
  background: transparent;
  color: var(--text-primary);
  cursor: pointer;
  transition:
    background 0.2s,
    color 0.2s,
    border-color 0.2s;
}

input[type="file"]::file-selector-button:hover {
  background: var(--bg-tertiary);
}

button {
  border: 1px solid var(--color-primary);
  padding: 8px 24px;
  border-radius: 4px;
  background: var(--color-primary);
  color: var(--text-on-primary);
  cursor: pointer;
  transition: opacity 0.2s;
}

button:hover:not(:disabled) {
  opacity: 0.9;
}

button:disabled {
  background: var(--bg-tertiary);
  border-color: var(--bg-tertiary);
  color: var(--text-muted);
  cursor: not-allowed;
}

.image-preview {
  display: flex;
  justify-content: center;
  margin-top: 16px;
}

.keyword-input {
  padding: 8px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background: var(--bg-primary);
  color: var(--text-primary);
  min-width: 200px;
}
.image-preview img {
  max-width: 300px;
  max-height: 300px;
  width: auto;
  height: auto;
  border-radius: 8px;
  border: 1px solid var(--border-color);
}

.message-container {
  margin-top: 16px;
  text-align: center;
}

.status-indicator {
  margin-top: 8px;
  font-size: 0.9rem;
}

.status-badge {
  font-size: 0.75rem;
  padding: 4px 8px;
  border-radius: 12px;
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
