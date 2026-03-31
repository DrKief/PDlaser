<script setup lang="ts">
import { ref, onMounted } from "vue";
import http from "../http-api";

const stats = ref({ total: 0, tags: 0 });

onMounted(async () => {
  const [imgRes, tagRes] = await Promise.all([
    http.get("/images"),
    http.get("/images/keywords")
  ]);
  stats.value.total = imgRes.data.length;
  stats.value.tags = tagRes.data.length;
});
</script>

<template>
  <div class="view-wrapper">
    <div class="about-container">
      <h1 class="page-title">PDLaser</h1>
      <p class="about-desc">
        A fast, vector-based visual archiving system. 
        Powered by pgvector and BoofCV, it enables deep similarity searching based on structure, color, and intensity.
      </p>
      
      <div class="stats-grid">
        <div class="stat-card">
          <span class="stat-val">{{ stats.total }}</span>
          <span class="label-text">Images Indexed</span>
        </div>
        <div class="stat-card">
          <span class="stat-val">{{ stats.tags }}</span>
          <span class="label-text">Unique Tags</span>
        </div>
        <div class="stat-card">
          <span class="stat-val">HNSW</span>
          <span class="label-text">Vector Indexing</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.view-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 70vh;
  animation: fadeIn 0.6s ease-out;
}
@keyframes fadeIn { from { opacity: 0; transform: translateY(10px); } to { opacity: 1; transform: translateY(0); } }

.about-container {
  max-width: 800px;
  text-align: center;
}

.page-title {
  font-size: clamp(4rem, 8vw, 6rem);
  font-family: var(--font-headline);
  margin-bottom: var(--space-md);
  color: var(--text-primary);
}

.about-desc {
  font-size: 1.25rem;
  line-height: 1.6;
  color: var(--text-secondary);
  margin-bottom: var(--space-xl);
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: var(--space-md);
}

.stat-card { 
  display: flex; 
  flex-direction: column; 
  gap: 0.5rem; 
  background: var(--bg-surface);
  padding: 2rem;
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
}
.stat-val { font-family: var(--font-sans); font-size: 3rem; font-weight: 300; color: var(--text-primary); }

/* --- CRUELTY OVERRIDES --- */
:root.cruelty .page-title {
  font-family: 'Impact';
  text-transform: uppercase;
  color: #FF00FF;
  font-size: 8rem;
}
:root.cruelty .stat-card {
  background: #000;
  border: 4px solid var(--color-accent);
  border-radius: 0;
  transform: rotate(-2deg);
}
:root.cruelty .stat-val { font-family: 'Impact'; color: #00FF00; }
</style>