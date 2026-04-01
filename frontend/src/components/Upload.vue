<script setup lang="ts">
import { ref } from "vue";
import http from "../http-api";
import { useImageStatus } from "../composables/useImageStatus";

interface UploadTask {
  internalId: string;
  file: File;
  previewUrl: string;
  id?: number;
  status: 'PENDING' | 'UPLOADING' | 'EXTRACTING' | 'COMPLETED' | 'FAILED' | 'DUPLICATE';
}

const tasks = ref<UploadTask[]>([]);
const tagsInput = ref("");
const tagsList = ref<string[]>([]);
const isUploading = ref(false);
const globalMessage = ref("");
const { pollStatus, statusCache } = useImageStatus();

const onFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (!target.files || target.files.length === 0) return;
  
  const incomingFiles = Array.from(target.files);
  const availableSlots = 10 - tasks.value.length;
  
  if (incomingFiles.length > availableSlots) {
    globalMessage.value = `Maximum 10 images. Keeping the first ${availableSlots} files.`;
  } else {
    globalMessage.value = "";
  }

  const filesToAdd = incomingFiles.slice(0, availableSlots);
  filesToAdd.forEach(file => {
    tasks.value.push({
      internalId: Math.random().toString(36).substring(7),
      file,
      previewUrl: URL.createObjectURL(file),
      status: 'PENDING'
    });
  });
  target.value = ''; 
};

const removeTaskById = (iId: string) => {
  const index = tasks.value.findIndex(t => t.internalId === iId);
  if (index > -1) {
    if (isUploading.value && tasks.value[index].status === 'UPLOADING') return;
    URL.revokeObjectURL(tasks.value[index].previewUrl);
    tasks.value.splice(index, 1);
  }
};

const addTag = (event: KeyboardEvent | FocusEvent) => {
  event.preventDefault();
  const t = tagsInput.value.trim().toLowerCase().replace(/\s+/g, '_');
  if (t && !tagsList.value.includes(t)) {
    tagsList.value.push(t);
  }
  tagsInput.value = "";
};

const removeTag = (tag: string) => {
  tagsList.value = tagsList.value.filter(t => t !== tag);
};

const executeUpload = async () => {
  if (tasks.value.length === 0) return;
  isUploading.value = true;
  globalMessage.value = "Uploading batch...";

  for (let i = tasks.value.length - 1; i >= 0; i--) {
    const task = tasks.value[i];
    if (task.status !== 'PENDING' && task.status !== 'FAILED') continue;
    
    task.status = 'UPLOADING';
    const formData = new FormData();
    formData.append("file", task.file);
    if (tagsList.value.length > 0) formData.append("keywords", tagsList.value.join(","));

    try {
      const response = await http.post("/images", formData, { headers: { "Content-Type": "multipart/form-data" } });
      const id = response.data.id || response.data;
      task.id = id;
      task.status = 'EXTRACTING';
      
      pollStatus(id).then(() => {
        if (statusCache[id] === 'COMPLETED') {
            task.status = 'COMPLETED';
            setTimeout(() => removeTaskById(task.internalId), 3500); // UI Clears out
        }
        if (statusCache[id] === 'FAILED') task.status = 'FAILED';
      });
      
    } catch (e: any) {
      if (e.response?.status === 409) {
          task.status = 'DUPLICATE';
          setTimeout(() => removeTaskById(task.internalId), 3500); // UI Clears out
      } else {
          task.status = 'FAILED';
      }
    }
  }
  
  globalMessage.value = "Batch uploaded. Completed items clear automatically.";
  isUploading.value = false;
};

const clearAll = () => {
  tasks.value.forEach(t => URL.revokeObjectURL(t.previewUrl));
  tasks.value = [];
  tagsList.value = [];
  tagsInput.value = "";
  globalMessage.value = "";
};
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">Upload Images</h1>
      <p class="page-subtitle">Add up to 10 images. They will be mathematically analyzed for similarity search.</p>
    </header>

    <div class="upload-grid">
      <section class="queue-col">
        <div class="drop-zone" v-if="!isUploading && tasks.length < 10">
          <div class="drop-box">
            <span class="material-symbols-outlined icon">add_photo_alternate</span>
            <span class="label-text">Select Files (Max 10)</span>
          </div>
          <input type="file" @change="onFileChange" accept="image/*" multiple class="file-input" title=" "/>
        </div>

        <div class="task-list" v-if="tasks.length > 0">
          <div v-for="task in tasks" :key="task.internalId" class="task-item">
            <img :src="task.previewUrl" class="task-thumb" />
            <div class="task-info">
              <span class="task-name" :title="task.file.name">{{ task.file.name }}</span>
              <span class="status-badge" :class="task.status.toLowerCase()">
                {{ task.status === 'EXTRACTING' && statusCache[task.id!] ? statusCache[task.id!] : task.status }}
              </span>
            </div>
            <button class="btn-icon" @click="removeTaskById(task.internalId)" v-if="!isUploading && task.status === 'PENDING'">
              <span class="material-symbols-outlined">close</span>
            </button>
          </div>
        </div>
      </section>

      <section class="meta-col">
        <div class="meta-card">
          <h3 class="meta-title">Batch Metadata</h3>
          
          <div class="input-group">
            <label class="label-text">Tags (Applied to all)</label>
            <div class="tags-container">
              <span v-for="tag in tagsList" :key="tag" class="tag-pill">
                {{ tag }}
                <button @click="removeTag(tag)" class="tag-remove"><span class="material-symbols-outlined">close</span></button>
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
          <button class="btn w-full" @click="executeUpload" :disabled="tasks.length === 0 || isUploading">
            {{ isUploading ? 'Processing...' : 'Upload & Analyze' }}
          </button>
          <button class="btn btn-outline w-full" @click="clearAll" v-if="tasks.length > 0 && !isUploading">
            Clear Queue
          </button>
        </div>
      </section>
    </div>
  </div>
