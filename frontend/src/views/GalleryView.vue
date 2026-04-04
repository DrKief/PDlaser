<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../api/http-client";
import { useAuth } from "../composables/useAuth";
import AdvancedSearchSidebar from "../components/AdvancedSearchSidebar.vue";

interface Image {
  id: number | string;
  uploader?: string;
  keywords: string[];
  extraction_status?: string;
  url?: string;
}

const route = useRoute();
const router = useRouter();
const { isLoggedIn } = useAuth();
const displayedImages = ref<Image[]>([]);
const isLoading = ref(true);
const displayLimit = ref(30);
const currentPage = ref(0);
const totalPages = ref(1);

const isSidebarOpen = ref(false);
const similarityResultsOverride = ref<Image[] | null>(null);
const similaritySourceOverride = ref<Image | null>(null);
const selectedSourceId = ref<number | null>(null);

const openSimilarityForImage = (id: number | string) => {
  if (typeof id === "number") {
    selectedSourceId.value = id;
    isSidebarOpen.value = true;
  }
};

const handleSimilarityResults = (payload: any) => {
  const { results, sourceId, isEphemeral, fileUrl } = payload;

  // 1. Establish the visual Source Image highlight
  if (isEphemeral && fileUrl) {
    similaritySourceOverride.value = {
      id: "Ephemeral",
      url: fileUrl,
      uploader: "Uploaded Target",
      keywords: ["Source Image"],
    };
  } else if (sourceId) {
    similaritySourceOverride.value = {
      id: sourceId,
      url: `/images/${sourceId}`,
      uploader: "Database Target",
      keywords: ["Source Image"],
    };
  }

  // 2. Map the mathematical results
  similarityResultsOverride.value = results.map((res: any) => ({
    id: res.id,
    url: `/images/${res.id}`,
    uploader: "Matched Result",
    extraction_status: "COMPLETED",
    keywords: [`${((1 - res.score) * 100).toFixed(2)}% Match`],
  }));
};

const loadImages = async (reset = false) => {
  if (reset) currentPage.value = 0;
  isLoading.value = true;
  similarityResultsOverride.value = null;
  similaritySourceOverride.value = null;

  try {
    const activeTag = route.query.tags as string;
    let response: any;
    if (activeTag) {
      response = await http.get("/images/search", {
        params: { keywords: activeTag, page: currentPage.value, size: displayLimit.value },
      });
    } else {
      response = await http.get(`/images?page=${currentPage.value}&size=${displayLimit.value}`);
    }

    const { content, totalElements } = response.data as { content: any[]; totalElements: number };

    // Map full backend data including extraction_status
    displayedImages.value = content.map((img: any) => ({
      id: img.id,
      url: `/images/${img.id}`,
      uploader: img.uploader,
      keywords: img.keywords || [],
      extraction_status: img.extraction_status,
    }));
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
  similarityResultsOverride.value = null;
  similaritySourceOverride.value = null;
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
          <button
            class="btn btn-outline"
            @click="isSidebarOpen = !isSidebarOpen"
            style="margin-right: 1rem"
          >
            <span
              class="material-symbols-outlined"
              style="vertical-align: middle; margin-right: 0.25rem"
              >image_search</span
            >
            Similarity Search
          </button>
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
            <img :src="similaritySourceOverride.url" class="artifact-img" />
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
            />

            <div class="hover-actions" v-if="typeof image.id === 'number'">
              <button
                @click.stop="openSimilarityForImage(image.id)"
                title="Find Similar"
                class="btn-icon similarity-btn"
              >
                <span class="material-symbols-outlined">image_search</span>
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
                  <span class="tag-text">#{{ image.keywords.join(", #") }}</span>
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
      @close="
        isSidebarOpen = false;
        selectedSourceId = null;
      "
      @executeSimilarity="handleSimilarityResults"
    />
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
  opacity: 0;
  transition: opacity 0.2s;
  z-index: 10;
}
.artifact-card:hover .hover-actions {
  opacity: 1;
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

.gallery-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: var(--space-lg);
  padding-bottom: var(--space-sm);
  border-bottom: 1px solid var(--border-subtle);
}
.filter-title {
  font-family: var(--font-headline);
  font-size: 2.5rem;
  margin: 0;
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
.page-btn:hover:not(:disabled) {
  background: var(--bg-element);
  border-color: var(--text-primary);
}
.page-btn.active {
  background: var(--color-accent);
  color: #fff;
  border-color: var(--color-accent);
}
</style>
