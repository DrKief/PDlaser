<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../api/http-client";
import { useAuth } from "../composables/useAuth";
import { useGallerySearch, type Image } from "../composables/useGallerySearch";
import AdvancedSearchSidebar from "../components/AdvancedSearchSidebar.vue";

const route = useRoute();
const router = useRouter();
const { isLoggedIn } = useAuth();
const {
  similarityResultsOverride,
  similaritySourceOverride,
  selectedSourceId,
  clearSimilaritySearch,
} = useGallerySearch();

const displayedImages = ref<Image[]>([]);
const isLoading = ref(true);
const displayLimit = ref(30);
const currentPage = ref(0);
const totalPages = ref(1);

const isSidebarOpen = ref(false);
const pendingSource = ref<{ url: string; isEphemeral: boolean } | null>(null);
const selectedForSimilarity = ref<number[]>([]);

const toggleSimilaritySelection = (id: number) => {
  if (selectedForSimilarity.value.includes(id)) {
    selectedForSimilarity.value = selectedForSimilarity.value.filter((i) => i !== id);
  } else {
    if (selectedForSimilarity.value.length >= 5) return;
    selectedForSimilarity.value = [...selectedForSimilarity.value, id];
  }
};

const openSimilarityWithSelection = () => {
  if (selectedForSimilarity.value.length > 0) {
    selectedSourceId.value = selectedForSimilarity.value[0] ?? null;
    isSidebarOpen.value = true;
  }
};

const handleSimilarityResults = (payload: {
  results: any[];
  sourceId: number | null;
  isEphemeral?: boolean;
  fileUrl?: string;
}) => {
  const { results, sourceId, isEphemeral, fileUrl } = payload;

  // 1. Establish the visual Source Image highlight
  if (isEphemeral && fileUrl) {
    similaritySourceOverride.value = {
      id: "Ephemeral",
      url: fileUrl,
      uploader: "Uploaded Target",
      keywords: [{ keyword: "Source Image", isAi: false }],
    };
  } else if (sourceId) {
    similaritySourceOverride.value = {
      id: sourceId,
      url: `/images/${sourceId}`,
      uploader: "Database Target",
      keywords: [{ keyword: "Source Image", isAi: false }],
    };
  }

  // Clear pending source since we now have a completed search source
  pendingSource.value = null;

  // 2. Map the mathematical results
  similarityResultsOverride.value = results.map((res: { id: number; score: number }) => ({
    id: res.id,
    url: `/images/${res.id}`,
    uploader: "Matched Result",
    extraction_status: "COMPLETED",
    keywords: [{ keyword: `${((1 - res.score) * 100).toFixed(2)}% Match`, isAi: false }],
  }));
};

const clearSourceSelection = () => {
  selectedSourceId.value = null;
  pendingSource.value = null;
  isSidebarOpen.value = false;
  clearSimilaritySearch();
};

const handleSourceSelected = (payload: {
  sourceId?: number | null;
  fileUrl?: string;
  isEphemeral: boolean;
}) => {
  if (payload.isEphemeral && payload.fileUrl) {
    pendingSource.value = { url: payload.fileUrl, isEphemeral: true };
  } else if (payload.sourceId) {
    pendingSource.value = { url: `/images/${payload.sourceId}`, isEphemeral: false };
  }
};

const loadImages = async (reset = false) => {
  if (reset) currentPage.value = 0;
  isLoading.value = true;
  clearSimilaritySearch();

  try {
    const activeTag = route.query.tags as string;
    let response: { data: { content: any[]; totalElements: number } };
    if (activeTag) {
      response = await http.get("/images/search", {
        params: { keywords: activeTag, page: currentPage.value, size: displayLimit.value },
      });
    } else {
      response = await http.get(`/images?page=${currentPage.value}&size=${displayLimit.value}`);
    }

    const { content, totalElements } = response.data;

    // Map full backend data including extraction_status
    displayedImages.value = content.map(
      (img: { id: number; uploader: string; keywords: any[]; extraction_status: string }) => ({
        id: img.id,
        url: `/images/${img.id}`,
        uploader: img.uploader,
        keywords: img.keywords || [],
        extraction_status: img.extraction_status,
      }),
    );
    totalPages.value = Math.ceil(totalElements / displayLimit.value) || 1;
  } catch (error) {
    if (reset) {
      displayedImages.value = [];
      totalPages.value = 1;
    }
  } finally {
    isLoading.value = false;
  }
};

