<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../http-api";
const route = useRoute();
const router = useRouter();
const imageId = route.params.id as string;
const metadata = ref<any>(null);
const isLoading = ref(true);

const newTag = ref("");
const isAddingTag = ref(false);

const addTag = async () => {
  const tag = newTag.value.trim();
  if (!tag) return;
  isAddingTag.value = true;
  try {
    await http.put(`/images/${imageId}/keywords?tag=${encodeURIComponent(tag)}`);
    if (!metadata.value.Keywords) metadata.value.Keywords = [];
    if (!metadata.value.Keywords.includes(tag)) {
      metadata.value.Keywords.push(tag);
    }
    newTag.value = '';
  } catch (error) {
    console.error("Failed to add tag", error);
    alert("Failed to add tag. It may already exist or you might not have permission.");
  } finally {
    isAddingTag.value = false;
  }
};

onMounted(async () => {
  try {
    const res = await http.get(`/images/${imageId}/metadata`);
    metadata.value = res.data;
  } catch (error) {
    console.error("Failed to fetch metadata", error);
  } finally {
    isLoading.value = false;
  }
});
const getImageUrl = () => `/images/${imageId}`;
const deleteImage = async () => {
  if (!confirm("Are you sure you want to delete this image?")) return;
  try {
    await http.delete(`/images/${imageId}`);
    router.push("/"); // Back to gallery
  } catch (error) {
    console.error(error);
  }
};
const findSimilar = () => {
  router.push({ path: '/search', query: { sourceId: imageId } });
};
</script>
<template>
  <div class="view-wrapper">
    <div v-if="isLoading" class="loading">Loading metadata...</div>
    <div v-else-if="!metadata" class="error">
      <h2>Image not found</h2>
      <button class="btn" @click="router.push('/')">Return to Gallery</button>
    </div>
    <div v-else class="detail-layout">
      <!-- Left: Full Image -->
      <div class="image-container">
        <img :src="getImageUrl()" :alt="metadata.Name" class="full-img" />
      </div>
      <!-- Right: Metadata & Actions -->
      <aside class="meta-sidebar">
        <h1 class="image-title">{{ metadata.Name }}</h1>
        <div class="meta-section">
          <div class="meta-row">
            <span class="label-text">ID</span>
            <span class="meta-val">{{ imageId }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Format</span>
            <span class="meta-val">{{ metadata.Type }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Resolution</span>
            <span class="meta-val">{{ metadata.Size }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Extraction Status</span>
            <span class="status-badge" :class="metadata.Extraction_Status.toLowerCase()">
              {{ metadata.Extraction_Status }}
            </span>
          </div>
        </div>
        <div class="meta-section">
          <span class="label-text">Tags</span>
          <div class="tags-list" v-if="metadata.Keywords && metadata.Keywords.length > 0">
            <span v-for="tag in metadata.Keywords" :key="tag" class="tag-pill">{{ tag }}</span>
          </div>
          <div class="tag-input-container">
            <input
              v-model="newTag"
              type="text"
              class="input-tag"
              placeholder="Add a tag..."
              @keyup.enter="addTag"
              :disabled="isAddingTag"
            />
            <button
              class="btn btn-tag"
              @click="addTag"
              :disabled="!newTag || isAddingTag"
            >
              <span v-if="isAddingTag">...</span>
              <span v-else>+</span>
            </button>
          </div>
        </div>
        <div class="actions">
          <button class="btn w-full" @click="findSimilar" :disabled="metadata.Extraction_Status !== 'COMPLETED'">
            Find Visually Similar
          </button>
          <button class="btn btn-outline danger w-full" @click="deleteImage">
            Delete Image
          </button>
        </div>
      </aside>
    </div>
  </div>
</template>
<style scoped>
@keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }
.loading, .error { text-align: center; padding: 4rem; color: var(--text-secondary); }
.detail-layout {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-xl);
  align-items: start;
}
@media (min-width: 1024px) {
  .detail-layout { grid-template-columns: 8fr 4fr; }
}
.image-container {
  background: var(--bg-element);
  border-radius: 4px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}
.full-img {
  max-width: 100%;
  max-height: 80vh;
  object-fit: contain;
  display: block;
}
.meta-sidebar {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 2rem;
  position: sticky;
  top: 100px;
}
.image-title {
  font-family: var(--font-sans);
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 2rem;
  word-break: break-all;
}
.meta-section {
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1.5rem;
  margin-bottom: 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
.meta-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.meta-val {
  font-family: var(--font-mono);
  font-size: 0.9rem;
  color: var(--text-primary);
}
.tags-list {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  margin-top: 0.5rem;
}
.tag-input-container {
  display: flex;
  margin-top: 0.75rem;
  gap: 0.25rem;
}
.input-tag {
  flex: 1;
  background: var(--bg-element);
  border: 1px solid var(--border-subtle);
  color: var(--text-primary);
  border-radius: 4px;
  padding: 0.5rem;
  font-size: 0.9rem;
  outline: none;
}
.input-tag::placeholder { color: var(--text-muted); }
.input-tag:focus { border-color: var(--color-accent); }
.btn-tag {
  background: var(--color-accent);
  color: #fff;
  border: none;
  border-radius: 4px;
  width: 36px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 1.2rem;
  line-height: 1;
}
.btn-tag:disabled { opacity: 0.5; cursor: not-allowed; }
.actions { display: flex; flex-direction: column; gap: 1rem; }
.danger { color: var(--color-danger); border-color: var(--color-danger); }
.danger:hover { background: var(--color-danger); color: #fff; }
/* --- CRUELTY OVERRIDES --- */
:root.cruelty .image-container { background: #000; border: 8px dashed var(--color-accent); border-radius: 0; }
:root.cruelty .meta-sidebar { background: #111; border: 4px solid var(--border-strong); border-radius: 0; }
:root.cruelty .image-title { font-family: 'Impact'; font-size: 2.5rem; color: #00FF00; text-transform: uppercase; }
</style>