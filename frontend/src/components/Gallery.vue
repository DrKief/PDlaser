<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import http from "../http-api";

interface Image { id: number; name: string; keywords: string[]; }
const images = ref<Image[]>([]);
const isLoading = ref(true);
const router = useRouter();

onMounted(async () => {
  try {
    const response = await http.get("/images");
    images.value = response.data.map((img: any) => ({ ...img, keywords: img.keywords || [] }));
  } catch (error) {
    console.error(error);
  } finally {
    isLoading.value = false;
  }
});

const getImageUrl = (image: Image) => `/images/${image.id}`;

const goToImage = (id: number) => {
  router.push(`/image/${id}`);
};
</script>

<template>
  <div class="view-wrapper">
    <div v-if="isLoading" class="empty-state label-text">Loading archive...</div>
    <div v-else-if="images.length === 0" class="empty-state">
      <h2 style="margin-bottom: 1rem;">No images found.</h2>
      <router-link to="/upload" class="btn">Upload your first image</router-link>
    </div>

    <div v-else class="masonry-grid">
      <article 
        v-for="image in images" 
        :key="image.id" 
        class="artifact-card"
        @click="goToImage(image.id)"
      >
        <img :src="getImageUrl(image)" :alt="image.name" class="artifact-img" loading="lazy" />
        
        <div class="card-overlay">
          <div class="overlay-content">
            <h3 class="artifact-name">{{ image.name }}</h3>
            <div class="tags" v-if="image.keywords.length">
              <span class="tag-text">{{ image.keywords.join(', ') }}</span>
            </div>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

<style scoped>
.view-wrapper { animation: fadeIn 0.6s ease-out; }
@keyframes fadeIn { from { opacity: 0; transform: translateY(5px); } to { opacity: 1; transform: translateY(0); } }

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
  border-radius: 4px; /* Subtle softening */
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
  pointer-events: none; /* Let clicks pass through to the article */
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

/* --- CRUELTY OVERRIDES --- */
:root.cruelty .artifact-card {
  border-radius: 0;
  border: 4px solid var(--border-strong);
  transform: rotate(calc(-2deg + (4deg * var(--n, 1)))); 
}
:root.cruelty .artifact-card:nth-child(even) { --n: 0; border-color: var(--color-accent); }
:root.cruelty .artifact-img { filter: contrast(200%) saturate(300%) hue-rotate(90deg); }
:root.cruelty .artifact-card:hover .artifact-img { filter: invert(1); }
:root.cruelty .card-overlay {
  opacity: 1;
  background: transparent;
  mix-blend-mode: difference;
}
:root.cruelty .artifact-name {
  font-family: 'Impact'; font-size: 1.5rem; background: #000; padding: 0.25rem; display: inline-block;
}
</style>