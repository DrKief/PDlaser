<script setup lang="ts">
import { ref } from 'vue';
import http from '../api/http-client';

const props = defineProps({
  placeholder: { type: String, default: 'Search tags...' },
  clearOnSelect: { type: Boolean, default: true },
  disabled: { type: Boolean, default: false }
});

const emit = defineEmits(['select']);

const searchQuery = ref('');
const filteredKeywords = ref<string[]>([]);
const isSearchFocused = ref(false);
const highlightedIndex = ref(-1);
let debounceTimer: ReturnType<typeof setTimeout> | null = null;

const onInput = () => {
  highlightedIndex.value = -1;
  if (debounceTimer) clearTimeout(debounceTimer);
  
  debounceTimer = setTimeout(async () => {
    if (searchQuery.value.trim().length < 2) {
      filteredKeywords.value = [];
      return;
    }
    try {
      const res = await http.get(`/images/keywords/search?q=${encodeURIComponent(searchQuery.value.trim())}`);
      filteredKeywords.value = res.data.slice(0, 8);
    } catch (e) {
      console.error("Failed to fetch suggestions", e);
      filteredKeywords.value = [];
    }
  }, 300);
};

const handleBlur = () => {
  setTimeout(() => {
    isSearchFocused.value = false;
    highlightedIndex.value = -1;
  }, 150);
};

const handleKeydown = (e: KeyboardEvent) => {
  if (e.key === 'Escape') {
    isSearchFocused.value = false;
    return;
  }
  
  if (!filteredKeywords.value.length) {
    if (e.key === 'Enter') {
      e.preventDefault();
      selectTag(searchQuery.value);
    }
    return;
  }

  if (e.key === 'ArrowDown') {
    e.preventDefault();
    highlightedIndex.value = Math.min(highlightedIndex.value + 1, filteredKeywords.value.length - 1);
  } else if (e.key === 'ArrowUp') {
    e.preventDefault();
    highlightedIndex.value = Math.max(highlightedIndex.value - 1, 0);
  } else if (e.key === 'Enter') {
    e.preventDefault();
    const selected = filteredKeywords.value[highlightedIndex.value];
    if (highlightedIndex.value >= 0 && selected) {
      selectTag(selected);
    } else {
      selectTag(searchQuery.value);
    }
  }
};

const selectTag = (tag: string) => {
  if (!tag.trim()) return;
  emit('select', tag.trim().toLowerCase());
  isSearchFocused.value = false;
  highlightedIndex.value = -1;
  filteredKeywords.value = [];
  if (props.clearOnSelect) {
    searchQuery.value = '';
  }
};
</script>

<template>
  <div class="autocomplete-wrapper" @blur="handleBlur">
    <input 
      type="text" 
      :placeholder="placeholder"
      :disabled="disabled"
      v-model="searchQuery"
      @input="onInput"
      @focus="isSearchFocused = true"
      @blur="handleBlur"
      @keydown="handleKeydown"
      class="autocomplete-input"
    />
    <ul class="autocomplete-dropdown" v-if="isSearchFocused && filteredKeywords.length > 0">
      <li 
        v-for="(kw, i) in filteredKeywords" 
        :key="kw"
        :class="{ highlighted: i === highlightedIndex }"
        @mousedown.prevent="selectTag(kw)"
      >
        {{ kw }}
      </li>
    </ul>
  </div>
</template>

<style scoped>
.autocomplete-wrapper {
  position: relative;
  width: 100%;
}

.autocomplete-input {
  width: 100%;
  border: none;
  background: transparent;
  outline: none;
  color: inherit;
  font-size: inherit;
  padding: inherit;
}

.autocomplete-dropdown {
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 4px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
  list-style: none;
  margin: 4px 0 0;
  padding: 4px 0;
  z-index: 100;
  max-height: 240px;
  overflow-y: auto;
}

.autocomplete-dropdown li {
  padding: 0.5rem 0.75rem;
  font-size: 0.875rem;
  cursor: pointer;
  color: var(--text-primary);
}

.autocomplete-dropdown li:hover,
.autocomplete-dropdown li.highlighted {
  background: var(--bg-element);
}


</style>