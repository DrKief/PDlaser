<script setup lang="ts">
import { ref } from "vue";
import http from "../http-api";

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

const onFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    if (previewUrl.value) {
      URL.revokeObjectURL(previewUrl.value);
    }
    selectedFile.value = target.files[0] as File;
    previewUrl.value = URL.createObjectURL(selectedFile.value);
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

    if (response.status === 200 || response.status === 201) {
      message.value = "Upload successful!";
      selectedFile.value = null;
      keywords.value = "";
      if (previewUrl.value) {
        URL.revokeObjectURL(previewUrl.value);
        previewUrl.value = null;
      }
      // Reset input if needed
      const input = document.getElementById("file-input") as HTMLInputElement;
      if (input) input.value = "";
    }
  } catch (error) {
    console.error(error);
    message.value = "Upload failed.";
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
    <p v-if="message">{{ message }}</p>
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
</style>
