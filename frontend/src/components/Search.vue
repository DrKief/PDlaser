<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../http-api";
import { useImageStatus } from "../composables/useImageStatus";

interface Image { id: number; name: string; }
const route = useRoute();
const router = useRouter();

const activeTab = ref<"attributes" | "similarity">("attributes");
const { statusCache, fetchStatus, pollStatus } = useImageStatus();

const allImages = ref<Image[]>([]);
const searchName = ref("");
const searchFormat = ref("");
const searchSize = ref("");
const searchKeywords = ref<string[]>([]);

const selectedSourceImageId = ref<number | null>(null);
const similarityAlgorithm = ref("gradient");
const similarityCount = ref(10);

const attributeResults = ref<number[]>([]);
const similarityResults = ref<any[]>([]);
const errorMsg = ref("");

onMounted(() => {
  http.get("/images").then(r => allImages.value = r.data);
  
  // If navigated here with a source image ID, auto-setup similarity search
  if (route.query.sourceId) {
    activeTab.value = "similarity";
    selectedSourceImageId.value = parseInt(route.query.sourceId as string);
    performSimilaritySearch();
  }
});

const getImageUrl = (id: number) => `/images/${id}`;

const goToImage = (id: number) => {
  router.push(`/image/${id}`);
};

const performAttributeSearch = async () => {
  errorMsg.value = "";
  const params: any = {};
  if (searchName.value) params.name = searchName.value;
  if (searchFormat.value) params.format = searchFormat.value;
  if (searchSize.value) params.size = searchSize.value;
  if (searchKeywords.value.length) params.keywords = searchKeywords.value.join(",");

  if (Object.keys(params).length === 0) {
    errorMsg.value = "Please enter search criteria."; return;
  }
  try {
    const res = await http.get("/images/search", { params });
    attributeResults.value = res.data;
    attributeResults.value.forEach(id => {
      if (!statusCache[id] || statusCache[id] === "PENDING") {
        fetchStatus(id).then(s => { if (s !== "COMPLETE" && s !== "FAILED") pollStatus(id); });
      }
    });
  } catch (e: any) {
    errorMsg.value = e.response?.status === 404 ? "No matches found." : "Search failed.";
    attributeResults.value = [];
  }
};

const performSimilaritySearch = async () => {
  errorMsg.value = "";
  if (!selectedSourceImageId.value) { errorMsg.value = "Select a source image first."; return; }
  try {
    const res = await http.get(`/images/${selectedSourceImageId.value}/similar`, {
      params: { number: similarityCount.value, descriptor: similarityAlgorithm.value },
    });
    similarityResults.value = res.data;
  } catch (e) {
    errorMsg.value = "Similarity scan failed.";
    similarityResults.value = [];
  }
};
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">Search</h1>
      <p class="page-subtitle">Find images by metadata or discover visually similar assets.</p>
    </header>

    <div class="query-split">
      <!-- Left: Engine Config -->
      <section class="engine-config">
        <div class="tab-switcher">
          <button class="tab-btn" :class="{active: activeTab === 'attributes'}" @click="activeTab = 'attributes'">By Attributes</button>
          <button class="tab-btn" :class="{active: activeTab === 'similarity'}" @click="activeTab = 'similarity'">Reverse Image Search</button>
        </div>

        <div v-show="activeTab === 'attributes'" class="config-form">
          <div class="form-group">
            <label class="label-text">Filename Contains</label>
            <input v-model="searchName" placeholder="e.g. landscape" @keyup.enter="performAttributeSearch"/>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label class="label-text">Format</label>
              <input v-model="searchFormat" placeholder="jpeg, png" @keyup.enter="performAttributeSearch"/>
            </div>
            <div class="form-group">
              <label class="label-text">Resolution</label>
              <input v-model="searchSize" placeholder="800,600" @keyup.enter="performAttributeSearch"/>
            </div>
          </div>
          <button class="btn w-full mt-4" @click="performAttributeSearch">Search Attributes</button>
        </div>

        <div v-show="activeTab === 'similarity'" class="config-form">
          <div class="form-group">
            <label class="label-text">Source Image</label>
            <select v-model="selectedSourceImageId">
              <option :value="null">Select from database</option>
              <option v-for="img in allImages" :key="img.id" :value="img.id">{{ img.name }}</option>
            </select>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label class="label-text">Algorithm</label>
              <select v-model="similarityAlgorithm">
                <option value="gradient">HOG (Shape / Edges)</option>
                <option value="cielab">CIELAB (Human Vision)</option>
                <option value="rgb">RGB (Color Dist)</option>
                <option value="saturation">HSV (Intensity)</option>
              </select>
            </div>
            <div class="form-group">
              <label class="label-text">Result Limit</label>
              <input v-model.number="similarityCount" type="number" min="1" max="50" />
            </div>
          </div>
          <button class="btn w-full mt-4" @click="performSimilaritySearch">Find Similar</button>
        </div>

        <p v-if="errorMsg" class="error-text">{{ errorMsg }}</p>
      </section>

      <!-- Right: Results Matrix -->
      <section class="results-matrix">
        <h3 class="label-text matrix-title">
          Results 
          <span v-if="activeTab === 'attributes'">({{ attributeResults.length }})</span>
          <span v-else>({{ similarityResults.length }})</span>
        </h3>

        <!-- Attributes Results -->
        <div class="results-grid" v-if="activeTab === 'attributes'">
          <div v-for="id in attributeResults" :key="id" class="result-item" @click="goToImage(id)">
            <img :src="getImageUrl(id)" class="res-img" />
          </div>
        </div>

        <!-- Similarity Results -->
        <div class="results-grid" v-if="activeTab === 'similarity'">
          <div v-for="res in similarityResults" :key="res.id" class="result-item" @click="goToImage(res.id)">
            <img :src="getImageUrl(res.id)" class="res-img" />
            <div class="res-meta">
              <span class="label-text match-score">{{ (1 - (res.score || 0)).toFixed(2) }} Match</span>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<style scoped>
