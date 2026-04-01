<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';
import http from '../http-api';

const status = ref("Loading...");
const limit = ref(50);
const offset = ref(0);
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
    await http.post('/admin/unsplash/import', { limit: limit.value, offset: offset.value });
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
      <p class="page-subtitle">Dataset ingestion routines and queue limits.</p>
    </header>

    <div class="meta-card">
      <h3 class="meta-title">Unsplash Dataset Puller</h3>
      <p class="label-text">System Status: <span class="highlight">{{ status }}</span></p>
      
      <p class="help-text" style="margin: 1rem 0;">
        Requires <code>photos.tsv</code> and <code>keywords.tsv</code> in <code>/var/lib/pdl/datasets</code>.
        Specify chunks to index securely without memory overload.
      </p>

      <div style="display:flex; gap: 1rem; margin-bottom: 1.5rem;">
        <div class="form-group" style="flex:1;">
          <label class="label-text">Number of Images (Limit)</label>
          <input type="number" v-model="limit" min="1" max="1000" />
        </div>
        <div class="form-group" style="flex:1;">
          <label class="label-text">Skip First (Offset)</label>
          <input type="number" v-model="offset" min="0" />
        </div>
      </div>

      <button class="btn w-full" @click="startImport" :disabled="status.includes('IMPORTING')">
        Start Background Import
      </button>
    </div>
  </div>
</template>

<style scoped>
.view-wrapper { max-width: 800px; margin: 0 auto; animation: fadeIn 0.4s ease-out; }
@keyframes fadeIn { from { opacity: 0; transform: translateY(10px); } to { opacity: 1; transform: translateY(0); } }
.page-title { font-family: var(--font-headline); font-size: 3rem; margin-bottom: 0.5rem; }
.page-subtitle { color: var(--text-secondary); margin-bottom: 2rem; }
.meta-card { background: var(--bg-surface); padding: 2rem; border-radius: 8px; border: 1px solid var(--border-subtle); }
.highlight { color: var(--color-accent); font-weight: bold; }
.w-full { width: 100%; }
.form-group label { display: block; margin-bottom: 0.5rem; }
</style>