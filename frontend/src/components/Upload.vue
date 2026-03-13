<script setup lang="ts">
import { ref } from "vue";
import http from "../http-api";

const selectedFile = ref<File | null>(null);
const message = ref("");

const onFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    selectedFile.value = target.files[0] as File;
  }
};

const uploadImage = async () => {
  if (!selectedFile.value) {
    message.value = "Please select a file first!";
    return;
  }

  const formData = new FormData();
  formData.append("file", selectedFile.value);

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
    <h2>Upload image</h2>
    <div class="upload-form">
      <input
        type="file"
        @change="onFileChange"
        id="file-input"
        accept="image/*"
      />
      <button @click="uploadImage" :disabled="!selectedFile">Upload</button>
    </div>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<style scoped>
.upload-form {
  margin: 20px 0;
  border: 1px solid #555;
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
}

input[type="file"]::file-selector-button {
  margin-right: 12px;
  border: 1px solid #555;
  padding: 8px 16px;
  border-radius: 4px;
  background: transparent;
  color: #e0e0e0;
  cursor: pointer;
  transition: background 0.2s;
}

input[type="file"]::file-selector-button:hover {
  background: #333;
}

button {
  border: 1px solid #33a06f;
  padding: 8px 24px;
  border-radius: 4px;
  background: #33a06f;
  color: #fff;
  cursor: pointer;
  transition: opacity 0.2s;
}

button:hover:not(:disabled) {
  opacity: 0.9;
}

button:disabled {
  background: #333;
  border-color: #333;
  color: #888;
  cursor: not-allowed;
}
</style>