</template>

<style scoped>
/* Incorporates all necessary UI updates */
.view-wrapper { animation: fadeIn 0.4s ease-out; max-width: 1200px; margin: 0 auto; }
@keyframes fadeIn { from { opacity: 0; transform: translateY(10px); } to { opacity: 1; transform: translateY(0); } }

.page-header { margin-bottom: var(--space-lg); }
.page-title { font-size: 3rem; font-family: var(--font-headline); margin-bottom: 0.5rem; }
.page-subtitle { color: var(--text-secondary); font-size: 1rem; }

.upload-grid { display: grid; grid-template-columns: 1fr; gap: var(--space-lg); align-items: start; }
@media (min-width: 1024px) { .upload-grid { grid-template-columns: 2fr 1fr; } }

.drop-zone { position: relative; background: var(--bg-surface-alt); border: 2px dashed var(--border-subtle); border-radius: 8px; padding: 3rem; text-align: center; transition: all 0.2s; margin-bottom: var(--space-md); }
.drop-zone:hover { border-color: var(--border-strong); background: var(--bg-element); }
.file-input { position: absolute; inset: 0; width: 100%; height: 100%; opacity: 0; cursor: pointer; z-index: 10; }
.drop-box { display: flex; flex-direction: column; align-items: center; gap: 1rem; color: var(--text-secondary); }
.drop-box .icon { font-size: 3rem; }

.task-list { display: flex; flex-direction: column; gap: 0.75rem; }
.task-item { display: flex; align-items: center; gap: 1rem; background: var(--bg-surface); border: 1px solid var(--border-subtle); padding: 0.75rem; border-radius: 6px; }
.task-thumb { width: 48px; height: 48px; object-fit: cover; border-radius: 4px; flex-shrink: 0; }
.task-info { flex: 1; min-width: 0; display: flex; flex-direction: column; gap: 0.25rem; }
.task-name { font-size: 0.9rem; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; color: var(--text-primary); }

.status-badge { font-size: 0.75rem; font-weight: 600; text-transform: uppercase; letter-spacing: 0.05em; }
.status-badge.pending { color: var(--text-muted); }
.status-badge.uploading { color: var(--color-accent); }
.status-badge.extracting { color: var(--color-accent); animation: pulseText 1.5s infinite; }
.status-badge.completed { color: var(--color-success); }
.status-badge.failed { color: var(--color-danger); }
.status-badge.duplicate { color: #f59e0b; }

@keyframes pulseText { 0% { opacity: 1; } 50% { opacity: 0.5; } 100% { opacity: 1; } }

.btn-icon { background: none; border: none; color: var(--text-muted); cursor: pointer; padding: 0.25rem; display: flex; flex-shrink: 0; }
.btn-icon:hover { color: var(--color-danger); }
.meta-card { background: var(--bg-surface); border: 1px solid var(--border-subtle); border-radius: 8px; padding: 1.5rem; margin-bottom: var(--space-md); }
.meta-title { font-size: 1.25rem; margin-bottom: 1.5rem; font-family: var(--font-sans); font-weight: 600; }
.input-group label { display: block; margin-bottom: 0.75rem; }
.tags-container { display: flex; flex-wrap: wrap; gap: 0.5rem; padding: 0.5rem; border: 1px solid var(--border-subtle); border-radius: 4px; background: var(--bg-body); min-height: 48px; align-items: center; }
.tags-container:focus-within { border-color: var(--border-strong); }
.tag-pill { display: flex; align-items: center; gap: 0.25rem; background: var(--bg-element); color: var(--text-primary); padding: 0.25rem 0.5rem 0.25rem 0.75rem; border-radius: 16px; font-size: 0.8rem; font-weight: 500; }
.tag-remove { background: none; border: none; display: flex; align-items: center; cursor: pointer; padding: 2px; color: var(--text-secondary); border-radius: 50%; }
.tag-remove:hover { color: var(--color-danger); background: var(--bg-surface); }
.tag-remove span { font-size: 14px; }
.tag-input { border: none; background: transparent; flex: 1; min-width: 120px; padding: 0.25rem; font-size: 0.9rem; outline: none; margin: 0; color: var(--text-primary); }
.global-msg { font-size: 0.9rem; color: var(--text-secondary); margin-bottom: 1rem; text-align: center; }
.actions { display: flex; flex-direction: column; gap: 1rem; }
.w-full { width: 100%; }

:root.cruelty .page-title { font-family: 'Impact'; color: #00FF00; font-size: 5rem; text-transform: uppercase; }
:root.cruelty .drop-zone { border: 4px dashed #FF00FF; background: #000; border-radius: 0; }
:root.cruelty .task-item { background: #111; border: 2px solid var(--color-accent); border-radius: 0; }
:root.cruelty .meta-card { background: #000; border: 4px solid var(--border-strong); border-radius: 0; }
:root.cruelty .tag-pill { background: #FFFF00; color: #000; font-family: 'Impact'; border-radius: 0; text-transform: uppercase; }
</style>
