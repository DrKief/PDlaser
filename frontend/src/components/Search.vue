<script setup lang="ts">
import { ref, onMounted, watch, computed } from "vue";
import http from "../http-api";
import { useImageStatus } from "../composables/useImageStatus";

interface Image {
  id: number;
  name: string;
}

const activeTab = ref<"attributes" | "similarity">("attributes");
const { statusCache, fetchStatus, pollStatus } = useImageStatus();

class TrieNode {
  children = new Map<string, TrieNode>();
  ids: number[] = [];
  words: string[] = [];
}

class Trie {
  root = new TrieNode();

  insert(name: string, id: number) {
    const lower = name.toLowerCase();
    for (let start = 0; start < lower.length; start++) {
      let node = this.root;
      for (let i = start; i < lower.length; i++) {
        const ch = lower.charAt(i);
        if (!node.children.has(ch)) node.children.set(ch, new TrieNode());
        node = node.children.get(ch)!;
        if (!node.ids.includes(id)) node.ids.push(id);
      }
    }
  }

  insertWord(word: string) {
    const lower = word.toLowerCase();
    for (let start = 0; start < lower.length; start++) {
      let node = this.root;
      for (let i = start; i < lower.length; i++) {
        const ch = lower.charAt(i);
        if (!node.children.has(ch)) node.children.set(ch, new TrieNode());
        node = node.children.get(ch)!;
        if (!node.words.includes(word)) node.words.push(word);
      }
    }
  }

  search(query: string): number[] {
    let node = this.root;
    for (const ch of query.toLowerCase()) {
      if (!node.children.has(ch)) return [];
      node = node.children.get(ch)!;
    }
    return node.ids.slice(0, 8);
  }

  searchWords(query: string): string[] {
    let node = this.root;
    for (const ch of query.toLowerCase()) {
      if (!node.children.has(ch)) return [];
      node = node.children.get(ch)!;
    }
    return node.words.slice(0, 8);
  }
}

const nameTrie = new Trie();

// --- Attribute Search State ---
const searchName = ref("");
const nameInputFocused = ref(false);
const nameHighlightedIndex = ref(-1);

const searchFormat = ref("");
const searchSize = ref("");

const searchKeywords = ref<string[]>([]);
const allKeywords = ref<string[]>([]);
const tagDropdownOpen = ref(false);

const attributeResults = ref<number[]>([]);
const attributeError = ref("");
const hasSearchedAttr = ref(false);

const delayBlur = (cb: () => void) => window.setTimeout(cb, 150);

const nameSuggestions = computed(() => {
  const q = searchName.value.trim();
  if (!q || !nameInputFocused.value) return [];
  return nameTrie.search(q).map(id => allImages.value.find(img => img.id === id)!);
});

const selectNameSuggestion = (name: string) => {
  searchName.value = name;
  nameInputFocused.value = false;
  nameHighlightedIndex.value = -1;
};

const handleNameKeydown = (e: KeyboardEvent) => {
  if (!nameSuggestions.value.length) return;
  if (e.key === "ArrowDown") {
    e.preventDefault();
    nameHighlightedIndex.value = Math.min(nameHighlightedIndex.value + 1, nameSuggestions.value.length - 1);
  } else if (e.key === "ArrowUp") {
    e.preventDefault();
    nameHighlightedIndex.value = Math.max(nameHighlightedIndex.value - 1, 0);
  } else if (e.key === "Enter" && nameHighlightedIndex.value >= 0) {
    e.preventDefault();
    const suggestion = nameSuggestions.value[nameHighlightedIndex.value];
    if (suggestion) {
      selectNameSuggestion(suggestion.name);
    }
  } else if (e.key === "Escape") {
    nameInputFocused.value = false;
  }
};

const attrSortOrder = ref<"asc" | "desc">("asc");

const sortedAttributeResults = computed(() => {
  const arr = [...attributeResults.value];
  return arr.sort((a, b) => attrSortOrder.value === "asc" ? a - b : b - a);
});

// --- Similarity Search State ---
const allImages = ref<Image[]>([]);
const selectedSourceImageId = ref<number | null>(null);
const simQuery = ref("");
const simInputFocused = ref(false);
const simHighlightedIndex = ref(-1);

const similarityAlgorithm = ref("gradient");
const similarityCount = ref(10);
const similarityResults = ref<any[]>([]);
const similarityError = ref("");
const hasSearchedSim = ref(false);

const simSortOrder = ref<"asc" | "desc">("asc");

const simSuggestions = computed(() => {
  const q = simQuery.value.trim();
  if (!q && simInputFocused.value) return allImages.value.slice(0, 8); // show some default if empty
  if (!q || !simInputFocused.value) return [];
  return nameTrie.search(q).map(id => allImages.value.find(img => img.id === id)!);
});

