<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import http from "../http-api";
import { useImageStatus } from "../composables/useImageStatus";

interface Image {
  id: number;
  name: string;
}

const activeTab = ref<"attributes" | "similarity">("attributes");
const { statusCache, fetchStatus, pollStatus } = useImageStatus();

// --- Attribute Search State ---
const searchName = ref("");
const searchFormat = ref("");
const searchSize = ref("");
const searchKeywords = ref("");
const attributeResults = ref<number[]>([]);
const attributeError = ref("");
const hasSearchedAttr = ref(false);

// --- Similarity Search State ---
const allImages = ref<Image[]>([]);
const selectedSourceImageId = ref<number | null>(null);
const similarityAlgorithm = ref("gradient");
const similarityCount = ref(10);
const similarityResults = ref<any[]>([]);
const similarityError = ref("");
const hasSearchedSim = ref(false);

const fetchAllImages = async () => {
  try {
    const response = await http.get("/images");
    allImages.value = response.data;
  } catch (e) {
    console.error("Failed to fetch images list", e);
  }
};

const getImageUrl = (id: number) => `/images/${id}`;

const performAttributeSearch = async () => {
  attributeError.value = "";
  attributeResults.value = [];
  hasSearchedAttr.value = true;

  const params: any = {};
  if (searchName.value) params.name = searchName.value;
  if (searchFormat.value) params.format = searchFormat.value;
  if (searchSize.value) params.size = searchSize.value;
  if (searchKeywords.value) params.keywords = searchKeywords.value;

  if (Object.keys(params).length === 0) {
    attributeError.value = "ERR: MISSING_PARAMETERS";
    return;
  }

  try {
    const response = await http.get("/images/search", { params });
    attributeResults.value = response.data;

    attributeResults.value.forEach((id) => {
      if (!statusCache[id] || statusCache[id] === "PENDING") {
        fetchStatus(id).then((status) => {
          if (status && status !== "COMPLETE" && status !== "FAILED") {
            pollStatus(id);
          }
        });
      }
    });
  } catch (e: any) {
    if (e.response && e.response.status === 404) {
      // Not strictly an error in UX, just 0 results
      attributeResults.value = [];
    } else {
      attributeError.value = "ERR: QUERY_FAILED";
      console.error(e);
    }
  }
};

const performSimilaritySearch = async () => {
  similarityError.value = "";
  similarityResults.value = [];
  hasSearchedSim.value = true;

  if (selectedSourceImageId.value === null) {
    similarityError.value = "ERR: NO_SOURCE_SELECTED";
    return;
  }

  try {
    const response = await http.get(`/images/${selectedSourceImageId.value}/similar`, {
      params: {
        number: similarityCount.value,
        descriptor: similarityAlgorithm.value,
      },
    });
    similarityResults.value = response.data;

    similarityResults.value.forEach((res) => {
      const id = res.id;
      if (!statusCache[id] || statusCache[id] === "PENDING") {
        fetchStatus(id).then((status) => {
          if (status && status !== "COMPLETE" && status !== "FAILED") {
            pollStatus(id);
          }
        });
      }
    });
  } catch (e: any) {
    similarityError.value = "ERR: SIMILARITY_SCAN_FAILED";
    console.error(e);
  }
};

onMounted(() => {
  fetchAllImages();
});

watch(selectedSourceImageId, (newId) => {
  if (newId !== null) {
    if (!statusCache[newId] || statusCache[newId] === "PENDING") {
      fetchStatus(newId).then((status) => {
        if (status && status !== "COMPLETE" && status !== "FAILED") {
          pollStatus(newId);
        }
      });
    }
  }
});
</script>

