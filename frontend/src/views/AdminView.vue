<script setup lang="ts">
import { ref, onMounted, onUnmounted } from "vue";
import http from "../api/http-client";

const activeTab = ref("upload");
const status = ref("IDLE");
const limit = ref(1000);
const fileInput = ref<HTMLInputElement | null>(null);

// Catalog State
const catalogImages = ref<any[]>([]);
const searchQuery = ref("");
const currentPage = ref(0);
const totalPages = ref(1);
const selectedIds = ref<Set<number>>(new Set());

const checkStatus = async () => {
  try {
    const res = await http.get("/admin/unsplash/status");
    status.value = res.data.status;
  } catch (e) { status.value = "OFFLINE"; }
};

const handleFileUpload = async () => {
  if (!fileInput.value?.files?.length) return;
  const formData = new FormData();
  formData.append("file", fileInput.value.files[0]);
  formData.append("limit", limit.value.toString());

  try {
    await http.post("/admin/unsplash/upload", formData, {
      headers: { "Content-Type": "multipart/form-data" }
    });
    alert("File uploaded. Processing rows in background...");
  } catch (e) {
    alert("Upload failed.");
  }
};

const fetchCatalog = async (page = 0) => {
  currentPage.value = page;
  try {
    const res = await http.get(`/admin/unsplash/catalog`, {
      params: { page, size: 24, query: searchQuery.value },
    });
    catalogImages.value = res.data.content;
    totalPages.value = res.data.totalPages || 1;
  } catch (e) { console.error("Catalog load failed"); }
};

const toggleSelection = (id: number) => {
  selectedIds.value.has(id) ? selectedIds.value.delete(id) : selectedIds.value.add(id);
};

const importSelected = async () => {
  try {
    await http.post("/admin/unsplash/import", Array.from(selectedIds.value));
    selectedIds.value.clear();
    alert("Download started in background.");
  } catch (e) { alert("Import failed."); }
};

onMounted(() => {
  checkStatus();
  fetchCatalog();
  const interval = setInterval(checkStatus, 3000);
  onUnmounted(() => clearInterval(interval));
});
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header text-center">
      <h1 class="page-title">Dataset Orchestrator</h1>
      <p class="page-subtitle">Upload Unsplash .csv000 files to index the remote catalog.</p>
    </header>

    <div class="status-bar" :class="{ active: status !== 'IDLE' }">
      Status: {{ status }}
    </div>

    <div class="tabs">
      <button :class="{ active: activeTab === 'upload' }" @click="activeTab = 'upload'">1. Upload Database</button>
      <button :class="{ active: activeTab === 'catalog' }" @click="activeTab = 'catalog'">2. Browse & Import</button>
    </div>

    <div v-if="activeTab === 'upload'" class="card max-w-lg" style="margin: 0 auto">
      <h3>Index Unsplash File</h3>
      <p class="help-text">Select <code>photos.csv000</code> from your local Unsplash folder.</p>
      
      <div class="form-group mt-4">
        <label class="label-text">Max Rows to Index</label>
        <input type="number" v-model="limit" />
      </div>

      <div class="form-group mt-4">
        <input type="file" ref="fileInput" accept=".csv000,.tsv" />
      </div>

      <button class="btn w-full mt-4" @click="handleFileUpload" :disabled="status !== 'IDLE'">
        Upload and Index
      </button>
    </div>

    <div v-if="activeTab === 'catalog'">
      <div class="toolbar">
        <input v-model="searchQuery" @keydown.enter="fetchCatalog(0)" placeholder="Search catalog..." class="search-bar" />
        <button class="btn" @click="fetchCatalog(0)">Search</button>
      </div>

      <div class="catalog-grid">
        <div v-for="img in catalogImages" :key="img.id" 
             class="catalog-card" :class="{ selected: selectedIds.has(img.id) }"
             @click="toggleSelection(img.id)">
          <img :src="img.remote_url + '?w=300'" class="thumb" />
          <div class="info">
            <span class="photog">{{ img.photographer_name }}</span>
            <span class="detail">{{ img.camera_make }} | {{ img.location_country }}</span>
          </div>
        </div>
      </div>

      <div class="pagination" v-if="totalPages > 1">
        <button @click="fetchCatalog(currentPage - 1)" :disabled="currentPage === 0">Prev</button>
        <span>Page {{ currentPage + 1 }} of {{ totalPages }}</span>
        <button @click="fetchCatalog(currentPage + 1)" :disabled="currentPage >= totalPages - 1">Next</button>
      </div>

      <div class="sticky-bar" v-if="selectedIds.size > 0">
        <button class="btn" @click="importSelected">Import {{ selectedIds.size }} Images</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.status-bar { padding: 1rem; background: var(--bg-element); border-radius: 8px; margin-bottom: 2rem; text-align: center; font-family: var(--font-mono); }
.status-bar.active { color: var(--color-accent); border: 1px solid var(--color-accent); }
.tabs { display: flex; justify-content: center; gap: 1rem; margin-bottom: 2rem; }
.tabs button { background: none; border: none; padding: 0.5rem 1rem; cursor: pointer; color: var(--text-secondary); font-weight: bold; }
.tabs button.active { color: var(--text-primary); border-bottom: 2px solid var(--color-accent); }
.catalog-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 1rem; margin-top: 1rem; }
.catalog-card { background: var(--bg-surface); border: 1px solid var(--border-subtle); border-radius: 4px; overflow: hidden; cursor: pointer; transition: transform 0.1s; }
.catalog-card.selected { border: 2px solid var(--color-accent); transform: scale(0.98); }
.thumb { width: 100%; height: 150px; object-fit: cover; }
.info { padding: 0.5rem; font-size: 0.75rem; display: flex; flex-direction: column; }
.photog { font-weight: bold; }
.toolbar { display: flex; gap: 1rem; margin-bottom: 1rem; }
.search-bar { flex: 1; padding: 0.5rem; border: 1px solid var(--border-subtle); border-radius: 4px; background: var(--bg-surface); color: var(--text-primary); }
.pagination { display: flex; justify-content: center; align-items: center; gap: 1rem; margin: 2rem 0 6rem 0; }
.sticky-bar { position: fixed; bottom: 2rem; left: 50%; transform: translateX(-50%); z-index: 100; }
</style>