<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../http-api";
interface Image { id: number; name: string; }
const route = useRoute();
const router = useRouter();
const activeTab = ref<"attributes" | "similarity">("attributes");
const allImages = ref<Image[]>([]);
// Attribute Search State
const tagsInput = ref("");
const searchKeywords = ref<string[]>([]);
const filteredKeywords = ref<string[]>([]);
const isTagFocused = ref(false);
let debounceTimer: ReturnType<typeof setTimeout> | null = null;
// Similarity State
const selectedSourceImageId = ref<number | null>(null);
const similarityAlgorithm = ref("gradient");
const similarityCount = ref(10);
const similarityResults = ref<any[]>([]);
const errorMsg = ref("");
onMounted(() => {
  http.get(`/images`).then(r => {
    // Only grab images that exist in the system for the similarity dropdown
    allImages.value = r.data.map((img:any) => ({id: img.id, name: `Image #${img.id}`}));
  });
  if (route.query.sourceId) {
    activeTab.value = "similarity";
    selectedSourceImageId.value = parseInt(route.query.sourceId as string);
  }
});
const getImageUrl = (id: number) => `/images/${id}`;
const goToImage = (id: number) => router.push(`/image/${id}`);
const handleTagBlur = () => setTimeout(() => isTagFocused.value = false, 200);
const onTagInput = () => {
  if (debounceTimer) clearTimeout(debounceTimer);
  debounceTimer = setTimeout(async () => {
    if (tagsInput.value.length < 2) {
      filteredKeywords.value = [];
      return;
    }
    try {
      const res = await http.get(`/images/keywords/search?q=${tagsInput.value}`);
      filteredKeywords.value = res.data.filter((k: string) => !searchKeywords.value.includes(k));
    } catch (e) {
      filteredKeywords.value = [];
    }
  }, 300);
};
const addTag = (tag?: string) => {
  const t = tag || tagsInput.value.trim().toLowerCase();
  if (t && !searchKeywords.value.includes(t)) {
    searchKeywords.value.push(t);
  }
  tagsInput.value = "";
  filteredKeywords.value = [];
  isTagFocused.value = false;
};
const removeTag = (tag: string) => {
  searchKeywords.value = searchKeywords.value.filter(t => t !== tag);
};
const performAttributeSearch = () => {
  errorMsg.value = "";
  if (!searchKeywords.value.length) {
    errorMsg.value = "Please enter tag criteria.";
    return;
  }
  // Redirect to Gallery which natively handles paginated tag search
  router.push({ path: '/', query: { tags: searchKeywords.value.join(',') } });
};
const performSimilaritySearch = async () => {
  errorMsg.value = "";
  if (!selectedSourceImageId.value) {
    errorMsg.value = "Select a source image first.";
    return;
  }
  try {
    const res = await http.get(`/images/${selectedSourceImageId.value}/similar`, {
      params: { number: similarityCount.value, descriptor: similarityAlgorithm.value }
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
      <h1 class="page-title">Advanced Search</h1>
      <p class="page-subtitle">Filter by tags or utilize vector-based visual similarity.</p>
    </header>
    <div class="query-split">
      <section class="card engine-config">
        <div class="tab-switcher">
          <button class="tab-btn" :class="{active: activeTab === 'attributes'}" @click="activeTab = 'attributes'">By Tags</button>
          <button class="tab-btn" :class="{active: activeTab === 'similarity'}" @click="activeTab = 'similarity'">Visual Match</button>
        </div>
        <!-- Attributes Config -->
        <div v-show="activeTab === 'attributes'" class="config-form">
          <div class="form-group relative">
            <label class="label-text">Must Contain Tags (AND condition)</label>
            <div class="tags-container">
              <span v-for="tag in searchKeywords" :key="tag" class="tag-pill">
                {{ tag }} <button @click="removeTag(tag)" class="tag-remove">&times;</button>
              </span>
              <input 
                v-model="tagsInput" 
                class="tag-input" 
                placeholder="Search tags..."
                @input="onTagInput"
                @focus="isTagFocused = true"
                @blur="handleTagBlur"
                @keydown.enter="filteredKeywords.length ? addTag(filteredKeywords[0]) : addTag()"
              />
            </div>
            <ul class="autocomplete-dropdown" v-if="isTagFocused && filteredKeywords.length > 0">
              <li v-for="kw in filteredKeywords" :key="kw" @mousedown.prevent="addTag(kw)">
                {{ kw }}
              </li>
            </ul>
          </div>
          <button class="btn w-full mt-4" @click="performAttributeSearch">Search via Gallery</button>
        </div>
        <!-- Similarity Config -->
        <div v-show="activeTab === 'similarity'" class="config-form">
          <div class="form-group">
            <label class="label-text">Source Image</label>
            <select v-model="selectedSourceImageId">
              <option :value="null">Select from database...</option>
              <option v-for="img in allImages" :key="img.id" :value="img.id">{{ img.name }}</option>
            </select>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label class="label-text">Algorithm</label>
              <select v-model="similarityAlgorithm">
                <option value="gradient">HOG (Shape/Edges)</option>
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
      <!-- Right: Similarity Results Matrix -->
      <section class="results-matrix" v-if="activeTab === 'similarity' && similarityResults.length">
        <h3 class="label-text matrix-title">Results ({{ similarityResults.length }})</h3>
        <div class="results-grid">
          <div v-for="res in similarityResults" :key="res.id" class="result-item" @click="goToImage(res.id)">
            <img :src="getImageUrl(res.id)" class="res-img" />
            <div class="res-meta">
              <span class="label-text match-score">{{ ((1 - res.score) * 100).toFixed(2) }}% Match</span>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>
<style scoped>
/* Inherits exact styles from earlier, omitting bulk for brevity */
.query-split { display: grid; grid-template-columns: 1fr; gap: var(--space-xl); }
@media (min-width: 1024px) { .query-split { grid-template-columns: 1fr 2fr; } }
.tab-switcher { display: flex; border-bottom: 1px solid var(--border-subtle); margin-bottom: var(--space-lg); }
.tab-btn { flex: 1; background: none; border: none; padding: 1rem 0; font-family: var(--font-sans); font-weight: 500; font-size: 0.9rem; color: var(--text-secondary); cursor: pointer; position: relative; transition: color 0.2s; }
.tab-btn:hover { color: var(--text-primary); }
.tab-btn.active { color: var(--text-primary); font-weight: 600; }
.tab-btn.active::after { content: ''; position: absolute; bottom: -1px; left: 0; width: 100%; height: 2px; background: var(--text-primary); }
.config-form { display: flex; flex-direction: column; gap: var(--space-md); }
.form-group label { display: block; margin-bottom: 0.5rem; }
.form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 1rem; }
.matrix-title { border-bottom: 1px solid var(--border-subtle); padding-bottom: 1rem; margin-bottom: 1.5rem; }
.results-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 1.5rem; }
.result-item { display: flex; flex-direction: column; gap: 0.5rem; cursor: pointer; }
.res-img { width: 100%; aspect-ratio: 1; object-fit: cover; transition: transform 0.3s; background: var(--bg-element); border-radius: 4px; }
.result-item:hover .res-img { transform: translateY(-4px); box-shadow: var(--shadow-subtle); }
.res-meta { display: flex; justify-content: flex-end; }
.match-score { color: var(--color-success); }
:root.cruelty .tab-btn { font-family: 'Impact'; font-size: 1.2rem; }
:root.cruelty .tab-btn.active { background: var(--color-accent); color: #000; }
:root.cruelty .res-img { filter: sepia(1) hue-rotate(180deg) saturate(300%); border: 4px solid #fff; border-radius: 0; }
</style>