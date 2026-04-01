<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';
import http from '../http-api';

const status = ref("Loading...");
let pollingInterval: any = null;

const checkStatus = async () => {
  try {
    const res = await http.get('/admin/unsplash/status');
    status.value = res.data.status;
  } catch (e) {
    status.value = "Access Denied / Error";
  }
};

const startImport = async () => {
  try {
    await http.post('/admin/unsplash/import');
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to start import");
  }
};

onMounted(() => {
  checkStatus();
  pollingInterval = setInterval(checkStatus, 5000);
});

onUnmounted(() => clearInterval(pollingInterval));
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">Admin Operations</h1>
      <p class="page-subtitle">Server maintenance and dataset ingestion routines.</p>
    </header>

    <div class="meta-card">
      <h3 class="meta-title">Unsplash Dataset Ingestion</h3>
      <p class="label-text">Current System Status: <span class="highlight">{{ status }}</span></p>
      
      <p class="help-text" style="margin: 1rem 0;">
        Requires <code>photos.tsv</code> and <code>keywords.tsv</code> mounted at the configured dataset directory (default: <code>/var/lib/pdl/datasets</code>). Processing is strictly throttled to prevent queue overflow and ensure 100% stable indexing.
      </p>

      <button class="btn btn-primary" @click="startImport" :disabled="status.includes('IMPORTING')">
        Start Dataset Import
      </button>
    </div>
  </div>
</template>

<style scoped>
/* Inherits global styles */
.meta-card { background: var(--bg-surface); padding: 2rem; border-radius: 8px; border: 1px solid var(--border-subtle); max-width: 600px; }
.highlight { color: var(--color-accent); font-weight: bold; }
</style>