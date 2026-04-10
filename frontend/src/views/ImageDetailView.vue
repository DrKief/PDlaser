<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../api/http-client";
const route = useRoute();
const router = useRouter();
const imageId = route.params.id as string;
const metadata = ref<any>(null);
const isLoading = ref(true);

const newTag = ref("");
const isAddingTag = ref(false);

const currentUser = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const payloadStr = atob(payloadBase64.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(payloadStr);
  } catch (e) {
    return null;
  }
});

const canDelete = computed(() => {
  if (!currentUser.value) return false;
  if (currentUser.value.role === "ROLE_ADMIN") return true;
  if (
    metadata.value &&
    metadata.value.user_id &&
    currentUser.value.userId === metadata.value.user_id
  )
    return true;
  return false;
});

const addTag = async () => {
  const tag = newTag.value.trim();
  if (!tag) return;
  isAddingTag.value = true;
  try {
    await http.put(`/images/${imageId}/keywords?tag=${encodeURIComponent(tag)}`);
    if (!metadata.value.Keywords) metadata.value.Keywords = [];
    if (!metadata.value.Keywords.find((k: any) => k.keyword === tag)) {
      metadata.value.Keywords.push({ keyword: tag, isAi: false });
    }
    newTag.value = "";
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
    router.push("/");
  } catch (error) {
    console.error(error);
  }
};

// Optional: Open advanced similarity search in gallery (as Sidebar triggers similarity from GalleryView)
const findSimilar = () => {
  router.push({ path: "/", query: { sourceId: imageId } });
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
            <span class="meta-val">{{ metadata.format }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Resolution</span>
            <span class="meta-val">{{ metadata.width }} x {{ metadata.height }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Extraction Status</span>
            <span class="status-badge" :class="metadata.extraction_status?.toLowerCase()">
              {{ metadata.extraction_status }}
            </span>
          </div>
        </div>

        <div class="meta-section" v-if="metadata.photographer_name">
          <div class="meta-row" v-if="metadata.photographer_name">
            <span class="label-text">Photographer</span>
            <span class="meta-val">{{ metadata.photographer_name }}</span>
          </div>
          <div class="meta-row" v-if="metadata.camera_make">
            <span class="label-text">Camera</span>
            <span class="meta-val">{{ metadata.camera_make }}</span>
          </div>
          <div class="meta-row" v-if="metadata.location_country">
            <span class="label-text">Location</span>
            <span class="meta-val">{{ metadata.location_country }}</span>
          </div>
          <div class="meta-row" v-if="metadata.stats_downloads != null">
            <span class="label-text">Downloads</span>
            <span class="meta-val">{{ metadata.stats_downloads }}</span>
          </div>
          <div
            class="meta-row"
            v-if="metadata.description"
            style="flex-direction: column; align-items: flex-start; gap: 0.5rem; margin-top: 1rem"
          >
            <span class="label-text">Description</span>
            <span class="meta-val" style="color: var(--text-secondary); line-height: 1.4">{{
              metadata.description
            }}</span>
          </div>
        </div>

        <div class="meta-section">
          <span class="label-text">Tags</span>
          <div class="tags-list" v-if="metadata.Keywords && metadata.Keywords.length > 0">
            <span
              v-for="tag in metadata.Keywords"
              :key="tag.keyword"
              class="tag-pill"
              :class="{ 'ai-tag': tag.isAi }"
            >
              {{ tag.keyword }}
            </span>
          </div>
          <div
            class="tag-input-container"
            style="position: relative; display: flex; align-items: center; margin-top: 0.75rem"
          >
            <input
              v-model="newTag"
              type="text"
              class="input-tag"
              placeholder="Type a tag and press enter..."
              @keyup.enter="addTag"
              :disabled="isAddingTag"
              style="width: 100%; padding-right: 2.5rem"
            />
            <span
              v-if="isAddingTag"
              class="material-symbols-outlined"
              style="
                position: absolute;
                right: 10px;
                color: var(--text-secondary);
                animation: spin 1s linear infinite;
              "
              >sync</span
            >
          </div>
        </div>
        <div class="actions">
          <button
            class="btn w-full"
            @click="findSimilar"
            :disabled="metadata.extraction_status !== 'COMPLETED'"
          >
            Find Visually Similar
          </button>
          <button class="btn btn-outline danger w-full" @click="deleteImage" v-if="canDelete">
            Delete Image
          </button>
        </div>
      </aside>
    </div>
  </div>
</template>

<style scoped>
@keyframes spin {
  100% {
    transform: rotate(360deg);
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.loading,
.error {
  text-align: center;
  padding: 4rem;
  color: var(--text-secondary);
}

.detail-layout {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-xl);
  align-items: start;
}

@media (min-width: 1024px) {
  .detail-layout {
    grid-template-columns: 8fr 4fr;
  }
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

.input-tag {
  background: var(--bg-element);
  border: 1px solid var(--border-subtle);
  color: var(--text-primary);
  border-radius: 4px;
  padding: 0.5rem;
  font-size: 0.9rem;
  outline: none;
}

.input-tag::placeholder {
  color: var(--text-muted);
}

.input-tag:focus {
  border-color: var(--color-accent);
}

.actions {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.danger {
  color: var(--color-danger);
  border-color: var(--color-danger);
}

.danger:hover {
  background: var(--color-danger);
  color: #fff;
}
</style>