.view-wrapper { animation: fadeIn 0.4s ease-out; max-width: 1400px; margin: 0 auto; }
@keyframes fadeIn { from { opacity: 0; transform: translateY(10px); } to { opacity: 1; transform: translateY(0); } }

.page-header { margin-bottom: var(--space-xl); }
.page-title { font-size: 3rem; font-family: var(--font-headline); margin-bottom: 0.5rem; }
.page-subtitle { color: var(--text-secondary); font-size: 1rem; }

.query-split {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-xl);
}
@media (min-width: 1024px) {
  .query-split { grid-template-columns: 1fr 2fr; }
}

.engine-config {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 1.5rem;
  align-self: start;
}

.tab-switcher {
  display: flex;
  border-bottom: 1px solid var(--border-subtle);
  margin-bottom: var(--space-lg);
}
.tab-btn {
  flex: 1; background: none; border: none; padding: 1rem 0; font-family: var(--font-sans);
  font-weight: 500; font-size: 0.9rem; color: var(--text-secondary);
  cursor: pointer; position: relative; transition: color 0.2s;
}
.tab-btn:hover { color: var(--text-primary); }
.tab-btn.active { color: var(--text-primary); font-weight: 600; }
.tab-btn.active::after {
  content: ''; position: absolute; bottom: -1px; left: 0; width: 100%; height: 2px; background: var(--text-primary);
}

.config-form { display: flex; flex-direction: column; gap: var(--space-md); }
.form-group label { display: block; margin-bottom: 0.5rem; }
.form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 1rem; }
.w-full { width: 100%; }
.mt-4 { margin-top: 1rem; }

.error-text { color: var(--color-danger); font-size: 0.9rem; margin-top: 1rem; text-align: center; }

.matrix-title { border-bottom: 1px solid var(--border-subtle); padding-bottom: 1rem; margin-bottom: 1.5rem; }

.results-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1.5rem;
}

.result-item { 
  display: flex; flex-direction: column; gap: 0.5rem; cursor: pointer;
}
.res-img {
  width: 100%; aspect-ratio: 1; object-fit: cover;
  transition: transform 0.3s; background: var(--bg-element);
  border-radius: 4px;
}
.result-item:hover .res-img { transform: translateY(-4px); box-shadow: var(--shadow-subtle); }
.res-meta { display: flex; justify-content: flex-end; }
.match-score { color: var(--color-success); }

/* --- CRUELTY OVERRIDES --- */
:root.cruelty .page-title { font-family: 'Impact'; text-transform: uppercase; color: #00FF00; font-size: 5rem; }
:root.cruelty .engine-config { background: #000; border: 4px solid var(--color-accent); border-radius: 0; }
:root.cruelty .tab-btn { font-family: 'Impact'; font-size: 1.2rem; }
:root.cruelty .tab-btn.active { background: var(--color-accent); color: #000; }
:root.cruelty .res-img { filter: sepia(1) hue-rotate(180deg) saturate(300%); border: 4px solid #fff; border-radius: 0; }
</style>