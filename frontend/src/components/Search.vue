<script setup lang="ts">
import { ref, onMounted } from "vue";
import http from "../http-api";

interface Image {
  id: number;
  name: string;
}

const activeTab = ref<"attributes" | "similarity">("attributes");

// --- Attribute Search State ---
const searchName = ref("");
const searchFormat = ref("");
const searchSize = ref("");
const searchKeywords = ref(""); // comma separated
const attributeResults = ref<number[]>([]);
const attributeError = ref("");

// --- Similarity Search State ---
const allImages = ref<Image[]>([]);
const selectedSourceImageId = ref<number | null>(null);
// Set default to 'gradient' as in Controller
const similarityAlgorithm = ref("gradient");
// Set default min to 10 as in Controller
const similarityCount = ref(10);
const similarityResults = ref<any[]>([]); // API returns objects list
const similarityError = ref("");

// --- Helpers ---
const fetchAllImages = async () => {
  try {
    const response = await http.get("/images");
    allImages.value = response.data;
  } catch (e) {
    console.error("Failed to fetch images list", e);
  }
};

const getImageUrl = (id: number) => `/images/${id}`;

// --- Actions ---

const performAttributeSearch = async () => {
  attributeError.value = "";
  attributeResults.value = [];

  const params: any = {};
  if (searchName.value) params.name = searchName.value;
  if (searchFormat.value) params.format = searchFormat.value;
  if (searchSize.value) params.size = searchSize.value;
  if (searchKeywords.value) {
    // Splitting manually to ensure proper list format if needed,
    // though Spring often handles comma-separated strings for lists.
    // Let's pass it as a comma-separated string first.
    params.keywords = searchKeywords.value;
  }

  // Don't search if empty
  if (Object.keys(params).length === 0) {
    attributeError.value = "Please enter at least one search criteria.";
    return;
  }

  try {
    const response = await http.get("/images/search", { params });
    // Response is List<Long> ids
    attributeResults.value = response.data;
  } catch (e: any) {
    if (e.response && e.response.status === 404) {
      attributeError.value = "No images found matching criteria.";
    } else {
      attributeError.value = "Search failed.";
      console.error(e);
    }
  }
};

const performSimilaritySearch = async () => {
  similarityError.value = "";
  similarityResults.value = [];

  if (selectedSourceImageId.value === null) {
    similarityError.value = "Please select a source image.";
    return;
  }

  try {
    const response = await http.get(`/images/${selectedSourceImageId.value}/similar`, {
      params: {
        number: similarityCount.value,
        descriptor: similarityAlgorithm.value,
      },
    });
    // Response is List<Map<String, Object>> e.g. [{id: 1, score: 0.5}, ...]
    similarityResults.value = response.data;
  } catch (e: any) {
    similarityError.value = "Similarity search failed.";
    console.error(e);
  }
};

onMounted(() => {
  fetchAllImages();
});
</script>