const goToPage = (pageNumber: number) => {
  if (pageNumber >= 0 && pageNumber < totalPages.value) {
    currentPage.value = pageNumber;
    loadImages();
  }
};

const clearFilter = () => {
  router.push("/");
  clearSimilaritySearch();
};

onMounted(() => {
  if (route.query.sourceId) {
    selectedSourceId.value = parseInt(route.query.sourceId as string);
    isSidebarOpen.value = true;
  }
  loadImages(true);
});

watch(
  () => route.query.tags,
  () => loadImages(true),
);

const goToImage = (id: number | string) => {
  if (typeof id === "number") router.push(`/image/${id}`);
};
</script>

<template>
  <div class="gallery-layout">
    <div class="main-content">
      <div class="gallery-header">
        <h2 class="filter-title" v-if="similarityResultsOverride">Visual Matches</h2>
        <h2 class="filter-title" v-else-if="route.query.tags">
          Results for: <span class="highlight">#{{ route.query.tags }}</span>
        </h2>
        <h2 class="filter-title" v-else>Gallery</h2>

        <div class="header-actions">
          <div class="search-button-group">
            <button class="btn btn-outline" @click="isSidebarOpen = !isSidebarOpen">
              <span
                class="material-symbols-outlined"
                style="vertical-align: middle; margin-right: 0.25rem"
                >image_search</span
              >
              Similarity Search
            </button>
            <div v-if="similaritySourceOverride || pendingSource" class="source-thumbnail-badge">
              <img
                :src="similaritySourceOverride?.url || pendingSource?.url"
                :alt="similaritySourceOverride?.uploader || 'Selected source'"
                class="thumbnail-image"
              />
              <button
                @click="clearSourceSelection"
                class="btn-icon thumbnail-clear"
                title="Clear selection"
              >
                <span class="material-symbols-outlined">close</span>
              </button>
            </div>
          </div>
          <button
            class="btn btn-outline"
            v-if="route.query.tags || similarityResultsOverride"
            @click="clearFilter"
          >
            Clear Filter
          </button>
        </div>
      </div>

      <div v-if="isLoading && displayedImages.length === 0" class="empty-state label-text">
        Loading archive...
      </div>

      <div
        v-else-if="!similarityResultsOverride && displayedImages.length === 0"
        class="empty-state"
      >
        <h2 style="margin-bottom: 1rem">No images found.</h2>
        <p class="label-text" v-if="route.query.tags">Try a different search term.</p>
        <router-link to="/upload" class="btn" v-else-if="isLoggedIn"
          >Upload your first image</router-link
        >
        <p class="label-text" v-else>You must log in to upload images.</p>
      </div>

      <div v-else>
        <div class="masonry-grid">
          <article v-if="similaritySourceOverride" class="artifact-card source-card">
            <div class="source-badge">
              <span class="material-symbols-outlined" style="font-size: 1rem">target</span>
              SOURCE TARGET
            </div>
            <img :src="similaritySourceOverride.url" class="artifact-img" draggable="false" />
          </article>

          <article
            v-for="image in similarityResultsOverride || displayedImages"
            :key="image.id"
            class="artifact-card"
          >
            <img
              :src="image.url"
              @click="goToImage(image.id)"
              class="artifact-img"
              loading="lazy"
              draggable="false"
            />

            <div class="hover-actions" v-if="typeof image.id === 'number'">
              <button
                @click.stop="toggleSimilaritySelection(image.id as number)"
                :title="
                  selectedForSimilarity.includes(image.id as number)
                    ? 'Remove from search'
                    : 'Add to search'
                "
                class="btn-icon similarity-btn"
                :class="{ 'is-selected': selectedForSimilarity.includes(image.id as number) }"
              >
                <span class="material-symbols-outlined">{{
                  selectedForSimilarity.includes(image.id as number) ? "remove" : "add"
                }}</span>
              </button>
            </div>

            <div class="card-overlay" @click="goToImage(image.id)">
              <div
                class="meta-chips"
                v-if="image.extraction_status && image.extraction_status !== 'COMPLETED'"
              >
                <span class="meta-chip status-chip" :class="image.extraction_status.toLowerCase()">
                  {{ image.extraction_status }}
                </span>
              </div>

              <div class="overlay-content">
                <h3 class="artifact-name">@{{ image.uploader || "System" }}</h3>
                <div class="tags" v-if="image.keywords && image.keywords.length">
                  <span class="tag-text">
                    <span
                      v-for="(kw, index) in image.keywords.slice(0, 5)"
                      :key="kw.keyword"
                      :class="{ 'ai-tag': kw.isAi }"
                    >
                      #{{ kw.keyword
                      }}<span v-if="Number(index) < Math.min(image.keywords.length, 5) - 1"
                        >,
                      </span>
                    </span>
                    <span v-if="image.keywords.length > 5">, ...</span>
                  </span>
                </div>
              </div>
            </div>
          </article>
        </div>

        <div class="pagination-controls" v-if="!similarityResultsOverride && totalPages > 1">
          <button
            class="btn btn-outline"
            @click="goToPage(currentPage - 1)"
            :disabled="currentPage === 0 || isLoading"
          >
            &laquo; Prev
          </button>
          <div class="page-numbers">
            <button
              v-for="page in totalPages"
              :key="page"
              class="btn page-btn"
              :class="{ active: page - 1 === currentPage }"
              @click="goToPage(page - 1)"
              :disabled="isLoading"
            >
              {{ page }}
            </button>
          </div>
          <button
            class="btn btn-outline"
            @click="goToPage(currentPage + 1)"
            :disabled="currentPage === totalPages - 1 || isLoading"
          >
            Next &raquo;
          </button>
        </div>
      </div>
    </div>

    <AdvancedSearchSidebar
      v-if="isSidebarOpen"
      :initial-source-id="selectedSourceId"
      :initial-source-ids="selectedForSimilarity"
      @close="
        isSidebarOpen = false;
        selectedSourceId = null;
        selectedForSimilarity = [];
      "
      @executeSimilarity="handleSimilarityResults"
      @sourceSelected="handleSourceSelected"
    />

    <div v-if="selectedForSimilarity.length > 0 && !isSidebarOpen" class="similarity-float">
      <button class="btn similarity-float-btn" @click="openSimilarityWithSelection">
        <span class="material-symbols-outlined">image_search</span>
        Search {{ selectedForSimilarity.length }} image{{
          selectedForSimilarity.length > 1 ? "s" : ""
        }}
      </button>
    </div>
  </div>
