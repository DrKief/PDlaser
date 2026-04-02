<script setup lang="ts">
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import http from "../api/http-client";
import TagAutocomplete from "../shared/TagAutocomplete.vue";

const props = defineProps<{ initialSourceId?: number | null }>();
const emit = defineEmits(['close', 'executeSimilarity']);

const router = useRouter();

const activeTab = ref<"attributes" | "similarity">(props.initialSourceId ? "similarity" : "attributes");

// Attributes State
const searchKeywords = ref<string[]>([]);

// Similarity State
const uploadMode = ref<"db" | "ephemeral">("db");
const selectedDbId = ref<number | null>(props.initialSourceId || null);
const uploadTarget = ref<File | null>(null);
const similarityAlgorithm = ref("semantic");
const similarityCount = ref(10);
const errorMsg = ref("");

// If the Gallery passes a new ID, update the sidebar automatically
watch(() => props.initialSourceId, (newId) => {
  if (newId) {
    selectedDbId.value = newId;
    activeTab.value = "similarity";
    uploadMode.value = "db";
  }
});

const onFileSelect = (e: Event) => {
  const target = e.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    uploadTarget.value = target.files[0];
  }
};

const addTag = (tag: string) => {
  if (tag && !searchKeywords.value.includes(tag)) {
    searchKeywords.value.push(tag);
  }
};

const removeTag = (tag: string) => {
  searchKeywords.value = searchKeywords.value.filter((t: string) => t !== tag);
};

const triggerAttributeSearch = () => {
  if (searchKeywords.value.length === 0) return;
  router.push({ path: '/', query: { tags: searchKeywords.value.join(',') } });
  emit('close');
};

const triggerSimilaritySearch = async () => {
  errorMsg.value = "";
  
  try {
    let results = [];
    if (uploadMode.value === "db") {
      if (!selectedDbId.value) return (errorMsg.value = "Please enter a database Image ID.");
      const res = await http.get(`/images/${selectedDbId.value}/similar`, {
        params: { number: similarityCount.value, descriptor: similarityAlgorithm.value }
      });
      results = res.data;
    } else {
      if (!uploadTarget.value) return (errorMsg.value = "Please select an image to upload.");
      const formData = new FormData();
      formData.append("file", uploadTarget.value);
      formData.append("number", similarityCount.value.toString());
      formData.append("descriptor", similarityAlgorithm.value);
      
      const res = await http.post("/images/search/ephemeral", formData, {
        headers: { "Content-Type": "multipart/form-data" }
      });
      results = res.data;
    }
    
    emit('executeSimilarity', results);
    emit('close');
  } catch (e) {
    errorMsg.value = "Similarity scan failed.";
  }
};
</script>

<template>
  <aside class="search-sidebar">
    <div class="sidebar-header">
      <h3>Filters & Matches</h3>
      <button @click="$emit('close')" class="btn-icon material-symbols-outlined">close</button>
    </div>
    
    <div class="tab-switcher">
      <button :class="{ active: activeTab === 'attributes' }" @click="activeTab = 'attributes'">Tags</button>
      <button :class="{ active: activeTab === 'similarity' }" @click="activeTab = 'similarity'">Visual Match</button>
    </div>

    <div v-show="activeTab === 'attributes'" class="sidebar-content">
       <p class="label-text mb-2">Must Contain Tags (AND condition)</p>
       <div class="tags-list mb-4" style="display: flex; gap: 0.5rem; flex-wrap: wrap;">
          <span v-for="tag in searchKeywords" :key="tag" class="tag-pill">
            {{ tag }} <button @click="removeTag(tag)" class="tag-remove">&times;</button>
          </span>
       </div>
       <TagAutocomplete @select="addTag" placeholder="Add a tag..." />
       <button class="btn w-full mt-4" @click="triggerAttributeSearch">Apply Filters</button>
    </div>

    <div v-show="activeTab === 'similarity'" class="sidebar-content">
      <div class="form-group mb-4">
        <label class="label-text">Source Image Type</label>
        <select v-model="uploadMode" style="margin-top: 0.5rem;">
          <option value="db">From Database (ID)</option>
          <option value="ephemeral">Upload Temporary Target</option>
        </select>
      </div>

      <div class="form-group" v-if="uploadMode === 'db'">
        <label class="label-text">Image ID</label>
        <input type="number" v-model="selectedDbId" placeholder="e.g., 15" />
      </div>

      <div class="form-group" v-if="uploadMode === 'ephemeral'">
        <label class="label-text">Upload Image</label>
        <input type="file" @change="onFileSelect" accept="image/*" style="margin-top: 0.5rem; padding-bottom: 0;" />
      </div>

      <div class="form-group mt-4">
        <label class="label-text">Algorithm</label>
        <select v-model="similarityAlgorithm" style="margin-top: 0.5rem;">
          <option value="semantic">Semantic (AI)</option>
          <option value="cielab">CIELAB (Human Vision)</option>
          <option value="gradient">HOG (Shape/Edges)</option>
        </select>
      </div>

      <div class="form-group mt-4">
        <label class="label-text">Result Limit</label>
        <input type="number" v-model.number="similarityCount" min="1" max="50" />
      </div>

      <button class="btn w-full mt-4" @click="triggerSimilaritySearch">Find Matches</button>
      <p v-if="errorMsg" class="error-text">{{ errorMsg }}</p>
    </div>
  </aside>
</template>

<style scoped>
.search-sidebar {
  width: 320px;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  height: calc(100vh - 120px);
  position: sticky;
  top: 100px;
  padding: 1.5rem;
  overflow-y: auto;
  box-shadow: var(--shadow-subtle);
}
.sidebar-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 1.5rem; }
.sidebar-header h3 { margin: 0; font-size: 1.25rem; }
.tab-switcher { display: flex; border-bottom: 1px solid var(--border-subtle); margin-bottom: 1.5rem; }
.tab-switcher button { flex: 1; background: none; border: none; padding: 0.75rem; cursor: pointer; color: var(--text-secondary); font-weight: 500;}
.tab-switcher button.active { border-bottom: 2px solid var(--color-accent); color: var(--text-primary); }
.mb-2 { margin-bottom: 0.5rem; }
.mb-4 { margin-bottom: 1rem; }
.mt-4 { margin-top: 1rem; }
.w-full { width: 100%; }
.btn-icon { background: none; border: none; cursor: pointer; color: var(--text-secondary); }
.btn-icon:hover { color: var(--color-danger); }
.tag-pill { background: var(--bg-element); padding: 0.25rem 0.5rem; border-radius: 4px; font-size: 0.8rem; display: flex; align-items: center; gap: 0.25rem; }
.tag-remove { background: none; border: none; cursor: pointer; color: var(--text-secondary); padding: 0 2px; }
.tag-remove:hover { color: var(--color-danger); }
</style>