<template>
  <div class="search-container">
    <h2>Search Database</h2>

    <div class="tabs">
      <button :class="{ active: activeTab === 'attributes' }" @click="activeTab = 'attributes'">
        by Attributes
      </button>
      <button :class="{ active: activeTab === 'similarity' }" @click="activeTab = 'similarity'">
        by Similarity
      </button>
    </div>

    <!-- ATTRIBUTE SEARCH PANEL -->
    <div v-if="activeTab === 'attributes'" class="panel">
      <div class="search-grid">
        <div class="field-group">
          <label>Name (contains)</label>
          <input v-model="searchName" placeholder="e.g. vacation" />
        </div>
        <div class="field-group">
          <label>Format</label>
          <input v-model="searchFormat" placeholder="e.g. jpeg, png" />
        </div>
        <div class="field-group">
          <label>Size</label>
          <input v-model="searchSize" placeholder="e.g. 800*600" />
        </div>
        <div class="field-group">
          <label>Keywords</label>
          <input v-model="searchKeywords" placeholder="e.g. nature, outdoors" />
        </div>
      </div>

      <button @click="performAttributeSearch" class="action-btn">SEARCH RECORDS</button>

      <div v-if="attributeError" class="error">{{ attributeError }}</div>

      <div v-if="attributeResults.length > 0" class="results-grid">
        <div v-for="id in attributeResults" :key="id" class="result-item">
          <img :src="getImageUrl(id)" loading="lazy" />
          <div class="caption">ID: {{ id }}</div>
        </div>
      </div>
    </div>

    <!-- SIMILARITY SEARCH PANEL -->
    <div v-if="activeTab === 'similarity'" class="panel">
      <div class="search-grid">
        <div class="field-group span-full">
          <label>Source Image</label>
          <select v-model="selectedSourceImageId">
            <option :value="null">-- Select Image --</option>
            <option v-for="img in allImages" :key="img.id" :value="img.id">
              {{ img.id }} - {{ img.name }}
            </option>
          </select>
        </div>

        <div class="field-group">
          <label>Algorithm</label>
          <select v-model="similarityAlgorithm">
            <option value="gradient">Gradient / Orientation (Shape)</option>
            <option value="saturation">Saturation (Color Intensity)</option>
            <option value="rgb">RGB (Color Distribution)</option>
          </select>
        </div>

        <div class="field-group">
          <label>Result Count</label>
          <input v-model.number="similarityCount" type="number" min="1" max="50" />
        </div>
      </div>

      <button @click="performSimilaritySearch" class="action-btn">INITIATE SCAN</button>

      <div v-if="similarityError" class="error">{{ similarityError }}</div>

      <div v-if="similarityResults.length > 0" class="results-grid">
        <div v-for="(res, idx) in similarityResults" :key="idx" class="result-item">
          <img :src="getImageUrl(res.id)" loading="lazy" />
          <div class="caption">
            Score: {{ typeof res.score === "number" ? res.score.toFixed(4) : res.score }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.search-container {
  max-width: 900px;
  margin: 0 auto;
  text-align: left;
}

h2 {
  margin-bottom: 2rem;
  text-align: center;
}

.tabs {
  display: flex;
  gap: 2px; /* Slight gap for separation */
  margin-bottom: 20px;
}

.tabs button {
  flex: 1;
  border-bottom: none;
  background-color: transparent;
  border: 2px solid transparent;
  opacity: 0.5;
  padding: 1rem;
  font-size: 1.2rem;
}

.tabs button.active {
  opacity: 1;
  background-color: var(--bg-secondary);
  border: 2px solid var(--border-color);
  border-bottom: none; /* Make it look connected to panel */
  position: relative;
  top: 2px; /* Overlap border */
  z-index: 2;
}

.panel {
  background-color: var(--bg-secondary);
  border: 1px solid var(--border-color);
  padding: 30px;
  position: relative;
  z-index: 1;
  border-radius: 0 var(--radius-lg, 12px) var(--radius-lg, 12px) var(--radius-lg, 12px); /* Check tab alignment */
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
}

.search-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 24px;
  margin-bottom: 24px;
}

.field-group {
  display: flex;
  flex-direction: column;
}

.field-group.span-full {
  grid-column: 1 / -1;
}

.field-group label {
  margin-bottom: 8px;
  color: var(--text-secondary);
  font-size: 0.85rem;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  font-weight: 600;
}

/* Inputs are styled globally but we ensure box-model fits */
input,
select {
  width: 100%;
  box-sizing: border-box;
  padding: 12px;
  font-size: 1rem;
}

.action-btn {
  width: 100%;
  padding: 16px;
  font-size: 1.1rem;
  letter-spacing: 1px;
  border-radius: var(--radius-md, 8px);
  background-color: var(--color-primary);
  color: var(--text-on-primary);
  font-weight: 700;
  text-transform: uppercase;
  border: none;
  cursor: pointer;
}
.action-btn:hover {
  background-color: var(--color-primary-hover);
}

.error {
  color: var(--color-danger);
  margin-top: 20px;
  border: 1px solid var(--color-danger); /* softer than dashed */
  background-color: rgba(229, 115, 115, 0.1); /* light red tint */
  border-radius: var(--radius-md, 8px);
  padding: 15px;
  text-align: center;
  font-weight: 600;
}

.results-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 24px;
  margin-top: 40px;
  border-top: 1px solid var(--border-color);
  padding-top: 24px;
}

.result-item {
  border: 1px solid var(--border-color);
  padding: 8px;
  text-align: center;
  background-color: var(--bg-secondary);
  transition:
    transform 0.2s,
    box-shadow 0.2s;
  border-radius: var(--radius-md, 8px);
  overflow: hidden;
}

.result-item:hover {
  transform: translateY(-4px);
  border-color: var(--color-primary);
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
}

.result-item img {
  width: 100%;
  height: 200px;
  object-fit: contain;
  display: block;
  background-color: var(--bg-tertiary);
  border-radius: var(--radius-sm, 4px);
  margin-bottom: 8px;
  border-bottom: none;
}

.caption {
  font-size: 0.9rem;
  padding: 0 5px 5px;
  font-family: var(--font-code, monospace);
}
</style>