</template>

<style scoped>
.gallery-layout {
  display: flex;
  gap: 2rem;
  align-items: flex-start;
}
.main-content {
  flex: 1;
  min-width: 0;
}

/* Source Image Outline CSS */
.source-card {
  border: 3px solid var(--color-accent);
  box-shadow: 0 0 20px color-mixin(in oklch, var(--color-accent) 40%, transparent);
  transform: translateY(-4px);
  z-index: 5;
}
.source-badge {
  position: absolute;
  top: 10px;
  left: 10px;
  background: var(--color-accent);
  color: var(--text-on-accent);
  padding: 0.25rem 0.75rem;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: 700;
  display: flex;
  align-items: center;
  gap: 0.25rem;
  z-index: 10;
  box-shadow: var(--shadow-subtle);
}

.hover-actions {
  position: absolute;
  top: 10px;
  right: 10px;
  opacity: 1;
  transition: opacity 0.2s;
  z-index: 10;
}
@media (hover: hover) and (pointer: fine) {
  .hover-actions {
    opacity: 0;
  }
  .artifact-card:hover .hover-actions {
    opacity: 1;
  }
}

.similarity-btn {
  background: var(--bg-surface);
  padding: 0.5rem;
  border-radius: 4px;
  color: var(--text-primary);
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: var(--shadow-subtle);
}
.similarity-btn:hover {
  color: var(--color-accent);
}
.similarity-btn.is-selected {
  background: var(--color-accent);
  color: var(--bg-surface);
}
.similarity-btn.is-selected:hover {
  background: var(--color-accent-hover);
}

.similarity-float {
  position: fixed;
  bottom: 2rem;
  right: 2rem;
  z-index: 50;
}
.similarity-float-btn {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1.25rem;
  box-shadow:
    var(--shadow-subtle),
    0 4px 12px rgba(0, 0, 0, 0.15);
}

.gallery-header {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: var(--space-lg);
  padding-bottom: var(--space-sm);
  border-bottom: 1px solid var(--border-subtle);
  gap: 1rem;
}
@media (min-width: 768px) {
  .gallery-header {
    flex-direction: row;
    align-items: center;
    gap: 0;
  }
}

