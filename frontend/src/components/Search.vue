<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../http-api";
import { useImageStatus } from "../composables/useImageStatus";

interface Image { id: number; name: string; }

const route = useRoute();
const router = useRouter();

const activeTab = ref<"attributes" | "similarity">("attributes");
const { statusCache, fetchStatus, pollStatus } = useImageStatus();

const allImages = ref<Image[]>([]);
const allKeywords = ref<string[]>([]);

// Attribute Search State
const searchName = ref("");
const searchFormat = ref("");
const searchSize = ref("");

const tagsInput = ref("");
const searchKeywords = ref<string[]>([]);
const isTagFocused = ref(false);

const filteredKeywords = computed(() => {
  if (!tagsInput.value) return [];
  const query = tagsInput.value.toLowerCase();
  return allKeywords.value
    .filter(k => k.toLowerCase().includes(query) && !searchKeywords.value.includes(k))
    .slice(0, 6);
});

// Similarity State
const selectedSourceImageId = ref<number | null>(null);
const similarityAlgorithm = ref("gradient");
const similarityCount = ref(10);

const attributeResults = ref<number[]>([]);
const similarityResults = ref<any[]>([]);
const errorMsg = ref("");

onMounted(() => {
  http.get(`/images`).then(r => allImages.value = r.data);
  http.get(`/images/keywords`).then(r => allKeywords.value = r.data);
  
  if (route.query.sourceId) {
    activeTab.value = "similarity";
    selectedSourceImageId.value = parseInt(route.query.sourceId as string);
    // performSimilaritySearch();
  }
});

const getImageUrl = (id: number) => {
  return `/images/${id}`;
};

const goToImage = (id: number) => {
  router.push(`/image/${id}`);
};

const handleTagBlur = () => {
  setTimeout(() => isTagFocused.value = false, 200);
};

const addTag = (tag?: string) => {
  const t = tag || tagsInput.value.trim().toLowerCase();
  if (t && !searchKeywords.value.includes(t)) {
    searchKeywords.value.push(t);
  }
  tagsInput.value = "";
  isTagFocused.value = false;
};

const removeTag = (tag: string) => {
  searchKeywords.value = searchKeywords.value.filter(t => t !== tag);
};

const performAttributeSearch = async () => {
  errorMsg.value = "";
  const params: any = {};
  
  if (searchName.value) params.name = searchName.value;
  if (searchFormat.value) params.format = searchFormat.value;
  if (searchSize.value) params.size = searchSize.value;
  if (searchKeywords.value.length) params.keywords = searchKeywords.value.join(',');

  if (Object.keys(params).length === 0) {
    errorMsg.value = "Please enter search criteria.";
    return;
  }

  try {
    const res = await http.get(`/images/search`, { params });
    attributeResults.value = res.data;
    
    // Check status for each result
    attributeResults.value.forEach(id => {
      if (!statusCache[id] || statusCache[id] === 'PENDING') {
        fetchStatus(id).then(s => {
          if (s !== 'COMPLETED' && s !== 'FAILED') {
            pollStatus(id);
          }
        });
      }
    });

  } catch (e: any) {
    errorMsg.value = e.response?.status === 404 ? "No matches found." : "Search failed.";
    attributeResults.value = [];
  }
};