<template>
  <div class="view-header">
    <h2>Query Interface</h2>
    <p class="view-description">Filter database by specific parameters or visual algorithms.</p>
  </div>

  <div class="query-layout">
    
    <!-- Segmented Control for Tabs -->
    <div class="segmented-control" role="tablist">
      <button 
        role="tab" 
        :aria-selected="activeTab === 'attributes'"
        :class="['segment-btn', { active: activeTab === 'attributes' }]" 
        @click="activeTab = 'attributes'"
      >
        PARAMETRIC_FILTER
      </button>
      <button 
        role="tab"
        :aria-selected="activeTab === 'similarity'"
        :class="['segment-btn', { active: activeTab === 'similarity' }]" 
        @click="activeTab = 'similarity'"
      >
        ALGORITHMIC_MATCH
      </button>
    </div>

    <!-- ATTRIBUTE SEARCH PANEL -->
    <div v-show="activeTab === 'attributes'" class="panel query-panel">
      <div class="search-form">
        <div class="field-group">
          <label>Identifier (Contains)</label>
          <input v-model="searchName" placeholder="e.g. subject_alpha" @keyup.enter="performAttributeSearch" />
        </div>
        <div class="field-group">
          <label>File Format</label>
          <input v-model="searchFormat" placeholder="e.g. jpeg, png" @keyup.enter="performAttributeSearch"/>
        </div>
        <div class="field-group">
          <label>Resolution</label>
          <input v-model="searchSize" placeholder="e.g. 800*600" @keyup.enter="performAttributeSearch" />
        </div>
        <div class="field-group">
          <label>Assigned Tags</label>
          <input v-model="searchKeywords" placeholder="e.g. confidential, verified" @keyup.enter="performAttributeSearch"/>
        </div>
      </div>

      <button @click="performAttributeSearch" class="btn btn-primary full-width mt-lg">
        EXECUTE FILTER
      </button>

      <div v-if="attributeError" class="system-error mt-md">> {{ attributeError }}</div>
    </div>

    <!-- SIMILARITY SEARCH PANEL -->
    <div v-show="activeTab === 'similarity'" class="panel query-panel">
      <div class="search-form">
        <div class="field-group span-full">
          <label>Reference Source</label>
          <select v-model="selectedSourceImageId">
            <option :value="null">-- SELECT REFERENCE TARGET --</option>
            <option v-for="img in allImages" :key="img.id" :value="img.id">
              REC_{{ String(img.id).padStart(4, '0') }} : {{ img.name }}
            </option>
          </select>
          <div
            v-if="selectedSourceImageId !== null && statusCache?.[selectedSourceImageId]"
            :class="['status-badge', 'mt-sm', statusCache[selectedSourceImageId]!.toLowerCase()]"
          >
            SRC_STATE: {{ statusCache[selectedSourceImageId] }}
          </div>
        </div>

        <div class="field-group">
          <label>Heuristic Vector</label>
          <select v-model="similarityAlgorithm">
            <option value="gradient">SHAPE (GRADIENT_ORIENT)</option>
            <option value="saturation">INTENSITY (SATURATION)</option>
            <option value="rgb">SPECTRUM (RGB_DISTRIB)</option>
          </select>
        </div>

        <div class="field-group">
          <label>Limit Returns</label>
          <input v-model.number="similarityCount" type="number" min="1" max="50" @keyup.enter="performSimilaritySearch"/>
        </div>
      </div>

      <button @click="performSimilaritySearch" class="btn btn-primary full-width mt-lg">
        INITIATE DEEP SCAN
      </button>

      <div v-if="similarityError" class="system-error mt-md">> {{ similarityError }}</div>
    </div>

    <!-- RESULTS AREA -->
    <div class="results-area" v-if="(activeTab === 'attributes' && hasSearchedAttr) || (activeTab === 'similarity' && hasSearchedSim)">
      <h3 class="results-header">Output Buffer</h3>
      
      <!-- Attribute Results -->
      <template v-if="activeTab === 'attributes'">
        <div v-if="attributeResults.length === 0 && !attributeError" class="empty-state">
          No records match specified parameters.
        </div>
        <div v-else class="results-grid">
          <article v-for="id in attributeResults" :key="id" class="result-card">
            <div class="image-wrapper">
              <img :src="getImageUrl(id)" loading="lazy" />
            </div>
            <div class="result-meta">
              <span class="record-id">REC_{{ String(id).padStart(4, '0') }}</span>
              <div v-if="statusCache?.[id]" :class="['status-badge', statusCache[id]!.toLowerCase()]">
                {{ statusCache[id] }}
              </div>
            </div>
          </article>
        </div>
      </template>

      <!-- Similarity Results -->
      <template v-if="activeTab === 'similarity'">
        <div v-if="similarityResults.length === 0 && !similarityError" class="empty-state">
          Scan complete. Zero matches found.
        </div>
        <div v-else class="results-grid">
          <article v-for="(res, idx) in similarityResults" :key="idx" class="result-card">
            <div class="image-wrapper">
              <img :src="getImageUrl(res.id)" loading="lazy" />
            </div>
            <div class="result-meta">
              <div class="meta-row">
                <span class="record-id">REC_{{ String(res.id).padStart(4, '0') }}</span>
                <span class="score">DEV: {{ typeof res.score === "number" ? res.score.toFixed(4) : res.score }}</span>
              </div>
              <div v-if="statusCache?.[res.id]" :class="['status-badge', statusCache[res.id]!.toLowerCase()]">
                {{ statusCache[res.id] }}
              </div>
            </div>
          </article>
        </div>
      </template>

    </div>
  </div>
