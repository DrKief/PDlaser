<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import http from "../api/http-client";

const router = useRouter();
const displayedImages = ref<any[]>([]);
const isLoading = ref(true);

onMounted(async () => {
  try {
    const response = await http.get(`/images?page=0&size=100&mine=true`);
    displayedImages.value = response.data.content;
  } catch (error) {
    console.error(error);
  } finally {
    isLoading.value = false;
  }
});

const deleteImage = async (id: number, event: Event) => {
  event.stopPropagation();
  if (!confirm("Delete this image permanently?")) return;
  try {
    await http.delete(`/images/${id}`);
    displayedImages.value = displayedImages.value.filter(img => img.id !== id);
  } catch (e) {
    alert("Failed to delete image.");
  }
};
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">My Archive</h1>
    </header>

    <div v-if="isLoading" class="label-text">Loading...</div>
    <div v-else-if="displayedImages.length === 0" class="empty-state">
      <h2>No uploads yet.</h2>
    </div>

    <div class="masonry-grid" v-else>
      <article v-for="image in displayedImages" :key="image.id" class="artifact-card" @click="router.push(`/image/${image.id}`)">
        <img :src="'/images/' + image.id" class="artifact-img" />
        <div class="card-overlay" style="opacity: 1; background: transparent; padding: 0.5rem; justify-content: flex-start;">
           <button @click="deleteImage(image.id, $event)" class="btn btn-outline danger" style="background: var(--bg-surface); padding: 0.25rem 0.5rem; font-size: 0.8rem;">
             Delete
           </button>
        </div>
      </article>
    </div>
  </div>
</template>

<style scoped>
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
  cursor: pointer;
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
  display: flex;
  pointer-events: none;
}

.card-overlay button {
  pointer-events: auto;
}

.danger { color: var(--color-danger); border-color: var(--color-danger); }
.danger:hover { background: var(--color-danger); color: #fff; }
</style>