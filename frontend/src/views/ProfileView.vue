<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import http from "../api/http-client";

const router = useRouter();
const displayedImages = ref<any[]>([]);
const isLoading = ref(true);

const currentUserRole = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const payloadStr = atob(payloadBase64.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(payloadStr).role;
  } catch (e) {
    return null;
  }
});

onMounted(async () => {
  try {
    const mine = currentUserRole.value === "ROLE_ADMIN" ? "false" : "true";
    const response = await http.get(`/images?page=0&size=100&mine=${mine}`);
    displayedImages.value = response.data.content;
  } catch (error) {
    console.error(error);
  } finally {
    isLoading.value = false;
  }
});

const deleteImage = async (id: number, event: Event) => {
  event.stopPropagation();
  if (!confirm("Are you sure you want to permanently delete this image?")) return;
  try {
    await http.delete(`/images/${id}`);
    displayedImages.value = displayedImages.value.filter((img) => img.id !== id);
  } catch (e) {
    alert("Failed to delete image.");
  }
};
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">My Archive</h1>
      <p class="page-subtitle">Manage your personal uploads.</p>
    </header>

    <div v-if="isLoading" class="label-text">Loading your images...</div>
    <div v-else-if="displayedImages.length === 0" class="empty-state">
      <h2>You haven't uploaded anything yet.</h2>
      <router-link to="/upload" class="btn" style="margin-top: 1rem">Upload an Image</router-link>
    </div>

    <div class="masonry-grid" v-else>
      <article
        v-for="image in displayedImages"
        :key="image.id"
        class="artifact-card"
        @click="router.push(`/image/${image.id}`)"
      >
        <img :src="'/images/' + image.id" class="artifact-img" loading="lazy" />

        <div class="hover-actions">
          <button
            @click.stop="deleteImage(image.id, $event)"
            title="Delete Permanently"
            class="btn-icon delete-btn"
          >
            <span class="material-symbols-outlined" style="font-size: 1.2rem">delete</span>
          </button>
        </div>

        <div class="card-overlay">
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
                  }}<span v-if="Number(index) < Math.min(image.keywords.length, 5) - 1">, </span>
                </span>
                <span v-if="image.keywords.length > 5">, ...</span>
              </span>
            </div>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

<style scoped>
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

/* Base Card Overlay matching the Gallery styles */
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
  pointer-events: none;
}
.artifact-card:hover .card-overlay {
  opacity: 1;
}

.overlay-content {
  margin-top: auto;
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

/* Impeccable Context: Hover Interaction Design */
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

.delete-btn {
  background: var(--bg-surface);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0.5rem;
  border-radius: 4px;
  color: var(--color-danger);
  border: 1px solid transparent;
  transition: all 0.2s;
  box-shadow: var(--shadow-subtle);
  cursor: pointer;
}
.delete-btn:hover {
  background: var(--color-danger);
  color: #fff;
  border-color: var(--color-danger);
}
</style>