</template>

<style scoped>
.view-header {
  margin-bottom: var(--space-xl);
}

.view-description {
  color: var(--text-secondary);
  font-family: var(--font-mono);
  font-size: 0.875rem;
  margin-top: -0.5rem;
}

.query-layout {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
  max-width: 900px;
}

/* Segmented Control */
.segmented-control {
  display: flex;
  background: var(--bg-tertiary);
  padding: 4px;
  border-radius: var(--radius-md);
  border: 1px solid var(--border-color);
}

.segment-btn {
  flex: 1;
  background: transparent;
  border: none;
  box-shadow: none;
  color: var(--text-muted);
  padding: 0.75rem;
}

.segment-btn:hover {
  color: var(--text-primary);
  background: transparent;
  box-shadow: none;
  transform: none;
}

.segment-btn.active {
  background: var(--bg-primary);
  color: var(--color-primary);
  border: 1px solid var(--border-color);
  box-shadow: 2px 2px 0 var(--border-color);
}

.query-panel {
  padding: var(--space-xl);
}

.search-form {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-md);
}

@media (min-width: 640px) {
  .search-form {
    grid-template-columns: 1fr 1fr;
  }
}

.span-full {
  grid-column: 1 / -1;
}

.system-error {
  color: var(--color-danger);
  font-family: var(--font-mono);
  font-size: 0.875rem;
  padding: var(--space-sm);
  background: color-mix(in oklch, var(--color-danger) 10%, transparent);
  border: 1px dashed var(--color-danger);
}

.results-header {
  border-bottom: 2px solid var(--border-color);
  padding-bottom: var(--space-sm);
  margin-top: var(--space-2xl);
  font-family: var(--font-mono);
  text-transform: uppercase;
}

.results-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: var(--space-md);
  margin-top: var(--space-lg);
}

.result-card {
  border: 1px solid var(--border-color);
  background: var(--bg-secondary);
  display: flex;
  flex-direction: column;
  transition: transform 0.2s var(--ease-out-expo);
}

.result-card:hover {
  border-color: var(--color-primary);
}

.image-wrapper {
  aspect-ratio: 1;
  background: var(--bg-primary);
  border-bottom: 1px solid var(--border-color);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: var(--space-xs);
}

.image-wrapper img {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

.result-meta {
  padding: var(--space-sm);
  display: flex;
  flex-direction: column;
  gap: var(--space-xs);
}

.meta-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.record-id, .score {
  font-family: var(--font-mono);
  font-size: 0.75rem;
  color: var(--text-primary);
}

.score {
  color: var(--color-primary);
}

/* Utilities */
.full-width { width: 100%; }
.mt-sm { margin-top: var(--space-sm); }
.mt-md { margin-top: var(--space-md); }
.mt-lg { margin-top: var(--space-lg); }
</style>