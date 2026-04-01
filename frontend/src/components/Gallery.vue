<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../http-api";

interface Image { 
  id: number; 
  name?: string; 
  uploader?: string;
  keywords: string[]; 
}

const route = useRoute();
const router = useRouter();

const allImages = ref<Image[]>([]);
const displayedImages = ref<Image[]>([]);
const isLoading = ref(true);

// Backend Pagination State
const displayLimit = ref(30); 
const currentPage = ref(0);
const hasMore = ref(true);

const loadImages = async (reset = false) => {
  if (isLoading.value && !reset) return;
  isLoading.value = true;
  
  if (reset) { 
    currentPage.value = 0; 
    allImages.value = []; 
    hasMore.value = true; 
  }
  
  try {
    const activeTag = route.query.tags as string;
    let response: any;

    if (activeTag) {
      // Dynamic filter via backend search
      response = await http.get("/images/search", { params: { keywords: activeTag } });
      
      // Fallback: Fetch all and filter locally to get metadata until the backend search returns full objects
      if (response.data.length > 0) {
        const all = await http.get("/images");
        const formatted = all.data
          .filter((img: any) => response.data.includes(img.id))
          .map((img: any) => ({ ...img, keywords: img.keywords || [] }));
        allImages.value = formatted;
        hasMore.value = false; // Search handles its own limits currently
      } else {
        allImages.value = [];
        hasMore.value = false;
      }
    } else {
      // Standard paginated load
      response = await http.get(`/images?page=${currentPage.value}&size=${displayLimit.value}`);
      if (response.data.length < displayLimit.value) {
        hasMore.value = false;
      }
      const formatted = response.data.map((img: any) => ({ ...img, keywords: img.keywords || [] }));
      allImages.value.push(...formatted);
    }
    
    updateDisplayedImages();

  } catch (error) {
    console.error(error);
    if (reset) {
      allImages.value = [];
      updateDisplayedImages();
    }
  } finally {
    isLoading.value = false;
  }
};

const updateDisplayedImages = () => {
  displayedImages.value = [...allImages.value];
};

const loadMore = () => {
  if (!hasMore.value) return;
  currentPage.value++;
  loadImages();
};

const clearFilter = () => {
  router.push('/');
};

onMounted(() => loadImages(true));

// Watch for URL changes (e.g., from the Top Nav search bar)
watch(() => route.query.tags, () => loadImages(true));

const getImageUrl = (image: Image) => `/images/${image.id}`;
const goToImage = (id: number) => router.push(`/image/${id}`);
</script>

<template>
  <div class="view-wrapper">
    <!-- Dynamic Header -->
    <div class="gallery-header" v-if="route.query.tags">
      <h2 class="filter-title">
        Results for: <span class="highlight">#{{ route.query.tags }}</span>
      </h2>
      <button class="btn btn-outline" @click="clearFilter">Clear Filter</button>
    </div>

    <div v-if="isLoading && allImages.length === 0" class="empty-state label-text">Loading archive...</div>
    <div v-else-if="allImages.length === 0" class="empty-state">
      <h2 style="margin-bottom: 1rem;">No images found.</h2>
      <p class="label-text" v-if="route.query.tags">Try a different search term.</p>
      <router-link to="/upload" class="btn" v-else>Upload your first image</router-link>
    </div>

    <div v-else>
      <div class="masonry-grid">
        <article 
          v-for="image in displayedImages" 
          :key="image.id" 
          class="artifact-card"
          @click="goToImage(image.id)"
        >
          <img :src="getImageUrl(image)" :alt="'Image ' + image.id" class="artifact-img" loading="lazy" />
          
          <div class="card-overlay">
            <div class="overlay-content">
              <!-- Privacy Change: Expose Uploader, hide filename -->
              <h3 class="artifact-name">@{{ image.uploader || 'System' }}</h3>
              <div class="tags" v-if="image.keywords && image.keywords.length">
                <span class="tag-text">#{{ image.keywords.join(', #') }}</span>
              </div>
            </div>
          </div>
        </article>
      </div>

      <!-- Pagination Load More -->
      <div class="load-more-container" v-if="hasMore">
        <button class="btn btn-outline" @click="loadMore" :disabled="isLoading">
          {{ isLoading ? 'Loading...' : 'Load More Artifacts' }}
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.view-wrapper { animation: fadeIn 0.6s ease-out; }
@keyframes fadeIn { from { opacity: 0; transform: translateY(5px); } to { opacity: 1; transform: translateY(0); } }

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
.highlight { color: var(--color-accent); font-style: italic; }

.empty-state { 
  text-align: center; 
  padding: var(--space-xl) 0; 
  color: var(--text-muted); 
}

/* MASONRY GRID (Unsplash Style) */
.masonry-grid {
  columns: 1;
  column-gap: 1.5rem;
}
@media (min-width: 640px) { .masonry-grid { columns: 2; } }
@media (min-width: 1024px) { .masonry-grid { columns: 3; } }
@media (min-width: 1536px) { .masonry-grid { columns: 4; } }

.artifact-card {
  break-inside: avoid;
  margin-bottom: 1.5rem;
  position: relative;
  background: var(--bg-element);
  cursor: zoom-in;
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
  background: linear-gradient(to top, rgba(0,0,0,0.6) 0%, rgba(0,0,0,0) 40%);
  opacity: 0;
  transition: opacity 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 1.5rem;
  color: #fff;
  pointer-events: none;
}
.artifact-card:hover .card-overlay { opacity: 1; }

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
  color: rgba(255,255,255,0.8); 
}

.load-more-container {
  display: flex;
  justify-content: center;
  margin-top: var(--space-xl);
  padding-bottom: var(--space-lg);
}

/* --- CRUELTY OVERRIDES --- */
:root.cruelty .filter-title { font-family: 'Impact'; color: #FF00FF; text-transform: uppercase; }
:root.cruelty .highlight { color: #00FF00; }
:root.cruelty .artifact-card {
  border-radius: 0; border: 4px solid var(--border-strong);
  transform: rotate(calc(-2deg + (4deg * var(--n, 1)))); 
}
:root.cruelty .artifact-card:nth-child(even) { --n: 0; border-color: var(--color-accent); }
:root.cruelty .artifact-img { filter: contrast(200%) saturate(300%) hue-rotate(90deg); }
:root.cruelty .artifact-card:hover .artifact-img { filter: invert(1); }
:root.cruelty .card-overlay { opacity: 1; background: transparent; mix-blend-mode: difference; }
:root.cruelty .artifact-name { font-family: 'Impact'; font-size: 1.5rem; background: #000; padding: 0.25rem; display: inline-block; }
</style>