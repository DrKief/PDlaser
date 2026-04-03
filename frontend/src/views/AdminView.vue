<script setup lang="ts">
import { ref, onMounted, onUnmounted } from "vue";
import http from "../api/http-client";
const status = ref("Loading...");
const limit = ref(50);
const offset = ref(0);
let pollingInterval: any = null;
const checkStatus = async () => {
  console.log("[AdminView] checkStatus triggered");
  try {
    const res = await http.get("/admin/unsplash/status");
    console.log("[AdminView] Status response:", res.data);
    status.value = res.data.status;
  } catch (e) {
    console.error("[AdminView] Error in checkStatus:", e);
    status.value = "Access Denied / Error";
  }
};
const startImport = async () => {
  console.log(
    "[AdminView] startImport triggered with limit:",
    limit.value,
    "offset:",
    offset.value,
  );
  try {
    const res = await http.post("/admin/unsplash/import", {
      limit: limit.value,
      offset: offset.value,
    });
    console.log("[AdminView] startImport response:", res);
    checkStatus();
  } catch (e: any) {
    console.error("[AdminView] Error in startImport:", e);
    alert(e.response?.data?.message || "Failed to start import");
  }
};
onMounted(() => {
  console.log("[AdminView] Component mounted. Starting polling...");
  checkStatus();
  pollingInterval = setInterval(checkStatus, 5000);
});
onUnmounted(() => {
  console.log("[AdminView] Component unmounted. Clearing polling interval...");
  clearInterval(pollingInterval);
});
</script>
<template>
  <div class="view-wrapper max-w-lg">
    <header class="page-header">
      <h1 class="page-title">Admin Operations</h1>
      <p class="page-subtitle">Dataset ingestion routines and queue limits.</p>
    </header>
    <div class="card meta-card">
      <h3 class="meta-title">Unsplash Dataset Puller</h3>
      <p class="label-text">
        System Status: <span class="highlight">{{ status }}</span>
      </p>
      <p class="help-text" style="margin: 1rem 0">
        Requires <code>photos.tsv</code> and <code>keywords.tsv</code> in
        <code>/var/lib/pdl/datasets</code>. Specify chunks to index securely without memory
        overload.
      </p>
      <div style="display: flex; gap: 1rem; margin-bottom: 1.5rem">
        <div class="form-group" style="flex: 1">
          <label class="label-text">Number of Images (Limit)</label>
          <input type="number" v-model="limit" min="1" max="1000" />
        </div>
        <div class="form-group" style="flex: 1">
          <label class="label-text">Skip First (Offset)</label>
          <input type="number" v-model="offset" min="0" />
        </div>
      </div>
      <button
        class="btn w-full"
        @click="startImport"
        :disabled="typeof status === 'string' && status.includes('IMPORTING')"
      >
        Start Background Import
      </button>
    </div>
  </div>
</template>
<style scoped>
.meta-card {
  background: var(--bg-surface);
  padding: 2rem;
  border-radius: 8px;
  border: 1px solid var(--border-subtle);
}

.highlight {
  color: var(--color-accent);
  font-weight: bold;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
}
</style>
