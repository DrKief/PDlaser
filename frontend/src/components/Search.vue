<template>
  <div class="search-bar">
    <input
      v-model="query"
      type="text"
      placeholder="Search..."
      @input="handleSearch"
      class="search-input"
    />
    <button @click="clearSearch">Clear</button>
  </div>
  <div class="image-search">
    <Upload mode="search" @file-selected="handleFileUpload" />
  <div class="histogram-select">
    <label for="histogram">Select Histogram:</label>
    <select id="histogram" v-model="selectedHistogram">
      <option value="gradient">Gradient/Orientation (1D)</option>
      <option value="saturation">Saturation (2D)</option>
      <option value="rgb">RGB (3D)</option>
    </select>
  </div>
  </div>
</template>

<script>
import Upload from "./Upload.vue";

export default {
  name: "Search",
  components: {
    Upload,
  },
  data() {
    return {
      query: "",
    };
  },
  // no changes needed to methods as the event payload is the same
  methods: {
    handleSearch() {
      this.$emit("search", this.query);
    },
    handleFileUpload(file) {
      if (file) {
        this.$emit("search-image", file);
      }
    },
    clearSearch() {
      this.query = "";
      this.$emit("search", "");
    },
  },
};
</script>

<style scoped>
.search-bar {
  display: flex;
  gap: 8px;
  margin-bottom: 16px;
}

.search-input {
  flex: 1;
  padding: 8px 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}

.search-input:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.1);
}

.clear-btn {
  padding: 8px 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}
.clear-btn:hover {
  background-color: #f0f0f0;
}
.image-search {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