const selectSimSuggestion = (id: number, name: string) => {
  selectedSourceImageId.value = id;
  simQuery.value = name;
  simInputFocused.value = false;
  simHighlightedIndex.value = -1;
};

const handleSimKeydown = (e: KeyboardEvent) => {
  if (!simSuggestions.value.length) return;
  if (e.key === "ArrowDown") {
    e.preventDefault();
    simHighlightedIndex.value = Math.min(simHighlightedIndex.value + 1, simSuggestions.value.length - 1);
  } else if (e.key === "ArrowUp") {
    e.preventDefault();
    simHighlightedIndex.value = Math.max(simHighlightedIndex.value - 1, 0);
  } else if (e.key === "Enter" && simHighlightedIndex.value >= 0) {
    e.preventDefault();
    const suggestion = simSuggestions.value[simHighlightedIndex.value];
    if (suggestion) {
      selectSimSuggestion(suggestion.id, suggestion.name);
    }
  } else if (e.key === "Escape") {
    simInputFocused.value = false;
  }
};

const sortedSimilarityResults = computed(() => {
  const arr = [...similarityResults.value];
  return arr.sort((a, b) => {
    const scoreA = Number(a.score) || 0;
    const scoreB = Number(b.score) || 0;
    return simSortOrder.value === "asc" ? scoreA - scoreB : scoreB - scoreA;
  });
});

const imageNameById = computed(() => Object.fromEntries(allImages.value.map(img => [img.id, img.name])));

const fetchAllImages = async () => {
  try {
    const response = await http.get("/images");
    allImages.value = response.data;
    allImages.value.forEach(img => nameTrie.insert(img.name, img.id));
  } catch (e) {
    console.error("Failed to fetch images list", e);
  }
};

const fetchAllKeywords = async () => {
  try {
    const response = await http.get("/images/keywords");
    allKeywords.value = response.data;
  } catch (e) {
    console.error("Failed to fetch keywords", e);
  }
};

onMounted(() => {
  fetchAllImages();
  fetchAllKeywords();
});

const getImageUrl = (id: number) => `/images/${id}`;

