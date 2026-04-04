<script setup lang="ts">
import { ref, onMounted, onUnmounted, computed } from "vue";
import http from "../api/http-client";

const activeTab = ref("sync");
const status = ref("Loading...");
const limit = ref(5000);
const offset = ref(0);
const fileType = ref("PHOTOS");
const selectedFile = ref<File | null>(null);
let pollingInterval: any = null;

// Catalog State
const catalogImages = ref<any[]>([]);
const searchQuery = ref("");
const searchCamera = ref("");
const searchCountry = ref("");
const currentPage = ref(0);
const totalPages = ref(1);
const selectedIds = ref<Set<number>>(new Set());
const batchLimit = ref(1000);

const isBusy = computed(() => {
  return (
    status.value.startsWith("DOWNLOADING") ||
    status.value.startsWith("EXTRACTING") ||
    status.value.startsWith("SYNCING") ||
    status.value.startsWith("IMPORTING")
  );
});

const checkStatus = async () => {
  try {
    const res = await http.get("/admin/unsplash/status");
    status.value = res.data.status;
  } catch (e) {
    status.value = "Access Denied / Error";
  }
};

const onFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    selectedFile.value = target.files[0];
  }
};

const startUploadAndSync = async () => {
  if (!selectedFile.value) {
    alert("Please select a file first (e.g., photos.csv000 or photos.tsv).");
    return;
  }

  const formData = new FormData();
  formData.append("file", selectedFile.value);
  formData.append("limit", limit.value.toString());
  formData.append("offset", offset.value.toString());
  formData.append("fileType", fileType.value);

  try {
    await http.post("/admin/unsplash/upload", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    // Clear selection after upload
    selectedFile.value = null;
    (document.getElementById("datasetFileInput") as HTMLInputElement).value = "";
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to upload and sync file.");
  }
};

const fetchCatalog = async (page = 0) => {
  currentPage.value = page;
  try {
    const res = await http.get(`/admin/unsplash/catalog`, {
      params: {
        page,
        size: 24,
        query: searchQuery.value,
        camera: searchCamera.value,
        country: searchCountry.value,
      },
    });
    catalogImages.value = res.data.content;
    totalPages.value = res.data.totalPages || 1;
  } catch (e) {
    console.error("Failed to load catalog");
  }
};

const toggleSelection = (id: number) => {
  if (selectedIds.value.has(id)) {
    selectedIds.value.delete(id);
  } else {
    selectedIds.value.add(id);
  }
};

const selectAll = () => {
  if (selectedIds.value.size === catalogImages.value.length) {
    selectedIds.value.clear();
  } else {
    catalogImages.value.forEach((img) => selectedIds.value.add(img.id));
  }
};

const importSelected = async () => {
  if (selectedIds.value.size === 0) return;
  try {
    await http.post("/admin/unsplash/import", Array.from(selectedIds.value));
    selectedIds.value.clear();
    fetchCatalog(currentPage.value);
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to start import");
  }
};

const importBatch = async () => {
  if (!confirm(`Are you sure you want to queue the top ${batchLimit.value} matches?`)) return;
  try {
    await http.post("/admin/unsplash/import/batch", null, {
      params: {
        limit: batchLimit.value,
        query: searchQuery.value,
        camera: searchCamera.value,
        country: searchCountry.value,
      },
    });
    fetchCatalog(currentPage.value);
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to start batch import");
  }
};

const handleSearch = () => {
  fetchCatalog(0);
};

onMounted(() => {
  checkStatus();
  fetchCatalog();
  pollingInterval = setInterval(checkStatus, 3000);
});

onUnmounted(() => {
  clearInterval(pollingInterval);
});
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header text-center">
      <h1 class="page-title">Dataset Orchestrator</h1>
      <p class="page-subtitle">
        Upload dataset chunks, map remote metadata, and selectively import High-Res assets.
      </p>
    </header>

    <div class="system-status-bar" :class="{ active: isBusy, error: status.startsWith('ERROR') }">
      <span class="material-symbols-outlined icon" v-if="!status.startsWith('ERROR')">sync</span>
      <span class="material-symbols-outlined" v-else>error</span>
      <span class="status-text">{{ status }}</span>
    </div>

    <div class="tabs">
      <button :class="{ active: activeTab === 'sync' }" @click="activeTab = 'sync'">
        1. Upload & Sync
      </button>
      <button :class="{ active: activeTab === 'catalog' }" @click="activeTab = 'catalog'">
        2. Remote Catalog
      </button>
    </div>

    <!-- TAB 1: UPLOAD & SYNC -->
    <div v-if="activeTab === 'sync'" class="sync-layout max-w-lg" style="margin: 0 auto">
      <div class="card meta-card">
        <h3 class="meta-title">Map Unsplash Dataset</h3>

        <div class="file-type-toggle">
          <label class="radio-label">
            <input type="radio" v-model="fileType" value="PHOTOS" />
            <span>📸 Photos Metadata (photos.tsv)</span>
          </label>
          <label class="radio-label">
            <input type="radio" v-model="fileType" value="KEYWORDS" />
            <span>🏷️ Keywords & Tags (keywords.tsv)</span>
          </label>
        </div>

        <p class="help-text" v-if="fileType === 'PHOTOS'">
          Select your local <code>photos.csv000</code> or <code>photos.tsv</code> file. The server
          will parse the metadata directly into the database.
          <strong>Actual image downloads occur in Step 2.</strong>
        </p>
        <p class="help-text" v-else style="color: var(--color-warning)">
          <strong>Note:</strong> You must sync Photos before syncing Keywords. Upload
          <code>keywords.tsv</code>. Keywords for unknown photos will be skipped.
        </p>

        <div class="file-drop-area" :class="{ 'has-file': selectedFile }">
          <span class="material-symbols-outlined" style="font-size: 3rem; margin-bottom: 1rem"
            >upload_file</span
          >
          <p v-if="!selectedFile">Click or drag a dataset file here</p>
          <p v-else style="font-weight: bold; color: var(--color-accent)">
            {{ selectedFile.name }} ({{ Math.round(selectedFile.size / 1024 / 1024) }} MB)
          </p>
          <input
            type="file"
            id="datasetFileInput"
            @change="onFileChange"
            accept=".tsv,.csv,.csv000,.txt"
            class="hidden-file-input"
          />
        </div>

        <div style="display: flex; gap: 1rem; margin: 1.5rem 0">
          <div class="form-group" style="flex: 1">
            <label class="label-text">Rows to Process</label>
            <input type="number" v-model="limit" min="1" max="1000000" />
          </div>
          <div class="form-group" style="flex: 1">
            <label class="label-text">Skip Rows</label>
            <input type="number" v-model="offset" min="0" />
          </div>
        </div>
        <button class="btn w-full" @click="startUploadAndSync" :disabled="isBusy || !selectedFile">
          Upload and Sync {{ fileType === "PHOTOS" ? "Photos" : "Keywords" }}
        </button>
      </div>
    </div>

    <!-- TAB 2: REMOTE CATALOG -->
    <div v-if="activeTab === 'catalog'" class="catalog-section">
      <div class="catalog-toolbar">
        <input
          type="text"
          v-model="searchQuery"
          @keydown.enter="handleSearch"
          placeholder="Search any..."
          class="search-bar"
        />
        <input
          type="text"
          v-model="searchCamera"
          @keydown.enter="handleSearch"
          placeholder="Filter Camera (e.g. Sony)..."
          class="search-bar"
        />
        <input
          type="text"
          v-model="searchCountry"
          @keydown.enter="handleSearch"
          placeholder="Filter Country (e.g. Japan)..."
          class="search-bar"
        />
        <button class="btn" @click="handleSearch">Search</button>
        <button class="btn btn-outline" @click="selectAll">Select All Visible</button>
      </div>

      <div
        class="batch-toolbar"
        style="margin-bottom: 1.5rem; display: flex; gap: 1rem; align-items: center"
      >
        <label class="label-text">Batch Import Count:</label>
        <input
          type="number"
          v-model="batchLimit"
          min="1"
          max="50000"
          class="search-bar"
          style="max-width: 150px"
        />
        <button class="btn btn-outline" @click="importBatch" :disabled="isBusy">
          Import Top {{ batchLimit }} Matches
        </button>
      </div>

      <div class="catalog-grid">
        <div
          v-for="img in catalogImages"
          :key="img.id"
          class="catalog-card"
          :class="{ selected: selectedIds.has(img.id) }"
          @click="toggleSelection(img.id)"
        >
          <div class="checkbox-indicator">
            <span class="material-symbols-outlined" v-if="selectedIds.has(img.id)"
              >check_circle</span
            >
          </div>
          <img :src="img.remote_url + '?w=400'" class="thumb" loading="lazy" />
          <div class="info">
            <span class="photog">📸 {{ img.photographer_name || "Unknown" }}</span>
            <span class="detail">🌍 {{ img.location_country || "Unknown Loc" }}</span>
            <span class="detail">📷 {{ img.camera_make || "Unknown Camera" }}</span>
            <span class="detail">⬇️ {{ img.stats_downloads }} downloads</span>
          </div>
        </div>
      </div>

      <div v-if="catalogImages.length === 0" class="empty-catalog">
        No remote metadata found. Please upload a dataset file in Step 1 first.
      </div>

      <div class="pagination" v-if="totalPages > 1">
        <button
          class="btn-outline"
          @click="fetchCatalog(currentPage - 1)"
          :disabled="currentPage === 0"
        >
          Prev
        </button>
        <span class="page-info">Page {{ currentPage + 1 }} of {{ totalPages }}</span>
        <button
          class="btn-outline"
          @click="fetchCatalog(currentPage + 1)"
          :disabled="currentPage === totalPages - 1"
        >
          Next
        </button>
      </div>

      <div class="sticky-action-bar" v-if="selectedIds.size > 0">
        <span
          ><strong>{{ selectedIds.size }}</strong> images selected for import.</span
        >
        <button class="btn" @click="importSelected" :disabled="isBusy">Download & Vectorize</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.text-center {
  text-align: center;
}
.max-w-lg {
  max-width: 600px;
}

.system-status-bar {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  padding: 1rem;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  margin-bottom: 2rem;
  font-family: var(--font-mono);
  transition: all 0.3s;
}
.system-status-bar.active {
  background: var(--bg-element);
  border-color: var(--color-accent);
  color: var(--color-accent);
}
.system-status-bar.error {
  border-color: var(--color-danger);
  color: var(--color-danger);
}
.system-status-bar.active .icon {
  animation: spin 2s linear infinite;
}
@keyframes spin {
  100% {
    transform: rotate(360deg);
  }
}

.tabs {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-bottom: 2rem;
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1rem;
}
.tabs button {
  background: none;
  border: none;
  font-family: var(--font-sans);
  font-size: 1rem;
  font-weight: 600;
  color: var(--text-secondary);
  cursor: pointer;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: all 0.2s;
}
.tabs button.active,
.tabs button:hover {
  background: var(--bg-element);
  color: var(--text-primary);
}

.meta-card {
  padding: 2rem;
}

.file-type-toggle {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-bottom: 1.5rem;
  background: var(--bg-surface-alt);
  padding: 1rem;
  border-radius: 8px;
  border: 1px solid var(--border-subtle);
}
.radio-label {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  cursor: pointer;
  font-weight: 500;
  color: var(--text-primary);
}

/* New File Drop Area Styles */
.file-drop-area {
  position: relative;
  background: var(--bg-surface-alt);
  border: 2px dashed var(--border-subtle);
  border-radius: 8px;
  padding: 3rem 1rem;
  text-align: center;
  margin-top: 1.5rem;
  transition: all 0.2s ease;
  color: var(--text-secondary);
}
.file-drop-area:hover,
.file-drop-area.has-file {
  background: var(--bg-element);
  border-color: var(--color-accent);
}
.hidden-file-input {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
}

.catalog-toolbar {
  display: flex;
  gap: 1rem;
  margin-bottom: 1.5rem;
}
.search-bar {
  flex: 1;
  padding: 0.75rem 1rem;
  border: 1px solid var(--border-subtle);
  border-radius: 4px;
  background: var(--bg-surface);
  color: var(--text-primary);
}

.catalog-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2rem;
}
.catalog-card {
  background: var(--bg-surface);
  border: 2px solid var(--border-subtle);
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  position: relative;
  transition:
    transform 0.2s,
    border-color 0.2s;
}
.catalog-card:hover {
  transform: translateY(-4px);
  border-color: var(--text-primary);
}
.catalog-card.selected {
  border-color: var(--color-accent);
}
.checkbox-indicator {
  position: absolute;
  top: 8px;
  left: 8px;
  color: var(--color-accent);
  background: rgba(0, 0, 0, 0.5);
  border-radius: 50%;
  display: flex;
}
.thumb {
  width: 100%;
  height: 150px;
  object-fit: cover;
  display: block;
}
.info {
  padding: 0.75rem;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  font-size: 0.8rem;
}
.photog {
  font-weight: bold;
  color: var(--text-primary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.detail {
  color: var(--text-secondary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.empty-catalog {
  text-align: center;
  padding: 4rem;
  color: var(--text-muted);
  font-size: 1.1rem;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  margin-bottom: 6rem;
}
.btn-outline {
  padding: 0.5rem 1rem;
  border: 1px solid var(--border-subtle);
  background: var(--bg-surface);
  color: var(--text-primary);
  border-radius: 4px;
  cursor: pointer;
}
.btn-outline:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.sticky-action-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: var(--bg-element);
  border-top: 1px solid var(--border-strong);
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 100;
  box-shadow: 0 -4px 20px rgba(0, 0, 0, 0.2);
}
</style>
