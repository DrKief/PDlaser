<script setup lang="ts">
import { ref, watch, computed } from "vue";
import http from "../api/http-client";

const props = defineProps<{ initialSourceId?: number | null }>();
const emit = defineEmits(["close", "executeSimilarity"]);

// Similarity State
const uploadMode = ref<"db" | "ephemeral">("db");
const selectedDbId = ref<number | null>(props.initialSourceId || null);
const uploadTarget = ref<File | null>(null);
const similarityAlgorithm = ref("semantic");
const similarityCount = ref(10);
const errorMsg = ref("");

watch(
  () => props.initialSourceId,
  (newId) => {
    if (newId) {
      selectedDbId.value = newId;
      uploadMode.value = "db";
    }
  },
);

const isSourceSelected = computed(() => {
  if (uploadMode.value === "db") return !!selectedDbId.value;
  return !!uploadTarget.value;
});

const onFileSelect = (e: Event) => {
  const target = e.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    uploadTarget.value = target.files[0] || null;
  }
};

const triggerSimilaritySearch = async () => {
  errorMsg.value = "";

  try {
    let results = [];
    if (uploadMode.value === "db") {
      if (!selectedDbId.value) return (errorMsg.value = "Please enter a database Image ID.");
      const res = await http.get(`/images/${selectedDbId.value}/similar`, {
        params: { number: similarityCount.value, descriptor: similarityAlgorithm.value },
      });
      results = res.data;
    } else {
      if (!uploadTarget.value) return (errorMsg.value = "Please select an image to upload.");
      const formData = new FormData();
      formData.append("file", uploadTarget.value);
      formData.append("number", similarityCount.value.toString());
      formData.append("descriptor", similarityAlgorithm.value);

      const res = await http.post("/images/search/ephemeral", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      results = res.data;
    }

    // NEW: Emit the complete payload including the source context
    emit("executeSimilarity", {
      results: results,
      sourceId: uploadMode.value === "db" ? selectedDbId.value : null,
      isEphemeral: uploadMode.value === "ephemeral",
      fileUrl: uploadTarget.value ? URL.createObjectURL(uploadTarget.value) : null,
    });

    emit("close");
  } catch (e) {
    errorMsg.value = "Similarity scan failed.";
  }
};
</script>

<template>
  <aside class="search-sidebar">
    <div class="sidebar-header">
      <h3>Visual Match</h3>
      <button
        @click="$emit('close')"
        class="btn-icon material-symbols-outlined"
        title="Close Filters"
      >
        close
      </button>
    </div>

    <div class="sidebar-content">
      <p v-if="!isSourceSelected" class="help-text warning-text">
        <span class="material-symbols-outlined" style="font-size: 1.2rem">info</span>
        Select a gallery image or upload a target file to enable similarity settings.
      </p>

      <div class="form-group mb-4">
        <label class="label-text">Source Target</label>
        <select v-model="uploadMode" style="margin-top: 0.5rem">
          <option value="db">From Database (ID)</option>
          <option value="ephemeral">Upload Temporary Image</option>
        </select>
      </div>

      <div class="form-group" v-if="uploadMode === 'db'">
        <label class="label-text">Image ID</label>
        <input type="number" v-model="selectedDbId" placeholder="e.g., 15" />
      </div>

      <div class="form-group" v-if="uploadMode === 'ephemeral'">
        <label class="label-text">Upload Image</label>
        <input
          type="file"
          @change="onFileSelect"
          accept="image/*"
          style="margin-top: 0.5rem; padding-bottom: 0"
        />
      </div>

      <fieldset :disabled="!isSourceSelected" style="border: none; padding: 0; margin: 0">
        <div class="form-group mt-4" :class="{ 'disabled-group': !isSourceSelected }">
          <label class="label-text">Algorithm</label>
          <select v-model="similarityAlgorithm" style="margin-top: 0.5rem">
            <option value="semantic">Semantic (AI)</option>
            <option value="cielab">CIELAB (Human Vision)</option>
            <option value="gradient">HOG (Shape/Edges)</option>
          </select>
        </div>

        <div class="form-group mt-4" :class="{ 'disabled-group': !isSourceSelected }">
          <label class="label-text">Result Limit</label>
          <input type="number" v-model.number="similarityCount" min="1" max="50" />
        </div>

        <button
          class="btn w-full mt-4"
          @click="triggerSimilaritySearch"
          :disabled="!isSourceSelected"
        >
          Find Matches
        </button>
      </fieldset>

      <p v-if="errorMsg" class="error-text">{{ errorMsg }}</p>
    </div>
  </aside>
</template>

<style scoped>
.search-sidebar {
  position: fixed;
  inset: 0;
  width: 100vw;
  height: 100vh;
  z-index: 100;
  background: var(--bg-surface);
  padding: max(1.5rem, env(safe-area-inset-top)) max(1.5rem, env(safe-area-inset-right))
    max(1.5rem, env(safe-area-inset-bottom)) max(1.5rem, env(safe-area-inset-left));
  overflow-y: auto;
  box-shadow: var(--shadow-subtle);
  border: none;
  border-radius: 0;
}

@media (min-width: 768px) {
  .search-sidebar {
    position: sticky;
    top: 120px;
    width: 320px;
    height: calc(100vh - 140px);
    border: 1px solid var(--border-subtle);
    border-radius: 8px;
    z-index: auto;
    padding: 1.5rem;
  }
}
.sidebar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1rem;
}
.sidebar-header h3 {
  margin: 0;
  font-size: 1.25rem;
  font-family: var(--font-headline);
}
.mb-4 {
  margin-bottom: 1rem;
}
.mt-4 {
  margin-top: 1rem;
}
.w-full {
  width: 100%;
}

.btn-icon {
  background: none;
  border: none;
  cursor: pointer;
  color: var(--text-secondary);
  padding: 0;
}
.btn-icon:hover {
  color: var(--color-danger);
}

.disabled-group {
  opacity: 0.5;
  pointer-events: none;
  transition: opacity 0.2s;
}
.help-text {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.85rem;
  padding: 0.75rem;
  border-radius: 4px;
  background: var(--bg-element);
  color: var(--text-secondary);
  margin-bottom: 1rem;
}
.warning-text {
  border-left: 3px solid var(--color-accent);
}
</style>