const performAttributeSearch = async () => {
  attributeError.value = "";
  attributeResults.value = [];
  hasSearchedAttr.value = true;

  const params: any = {};
  if (searchName.value) params.name = searchName.value;
  if (searchFormat.value) params.format = searchFormat.value;
  if (searchSize.value) params.size = searchSize.value;
  if (searchKeywords.value && searchKeywords.value.length > 0) {
    params.keywords = searchKeywords.value.join(",");
  }

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
        <div class="field-group" style="position: relative;">
          <label>Name contains</label>
          <input
            v-model="searchName"
            placeholder="e.g. landscape"
            autocomplete="off"
            @focus="nameInputFocused = true"
            @blur="delayBlur(() => nameInputFocused = false)"
            @keydown="handleNameKeydown"
            @keyup.enter="!nameHighlightedIndex && performAttributeSearch()"
          />
          <ul v-if="nameSuggestions.length" class="autocomplete-dropdown">
            <li
              v-for="(img, i) in nameSuggestions"
              :key="img.id"
              :class="{ highlighted: i === nameHighlightedIndex }"
              @mousedown.prevent="selectNameSuggestion(img.name)"
            >
              {{ img.name }}
            </li>
          </ul>
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
          <div class="dropdown-multiselect">
            <div :class="['dropdown-header', { open: tagDropdownOpen }]" @click="tagDropdownOpen = !tagDropdownOpen">
              <span class="header-text" :class="{ 'placeholder': searchKeywords.length === 0 }">
                {{ searchKeywords.length ? searchKeywords.join(', ') : 'Select tags...' }}
              </span>
              <span class="dropdown-arrow">▼</span>
            </div>
            <div class="dropdown-list" v-show="tagDropdownOpen">
              <label v-for="kw in allKeywords" :key="kw" class="dropdown-item">
                <input type="checkbox" :value="kw" v-model="searchKeywords" class="sr-only" />
                <span class="custom-dot"></span>
                <span class="dropdown-item-text">{{ kw }}</span>
              </label>
            </div>
          </div>
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
        <div class="field-group span-full" style="position: relative;">
          <label>Reference Image</label>
          <input
            v-model="simQuery"
            placeholder="Type to search for reference image..."
            autocomplete="off"
            @focus="simInputFocused = true"
            @blur="delayBlur(() => simInputFocused = false)"
            @keydown="handleSimKeydown"
            class="sim-input"
          />
          <ul v-if="simSuggestions.length" class="autocomplete-dropdown">
            <li
              v-for="(img, i) in simSuggestions"
              :key="img.id"
              :class="{ highlighted: i === simHighlightedIndex }"
              @mousedown.prevent="selectSimSuggestion(img.id, img.name)"
            >
              {{ img.name }} (ID: {{ img.id }})
            </li>
          </ul>

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
      <div class="results-header-container">
        <h3 class="results-header">Results</h3>
        <div class="sort-control">
          <label>Sort Order:</label>
          <select v-if="activeTab === 'attributes'" v-model="attrSortOrder" class="sort-select">
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
          </select>
          <select v-if="activeTab === 'similarity'" v-model="simSortOrder" class="sort-select">
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
          </select>
        </div>
      </div>

      <template v-if="activeTab === 'attributes'">
        <div v-if="attributeResults.length === 0 && !attributeError" class="empty-state">
          No matches found for the specified criteria.
        </div>
        <div v-else class="results-grid">
          <article v-for="id in sortedAttributeResults" :key="id" class="result-card panel">
            <div class="image-wrapper">
              <img :src="getImageUrl(id)" loading="lazy" />
            </div>
            <div class="result-meta">
              <span class="record-id">{{ imageNameById[id] ?? `ID: ${id}` }}</span>
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
          <article v-for="(res, idx) in sortedSimilarityResults" :key="idx" class="result-card panel">
            <div class="image-wrapper">
              <img :src="getImageUrl(res.id)" loading="lazy" />
            </div>
            <div class="result-meta">
              <div class="meta-row">
                <span class="record-id">{{ imageNameById[res.id] ?? `ID: ${res.id}` }}</span>
                <span class="score"
                  >Distance:
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

.dropdown-multiselect {
  position: relative;
  width: 100%;
}

.dropdown-header {
  border-radius: var(--radius-md);
  border: 1px solid transparent;
  padding: 0.75rem 1rem;
  font-family: var(--font-sans);
  font-size: 0.875rem;
  background-color: var(--bg-element);
  color: var(--text-primary);
  transition: all 0.2s var(--ease-standard);
  width: 100%;
  box-sizing: border-box;
  box-shadow: var(--shadow-inset);
  cursor: pointer;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.dropdown-header.open {
  background-color: var(--bg-surface);
  border-color: var(--color-accent);
  box-shadow: 0 0 0 3px color-mix(in oklch, var(--color-accent) 20%, transparent);
}

.header-text {
  flex: 1;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.header-text.placeholder {
  color: var(--text-secondary);
}

.dropdown-arrow {
  font-size: 0.8rem;
  color: var(--text-muted);
}

.dropdown-list {
  position: absolute;
  top: calc(100% + var(--space-xs));
  left: 0;
  right: 0;
  background-color: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: var(--radius-md);
  max-height: 200px;
  overflow-y: auto;
  z-index: 10;
  box-shadow: var(--shadow-surface);
  padding: var(--space-xs) 0;
}

.dropdown-item {
  display: flex;
  align-items: center;
  gap: var(--space-sm);
  padding: 0.5rem 1rem;
  cursor: pointer;
  font-size: 0.875rem;
  color: var(--text-primary);
  transition: background-color 0.2s var(--ease-standard);
}

.dropdown-item:hover {
  background-color: var(--bg-element-hover);
}

.custom-dot {
  width: 14px;
  height: 14px;
  border-radius: 50%;
  border: 2px solid var(--border-subtle);
  background-color: transparent;
  transition: all 0.2s var(--ease-standard);
  flex-shrink: 0;
}

input:checked + .custom-dot {
  background-color: var(--color-accent);
  border-color: var(--color-accent);
}

.dropdown-item-text {
  flex: 1;
  text-align: left;
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

.results-header-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: var(--space-sm);
  margin-top: var(--space-2xl);
}

.results-header {
  margin: 0;
  font-size: 1.125rem;
}

.sort-control {
  display: flex;
  align-items: center;
  gap: var(--space-sm);
}

.sort-control label {
  margin: 0;
}

.sort-select {
  width: auto;
  min-width: 120px;
  padding: 0.4rem 0.8rem;
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

.autocomplete-dropdown {
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: var(--radius-sm);
  box-shadow: 0 4px 16px rgba(0,0,0,0.12);
  list-style: none;
  margin: 4px 0 0;
  padding: 4px 0;
  z-index: 100;
  max-height: 240px;
  overflow-y: auto;
}

.autocomplete-dropdown li {
  padding: 0.5rem 0.75rem;
  font-size: 0.875rem;
  cursor: pointer;
  color: var(--text-primary);
}

.autocomplete-dropdown li:hover,
.autocomplete-dropdown li.highlighted {
  background: var(--bg-element);
}

.sim-input {
  width: 100%;
}
</style>
