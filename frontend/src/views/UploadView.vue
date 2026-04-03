<script setup lang="ts">
import { ref } from "vue";
import { useUploadQueue } from "../composables/useUploadQueue";

const tagsInput = ref("");
const {
  tasks,
  tagsList,
  isUploading,
  globalMessage,
  statusCache,
  executeUpload,
  onFileChange,
  removeTaskById,
  clearAll,
} = useUploadQueue();

const addTag = (event: KeyboardEvent | FocusEvent) => {
  event.preventDefault();
  const t = tagsInput.value.trim().toLowerCase().replace(/\s+/g, "_");
  if (t && !tagsList.value.includes(t)) {
    tagsList.value.push(t);
  }
  tagsInput.value = "";
};

const removeTag = (tag: string) => {
  tagsList.value = tagsList.value.filter((t) => t !== tag);
};
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">Upload Images</h1>
      <p class="page-subtitle">
        Add up to 10 images. They will be mathematically analyzed for similarity search.
      </p>
    </header>
    <div class="upload-grid">
      <section class="queue-col">
        <div class="drop-zone" v-if="!isUploading && tasks.length < 10">
          <div class="drop-box">
            <span class="material-symbols-outlined icon">add_photo_alternate</span>
            <span class="label-text">Select Files (Max 10)</span>
          </div>
          <input
            type="file"
            @change="onFileChange"
            accept="image/*"
            multiple
            class="file-input"
            title=" "
          />
        </div>
        <div class="task-list" v-if="tasks.length > 0">
          <div v-for="task in tasks" :key="task.internalId" class="task-item">
            <img :src="task.previewUrl" class="task-thumb" />
            <div class="task-info">
              <span class="task-name" :title="task.file.name">{{ task.file.name }}</span>
              <span class="status-badge" :class="task.status.toLowerCase()">
                {{
                  task.status === "EXTRACTING" && statusCache[task.id!]
                    ? statusCache[task.id!]
                    : task.status
                }}
              </span>
            </div>
            <button
              class="btn-icon"
              @click="removeTaskById(task.internalId)"
              v-if="!isUploading && task.status === 'PENDING'"
            >
              <span class="material-symbols-outlined">close</span>
            </button>
          </div>
        </div>
      </section>
      <section class="meta-col">
        <div class="card meta-card">
          <h3 class="meta-title">Batch Metadata</h3>
          <div class="input-group">
            <label class="label-text">Tags (Applied to all)</label>
            <div class="tags-container">
              <span v-for="tag in tagsList" :key="tag" class="tag-pill">
                {{ tag }}
                <button @click="removeTag(tag)" class="tag-remove">
                  <span class="material-symbols-outlined">close</span>
                </button>
              </span>
              <input
                type="text"
                v-model="tagsInput"
                @keydown.enter="addTag"
                class="tag-input"
                placeholder="Type tag and press enter..."
                :disabled="isUploading"
              />
            </div>
          </div>
        </div>
        <p class="global-msg" v-if="globalMessage">{{ globalMessage }}</p>
        <div class="actions">
          <button
            class="btn w-full"
            @click="executeUpload"
            :disabled="tasks.length === 0 || isUploading"
          >
            {{ isUploading ? "Processing..." : "Upload & Analyze" }}
          </button>
          <button
            class="btn btn-outline w-full"
            @click="clearAll"
            v-if="tasks.length > 0 && !isUploading"
          >
            Clear Queue
          </button>
        </div>
      </section>
    </div>
  </div>
</template>

<style scoped>
.upload-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-lg);
  align-items: start;
}

.queue-col {
  min-width: 0;
}

@media (min-width: 1024px) {
  .upload-grid {
    grid-template-columns: 2fr 1fr;
  }
}

.drop-zone {
  position: relative;
  background: var(--bg-surface-alt);
  border: 2px dashed var(--border-subtle);
  border-radius: 8px;
  padding: 3rem;
  text-align: center;
  transition: all 0.2s;
  margin-bottom: var(--space-md);
}

.drop-zone:hover {
  border-color: var(--border-strong);
  background: var(--bg-element);
}

.file-input {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
  z-index: 10;
}

.drop-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  color: var(--text-secondary);
}

.drop-box .icon {
  font-size: 3rem;
}

.task-list {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.task-item {
  display: flex;
  align-items: center;
  min-width: 0;
  gap: 1rem;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  padding: 0.75rem;
  border-radius: 6px;
}

.task-thumb {
  width: 48px;
  height: 48px;
  object-fit: cover;
  border-radius: 4px;
  flex-shrink: 0;
}

.task-info {
  flex: 1;
  min-width: 0;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  overflow: hidden;
}

.task-name {
  font-size: 0.9rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  color: var(--text-primary);
}

.status-badge {
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.status-badge.pending {
  color: var(--text-muted);
}

.status-badge.uploading {
  color: var(--color-accent);
}

.status-badge.extracting {
  color: var(--color-accent);
  animation: pulseText 1.5s infinite;
}

.status-badge.completed {
  color: var(--color-success);
}

.status-badge.failed {
  color: var(--color-danger);
}

.status-badge.duplicate {
  color: #f59e0b;
}

@keyframes pulseText {
  0% {
    opacity: 1;
  }
  50% {
    opacity: 0.5;
  }
  100% {
    opacity: 1;
  }
}

.btn-icon {
  background: none;
  border: none;
  color: var(--text-muted);
  cursor: pointer;
  padding: 0.25rem;
  display: flex;
  flex-shrink: 0;
}

.btn-icon:hover {
  color: var(--color-danger);
}

.meta-card {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 1.5rem;
  margin-bottom: var(--space-md);
}

.meta-title {
  font-size: 1.25rem;
  margin-bottom: 1.5rem;
  font-family: var(--font-sans);
  font-weight: 600;
}

.input-group label {
  display: block;
  margin-bottom: 0.75rem;
}

.tag-remove span {
  font-size: 14px;
}

.global-msg {
  font-size: 0.9rem;
  color: var(--text-secondary);
  margin-bottom: 1rem;
  text-align: center;
}

.actions {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
</style>