.header-actions {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  width: 100%;
}
.header-actions .btn {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
@media (min-width: 768px) {
  .header-actions {
    flex-direction: row;
    width: auto;
    gap: 1rem;
  }
  .header-actions .btn {
    width: auto;
  }
}

.filter-title {
  font-family: var(--font-headline);
  font-size: 2rem;
  margin: 0;
  word-break: break-word;
}
@media (min-width: 768px) {
  .filter-title {
    font-size: 2.5rem;
  }
}
.highlight {
  color: var(--color-accent);
  font-style: italic;
}

.empty-state {
  text-align: center;
  padding: var(--space-xl) 0;
  color: var(--text-muted);
}

.masonry-grid {
  columns: 1;
  column-gap: 1.5rem;
}
@media (min-width: 640px) {
  .masonry-grid {
    columns: 2;
  }
}
@media (min-width: 1024px) {
  .masonry-grid {
    columns: 3;
  }
}
@media (min-width: 1536px) {
  .masonry-grid {
    columns: 4;
  }
}

.artifact-card {
  break-inside: avoid;
  margin-bottom: 1.5rem;
  position: relative;
  background: var(--bg-element);
  overflow: hidden;
  border-radius: 4px;
}

.artifact-img {
  width: 100%;
  height: auto;
  display: block;
  transition: transform 0.4s var(--ease-standard);
}

.card-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(
    to top,
    rgba(0, 0, 0, 0.8) 0%,
    rgba(0, 0, 0, 0) 40%,
    rgba(0, 0, 0, 0) 70%,
    rgba(0, 0, 0, 0.7) 100%
  );
  opacity: 0;
  transition: opacity 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1.5rem;
  color: #fff;
  cursor: pointer;
}
.artifact-card:hover .card-overlay {
  opacity: 1;
}

.overlay-content {
  margin-top: auto;
}

/* Metadata Chips CSS */
.meta-chips {
  display: flex;
  gap: 0.5rem;
  justify-content: flex-end;
}
.meta-chip {
  font-family: var(--font-sans);
  font-size: 0.7rem;
  font-weight: 700;
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  background: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(4px);
  text-transform: uppercase;
}
.status-chip.completed {
  color: var(--color-success);
  border: 1px solid var(--color-success);
}
.status-chip.processing,
.status-chip.pending {
  color: #f59e0b;
  border: 1px solid #f59e0b;
}
.status-chip.failed {
  color: var(--color-danger);
  border: 1px solid var(--color-danger);
}
.id-chip {
  color: #fff;
  border: 1px solid rgba(255, 255, 255, 0.3);
}

.artifact-name {
  font-family: var(--font-sans);
  font-size: 1rem;
  font-weight: 500;
  margin: 0 0 0.25rem 0;
  color: #fff;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.tag-text {
  font-family: var(--font-sans);
  font-size: 0.8rem;
  color: rgba(255, 255, 255, 0.8);
}

.pagination-controls {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: var(--space-md);
  margin-top: var(--space-xl);
  padding-bottom: var(--space-lg);
}
.page-numbers {
  display: flex;
  gap: 0.5rem;
  overflow-x: auto;
}
.page-btn {
  background: var(--bg-surface);
  color: var(--text-primary);
  border: 1px solid var(--border-subtle);
  padding: 0.5rem 1rem;
  min-width: 40px;
}
.page-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
.page-btn:hover:not(:disabled) {
  background: var(--bg-element);
  border-color: var(--text-primary);
}
.page-btn.active {
  background: var(--color-accent);
  color: #fff;
  border-color: var(--color-accent);
}

.search-button-group {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  width: 100%;
}
@media (min-width: 768px) {
  .search-button-group {
    width: auto;
  }
}

.source-thumbnail-badge {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem;
  background: var(--bg-surface);
  border: 2px solid var(--color-accent);
  border-radius: 4px;
  box-shadow: var(--shadow-subtle);
  flex-shrink: 0;
}

.thumbnail-image {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 2px;
  display: block;
}

.thumbnail-clear {
  padding: 0.25rem;
  color: var(--text-primary);
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 2px;
  transition: all 0.2s ease;
}
.thumbnail-clear:hover {
  background: var(--bg-element);
  color: var(--color-accent);
}
</style>