const performSimilaritySearch = async () => {
  errorMsg.value = "";
  if (!selectedSourceImageId.value) {
    errorMsg.value = "Select a source image first.";
    return;
  }

  try {
    const res = await http.get(`/images/${selectedSourceImageId.value}/similar`, {
      params: { 
        number: similarityCount.value, 
        descriptor: similarityAlgorithm.value 
      }
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
      <p class="page-subtitle">Combine multiple metadata points or utilize vector-based visual similarity.</p>
    </header>

    <div class="query-split">
      <section class="engine-config">
        
        <div class="tab-switcher">
          <button class="tab-btn" :class="{active: activeTab === 'attributes'}" @click="activeTab = 'attributes'">By Attributes</button>
          <button class="tab-btn" :class="{active: activeTab === 'similarity'}" @click="activeTab = 'similarity'">Reverse Image</button>
        </div>

        <!-- Attributes Config -->
        <div v-show="activeTab === 'attributes'" class="config-form">
          <div class="form-group relative">
            <label class="label-text">Tags</label>
            <div class="tags-container">
              <span v-for="tag in searchKeywords" :key="tag" class="tag-pill">
                {{ tag }} <button @click="removeTag(tag)" class="tag-remove">&times;</button>
              </span>
              <input 
                v-model="tagsInput" 
                class="tag-input" 
                placeholder="Add tags..."
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

          <div class="form-group">
            <label class="label-text">Filename Contains</label>
            <input v-model="searchName" placeholder="e.g. landscape" @keyup.enter="performAttributeSearch" />
          </div>

          <div class="form-row">
            <div class="form-group">
              <label class="label-text">Format</label>
              <input v-model="searchFormat" placeholder="jpeg, png" @keyup.enter="performAttributeSearch" />
            </div>
            <div class="form-group">
              <label class="label-text">Resolution</label>
              <input v-model="searchSize" placeholder="800x600" @keyup.enter="performAttributeSearch" />
            </div>
          </div>

          <button class="btn w-full mt-4" @click="performAttributeSearch">Search Attributes</button>
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

      <!-- Right: Results Matrix -->
      <section class="results-matrix">
        <h3 class="label-text matrix-title">
          Results 
          <span v-if="activeTab === 'attributes'">({{ attributeResults.length }})</span>
          <span v-else>({{ similarityResults.length }})</span>
        </h3>
        
        <div class="results-grid" v-if="activeTab === 'attributes'">
          <div v-for="id in attributeResults" :key="id" class="result-item" @click="goToImage(id)">
            <img :src="getImageUrl(id)" class="res-img" />
          </div>
        </div>

        <div class="results-grid" v-if="activeTab === 'similarity'">
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
.view-wrapper {
  animation: fadeIn 0.4s ease-out;
  max-width: 1400px;
  margin: 0 auto;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(10px); }
  to { opacity: 1; transform: translateY(0); }
}

.page-header { margin-bottom: var(--space-xl); }
.page-title {
  font-size: 3rem;
  font-family: var(--font-headline);
  margin-bottom: 0.5rem;
}
.page-subtitle {
  color: var(--text-secondary);
  font-size: 1rem;
}

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
  flex: 1;
  background: none;
  border: none;
  padding: 1rem 0;
  font-family: var(--font-sans);
  font-weight: 500;
  font-size: 0.9rem;
  color: var(--text-secondary);
  cursor: pointer;
  position: relative;
  transition: color 0.2s;
}

.tab-btn:hover { color: var(--text-primary); }
.tab-btn.active { color: var(--text-primary); font-weight: 600; }
.tab-btn.active::after {
  content: '';
  position: absolute;
  bottom: -1px;
  left: 0;
  width: 100%;
  height: 2px;
  background: var(--text-primary);
}

.config-form {
  display: flex;
  flex-direction: column;
  gap: var(--space-md);
}

.form-group label { display: block; margin-bottom: 0.5rem; }
.form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 1rem; }
.w-full { width: 100%; }
.mt-4 { margin-top: 1rem; }

/* Tag Input Styles */
.relative { position: relative; }
.tags-container {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  padding: 0.5rem;
  border-bottom: 1px solid var(--border-subtle);
  transition: border-color 0.2s;
}

.tags-container:focus-within { border-color: var(--border-strong); }

.tag-pill {
  background: var(--bg-element);
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  font-size: 0.8rem;
  display: flex;
  align-items: center;
  gap: 0.25rem;
}

.tag-remove {
  background: none;
  border: none;
  cursor: pointer;
  color: var(--text-secondary);
  padding: 0 2px;
}
.tag-remove:hover { color: var(--color-danger); }

.tag-input {
  border: none;
  background: transparent;
  flex: 1;
  min-width: 100px;
  padding: 0;
  margin: 0;
  outline: none;
  font-size: 0.9rem;
}

.tag-input:focus { border-bottom: none; }

.autocomplete-dropdown {
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 4px;
  box-shadow: var(--shadow-subtle);
  list-style: none;
  padding: 0.5rem 0;
  margin: 0;
  z-index: 100;
}

.autocomplete-dropdown li {
  padding: 0.5rem 1rem;
  font-size: 0.9rem;
  cursor: pointer;
}

.autocomplete-dropdown li:hover { background: var(--bg-element); }

.error-text {
  color: var(--color-danger);
  font-size: 0.9rem;
  margin-top: 1rem;
  text-align: center;
}

.matrix-title {
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1rem;
  margin-bottom: 1.5rem;
}

.results-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1.5rem;
}

.result-item {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  cursor: pointer;
}

.res-img {
  width: 100%;
  aspect-ratio: 1;
  object-fit: cover;
  transition: transform 0.3s;
  background: var(--bg-element);
  border-radius: 4px;
}

.result-item:hover .res-img {
  transform: translateY(-4px);
  box-shadow: var(--shadow-subtle);
}

.res-meta { display: flex; justify-content: flex-end; }
.match-score { color: var(--color-success); }

/* --- CRUELTY OVERRIDES --- */
:root.cruelty .page-title { font-family: 'Impact'; text-transform: uppercase; color: #00FF00; font-size: 5rem; }
:root.cruelty .engine-config { background: #000; border: 4px solid var(--color-accent); border-radius: 0; }
:root.cruelty .tab-btn { font-family: 'Impact'; font-size: 1.2rem; }
:root.cruelty .tab-btn.active { background: var(--color-accent); color: #000; }
:root.cruelty .res-img { filter: sepia(1) hue-rotate(180deg) saturate(300%); border: 4px solid #fff; border-radius: 0; }
:root.cruelty .autocomplete-dropdown { background: #000; border: 4px solid #fff; border-radius: 0; }
:root.cruelty .autocomplete-dropdown li { font-family: 'Impact'; color: #00FF00; }
:root.cruelty .autocomplete-dropdown li:hover { background: #FF00FF; color: #fff; }
</style>