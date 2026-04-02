<script setup lang="ts">
import { ref, onMounted } from 'vue';
import http from '../api/http-client';

const props = defineProps({
  placeholder: { type: String, default: 'Search tags...' },
  clearOnSelect: { type: Boolean, default: true },
  disabled: { type: Boolean, default: false }
});

const emit = defineEmits(['select']);

const searchQuery = ref('');
const filteredKeywords = ref<string[]>([]);
const defaultKeywords = ref<string[]>([]);
const isSearchFocused = ref(false);
const highlightedIndex = ref(-1);
let debounceTimer: ReturnType<typeof setTimeout> | null = null;

onMounted(async () => {
  try {
    const res = await http.get('/images/keywords/popular?limit=8');
    defaultKeywords.value = res.data;
  } catch (e) {}
});

const onFocus = () => {
  isSearchFocused.value = true;
  if (searchQuery.value.trim().length < 2) {
    filteredKeywords.value = defaultKeywords.value;
  }
};

const onInput = () => {
  highlightedIndex.value = -1;
  if (debounceTimer) clearTimeout(debounceTimer);
  
  if (searchQuery.value.trim().length < 2) {
    filteredKeywords.value = defaultKeywords.value;
    return;
  }

  debounceTimer = setTimeout(async () => {
    try {
      const res = await http.get(`/images/keywords/search?q=${encodeURIComponent(searchQuery.value.trim())}`);
      filteredKeywords.value = res.data.slice(0, 8);
    } catch (e) {
      filteredKeywords.value = [];
    }
  }, 200); // Fast 200ms debounce for smooth typing
};

const handleBlur = () => {
  setTimeout(() => {
    isSearchFocused.value = false;
    highlightedIndex.value = -1;
  }, 200);
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
      @focus="onFocus"
      @blur="handleBlur"
      @keydown="handleKeydown"
      class="autocomplete-input"
    />
    <Transition name="fade">
      <ul class="autocomplete-dropdown" v-if="isSearchFocused && filteredKeywords.length > 0">
        <TransitionGroup name="list">
          <li 
            v-for="(kw, i) in filteredKeywords" 
            :key="kw"
            :class="{ highlighted: i === highlightedIndex }"
            @mousedown.prevent="selectTag(kw)"
          >
            <span class="material-symbols-outlined tag-icon">tag</span>
            {{ kw }}
          </li>
        </TransitionGroup>
      </ul>
    </Transition>
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
  top: calc(100% + 8px);
  left: 0;
  right: 0;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  box-shadow: var(--shadow-subtle);
  list-style: none;
  margin: 0;
  padding: 0.5rem;
  z-index: 100;
  max-height: 300px;
  overflow-y: auto;
  overflow-x: hidden;
}

.autocomplete-dropdown li {
  padding: 0.5rem 0.75rem;
  font-size: 0.9rem;
  cursor: pointer;
  color: var(--text-primary);
  border-radius: 4px;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  transition: background 0.1s ease, color 0.1s ease;
}

.tag-icon {
  font-size: 1.1rem;
  color: var(--text-muted);
}

.autocomplete-dropdown li:hover,
.autocomplete-dropdown li.highlighted {
  background: var(--bg-element);
  color: var(--color-accent);
}

.autocomplete-dropdown li:hover .tag-icon,
.autocomplete-dropdown li.highlighted .tag-icon {
  color: var(--color-accent);
}

/* Animations */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.2s ease, transform 0.2s ease;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.list-move, 
.list-enter-active, 
.list-leave-active {
  transition: all 0.3s ease;
}
.list-enter-from, 
.list-leave-to {
  opacity: 0;
  transform: translateX(-15px);
}
.list-leave-active {
  position: absolute;
}
</style>