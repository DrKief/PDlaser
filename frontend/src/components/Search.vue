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
    attributeError.value = "Please enter at least one criteria.";
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
      attributeResults.value = [];
    } else {
      attributeError.value = "Search failed due to a network or server error.";
      console.error(e);
    }
  }
};

const performSimilaritySearch = async () => {
  similarityError.value = "";
  similarityResults.value = [];
  hasSearchedSim.value = true;

  if (selectedSourceImageId.value === null) {
    similarityError.value = "Please select a reference image.";
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
    similarityError.value = "Similarity scan failed.";
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
    <h2>Search</h2>
    <p class="view-description">Find images by specific parameters or visual similarity.</p>
  </div>

  <div class="query-layout">
    <!-- Physical-style Segmented Control -->
    <div class="segmented-control" role="tablist">
      <button
        role="tab"
        :aria-selected="activeTab === 'attributes'"
        :class="['segment-btn', { active: activeTab === 'attributes' }]"
        @click="activeTab = 'attributes'"
      >
        By Attributes
      </button>
      <button
        role="tab"
        :aria-selected="activeTab === 'similarity'"
        :class="['segment-btn', { active: activeTab === 'similarity' }]"
        @click="activeTab = 'similarity'"
      >
        By Similarity
      </button>
    </div>

    <!-- ATTRIBUTE SEARCH PANEL -->
    <div v-show="activeTab === 'attributes'" class="panel query-panel">
      <div class="search-form">
        <div class="field-group">
          <label>Name contains</label>
          <input
            v-model="searchName"
            placeholder="e.g. landscape"
            @keyup.enter="performAttributeSearch"
          />
        </div>
        <div class="field-group">
          <label>File Format</label>
          <input
            v-model="searchFormat"
            placeholder="e.g. jpeg, png"
            @keyup.enter="performAttributeSearch"
          />
        </div>
        <div class="field-group">
          <label>Resolution</label>
          <input
            v-model="searchSize"
            placeholder="e.g. 800*600"
            @keyup.enter="performAttributeSearch"
          />
        </div>
        <div class="field-group">
          <label>Tags</label>
          <input
            v-model="searchKeywords"
            placeholder="e.g. nature, outdoors"
            @keyup.enter="performAttributeSearch"
          />
        </div>
      </div>

      <div class="action-row">
        <button @click="performAttributeSearch" class="btn btn-primary">Search Images</button>
      </div>

      <div v-if="attributeError" class="error-msg">{{ attributeError }}</div>
    </div>

    <!-- SIMILARITY SEARCH PANEL -->
    <div v-show="activeTab === 'similarity'" class="panel query-panel">
      <div class="search-form">
        <div class="field-group span-full">
          <label>Reference Image</label>
          <select v-model="selectedSourceImageId">
            <option :value="null">Choose reference image...</option>
            <option v-for="img in allImages" :key="img.id" :value="img.id">
              {{ img.name }} (ID: {{ img.id }})
            </option>
          </select>
          <div
            v-if="selectedSourceImageId !== null && statusCache?.[selectedSourceImageId]"
            class="source-status"
          >
            Source status:
            <span :class="['status-badge', statusCache[selectedSourceImageId]!.toLowerCase()]">{{
              statusCache[selectedSourceImageId]
            }}</span>
          </div>
        </div>

        <div class="field-group">
          <label>Algorithm</label>
          <select v-model="similarityAlgorithm">
            <option value="gradient">Gradient / Shape</option>
            <option value="saturation">Saturation Intensity</option>
            <option value="rgb">RGB Distribution</option>
            <option value="cielab">CIElab Distribution</option>
          </select>
        </div>

        <div class="field-group">
          <label>Results Limit</label>
          <input
            v-model.number="similarityCount"
            type="number"
            min="1"
            max="50"
            @keyup.enter="performSimilaritySearch"
          />
        </div>
      </div>

      <div class="action-row">
        <button @click="performSimilaritySearch" class="btn btn-primary">Find Similar</button>
      </div>

      <div v-if="similarityError" class="error-msg">{{ similarityError }}</div>
    </div>

    <!-- RESULTS AREA -->
    <div
      class="results-area"
      v-if="
        (activeTab === 'attributes' && hasSearchedAttr) ||
        (activeTab === 'similarity' && hasSearchedSim)
      "
    >
      <h3 class="results-header">Results</h3>

      <template v-if="activeTab === 'attributes'">
        <div v-if="attributeResults.length === 0 && !attributeError" class="empty-state">
          No matches found for the specified criteria.
        </div>
        <div v-else class="results-grid">
          <article v-for="id in attributeResults" :key="id" class="result-card panel">
            <div class="image-wrapper">
              <img :src="getImageUrl(id)" loading="lazy" />
            </div>
            <div class="result-meta">
              <span class="record-id">ID: {{ id }}</span>
              <div
                v-if="statusCache?.[id]"
                :class="['status-badge', statusCache[id]!.toLowerCase()]"
              >
                {{ statusCache[id] }}
              </div>
            </div>
          </article>
        </div>
      </template>

      <template v-if="activeTab === 'similarity'">
        <div v-if="similarityResults.length === 0 && !similarityError" class="empty-state">
          No similar images found.
        </div>
        <div v-else class="results-grid">
          <article v-for="(res, idx) in similarityResults" :key="idx" class="result-card panel">
            <div class="image-wrapper">
              <img :src="getImageUrl(res.id)" loading="lazy" />
            </div>
            <div class="result-meta">
              <div class="meta-row">
                <span class="record-id">ID: {{ res.id }}</span>
                <span class="score"
                  >Score:
                  {{ typeof res.score === "number" ? res.score.toFixed(3) : res.score }}</span
                >
              </div>
              <div
                v-if="statusCache?.[res.id]"
                :class="['status-badge', statusCache[res.id]!.toLowerCase()]"
              >
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
  font-size: 0.875rem;
}

.query-layout {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
  max-width: 900px;
}

/* Segmented Control (Braun switch style) */
.segmented-control {
  display: flex;
  background: var(--bg-element);
  padding: 4px;
  border-radius: var(--radius-pill);
  box-shadow: var(--shadow-inset);
  width: fit-content;
}

.segment-btn {
  flex: 1;
  background: transparent;
  color: var(--text-muted);
  padding: 0.5rem 1.5rem;
  border-radius: var(--radius-pill);
  font-size: 0.875rem;
  box-shadow: none;
}

.segment-btn:hover {
  background: transparent;
  color: var(--text-primary);
}

.segment-btn.active {
  background: var(--bg-surface);
  color: var(--text-primary);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.query-panel {
  padding: var(--space-xl);
}

.search-form {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-md);
  margin-bottom: var(--space-xl);
}

@media (min-width: 640px) {
  .search-form {
    grid-template-columns: 1fr 1fr;
  }
}

.span-full {
  grid-column: 1 / -1;
}

.source-status {
  margin-top: var(--space-sm);
  font-size: 0.75rem;
  color: var(--text-muted);
  display: flex;
  align-items: center;
  gap: 8px;
}

.action-row {
  display: flex;
  justify-content: flex-end;
}

.error-msg {
  color: var(--color-danger);
  font-size: 0.875rem;
  margin-top: var(--space-md);
}

.results-header {
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: var(--space-sm);
  margin-top: var(--space-2xl);
  font-size: 1.125rem;
}

.results-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: var(--space-md);
  margin-top: var(--space-lg);
}

.result-card {
  padding: var(--space-sm);
  display: flex;
  flex-direction: column;
}

.image-wrapper {
  aspect-ratio: 1;
  background: var(--bg-element);
  border-radius: var(--radius-sm);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: var(--space-xs);
  margin-bottom: var(--space-sm);
}

.image-wrapper img {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
  border-radius: 4px;
}

.result-meta {
  display: flex;
  flex-direction: column;
  gap: var(--space-xs);
  padding: 0 var(--space-xs);
}

.meta-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.record-id {
  font-size: 0.75rem;
  color: var(--text-secondary);
}

.score {
  font-size: 0.75rem;
  font-weight: 500;
  color: var(--color-accent);
}
</style>
