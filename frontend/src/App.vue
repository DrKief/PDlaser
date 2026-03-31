<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import http from "./http-api";

const router = useRouter();
const theme = ref("light");
let audioCtx: AudioContext | null = null;

// Top Search Bar State
const searchQuery = ref("");
const allKeywords = ref<string[]>([]);
const isSearchFocused = ref(false);

const filteredKeywords = computed(() => {
  if (!searchQuery.value) return [];
  const query = searchQuery.value.toLowerCase();
  return allKeywords.value.filter(k => k.toLowerCase().includes(query)).slice(0, 8); // Limit autocomplete to 8
});

onMounted(async () => {
  // Setup Theme
  const savedTheme = localStorage.getItem('theme');
  if (savedTheme) {
    theme.value = savedTheme;
  } else if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
    theme.value = 'dark';
  } else {
    theme.value = 'light';
  }
  document.documentElement.className = theme.value;
  
  document.addEventListener('click', handleGlobalClick);

  // Fetch all keywords for the global autocomplete search bar
  try {
    const res = await http.get(`/images/keywords`);
    allKeywords.value = res.data;
  } catch (e) {
    console.error("Failed to load keywords for search bar");
  }
});

const handleSearchBlur = () => {
  setTimeout(() => isSearchFocused.value = false, 200);
};

const executeSearch = (tag: string) => {
  searchQuery.value = tag;
  isSearchFocused.value = false;
  // Route to the gallery with the selected tag as a filter
  router.push({ path: '/', query: { tags: tag } });
  searchQuery.value = ""; // Clear input after searching
};

// Cruelty Audio
const playPainSound = () => {
  if (theme.value !== 'cruelty') return;
  
  if (!audioCtx) {
    audioCtx = new (window.AudioContext || (window as any).webkitAudioContext)();
  }
  
  if (audioCtx.state === 'suspended') {
    audioCtx.resume();
  }

  const osc = audioCtx.createOscillator();
  const gain = audioCtx.createGain();
  
  osc.type = 'sawtooth';
  osc.frequency.setValueAtTime(Math.random() * 1000 + 100, audioCtx.currentTime);
  osc.frequency.exponentialRampToValueAtTime(10, audioCtx.currentTime + 0.1);
  
  gain.gain.setValueAtTime(0.3, audioCtx.currentTime);
  gain.gain.exponentialRampToValueAtTime(0.01, audioCtx.currentTime + 0.1);
  
  osc.connect(gain);
  gain.connect(audioCtx.destination);
  
  osc.start();
  osc.stop(audioCtx.currentTime + 0.2);
};

const handleGlobalClick = () => {
  playPainSound();
};

const toggleTheme = (target: string) => {
  theme.value = target;
  document.documentElement.className = theme.value;
  localStorage.setItem('theme', theme.value);
};

</script>

<template>
  <div class="crt-overlay"></div>
  <div class="app-layout">
    
    <header class="top-nav">
      <div class="nav-left">
        <router-link to="/" class="logo">pdl.</router-link>
        
        <!-- Quick Tag Search Bar -->
        <div class="global-search" @blur="handleSearchBlur">
          <span class="material-symbols-outlined search-icon">search</span>
          <input 
            type="text" 
            placeholder="Search tags..." 
            v-model="searchQuery"
            @focus="isSearchFocused = true"
            @keydown.enter="filteredKeywords.length ? executeSearch(filteredKeywords[0]) : executeSearch(searchQuery)"
          />
          <ul class="autocomplete-dropdown" v-if="isSearchFocused && filteredKeywords.length > 0">
            <li 
              v-for="kw in filteredKeywords" 
              :key="kw"
              @mousedown.prevent="executeSearch(kw)"
            >
              {{ kw }}
            </li>
          </ul>
        </div>
      </div>
      
      <div class="nav-right">
        <nav class="nav-links">
          <router-link to="/search">Advanced</router-link>
          <router-link to="/about">About</router-link>
        </nav>
        
        <div class="divider"></div>
        
        <button class="cruelty-toggle" @click="toggleTheme(theme === 'cruelty' ? 'light' : 'cruelty')">
          {{ theme === 'cruelty' ? 'REVERT CRUELTY' : 'button' }}
        </button>
        
        <button class="theme-toggle" @click="toggleTheme(theme === 'light' ? 'dark' : 'light')" v-if="theme !== 'cruelty'" title="Toggle Theme">
          <span class="material-symbols-outlined">contrast</span>
        </button>
        
        <router-link to="/upload" class="btn upload-btn">Upload</router-link>
      </div>
    </header>

    <main class="content-canvas">
      <router-view></router-view>
    </main>

  </div>
</template>

<style scoped>
.app-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.top-nav {
  position: sticky;
  top: 0;
  z-index: 50;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background: color-mixin(in oklch, var(--bg-surface) 90%, transparent);
  backdrop-filter: blur(12px);
  border-bottom: 1px solid var(--border-subtle);
}

.nav-left {
  display: flex;
  align-items: center;
  gap: 2.5rem;
  flex: 1;
}

.logo {
  font-family: var(--font-headline);
  font-size: 2rem;
  font-style: italic;
  font-weight: 700;
  color: var(--text-primary);
  text-decoration: none;
  transition: opacity 0.2s;
}

.logo:hover { opacity: 0.7; }

/* Global Search Bar */
.global-search {
  position: relative;
  display: flex;
  align-items: center;
  background: var(--bg-element);
  border-radius: 20px;
  padding: 0 1rem;
  width: 100%;
  max-width: 300px;
  border: 1px solid transparent;
  transition: border-color 0.2s;
}

.global-search:focus-within {
  border-color: var(--border-strong);
}

.search-icon {
  color: var(--text-secondary);
  font-size: 1.25rem;
}

.global-search input {
  border: none;
  background: transparent;
  padding: 0.5rem 0.5rem;
  font-size: 0.9rem;
  color: var(--text-primary);
  width: 100%;
  outline: none;
}

.autocomplete-dropdown {
  position: absolute;
  top: 110%;
  left: 0;
  width: 100%;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  box-shadow: var(--shadow-subtle);
  list-style: none;
  padding: 0.5rem 0;
  margin: 0;
  z-index: 100;
}

.autocomplete-dropdown li {
  padding: 0.5rem 1rem;
  font-size: 0.9rem;
  color: var(--text-primary);
  cursor: pointer;
}

.autocomplete-dropdown li:hover {
  background: var(--bg-element);
}

.nav-right {
  display: flex;
  align-items: center;
  gap: 1.5rem;
}

.nav-links {
  display: none;
  gap: 1.5rem;
}

@media (min-width: 768px) {
  .nav-links { display: flex; }
}

.nav-links a {
  font-family: var(--font-sans);
  color: var(--text-secondary);
  text-decoration: none;
  font-size: 0.95rem;
  font-weight: 500;
  transition: color 0.2s;
}

.nav-links a:hover, .nav-links a.router-link-active {
  color: var(--text-primary);
}

.divider {
  height: 24px;
  width: 1px;
  background: var(--border-subtle);
}

.cruelty-toggle {
  background: none;
  border: none;
  font-family: var(--font-sans);
  text-transform: uppercase;
  letter-spacing: 0.1em;
  font-size: 0.7rem;
  font-weight: 700;
  color: var(--text-muted);
  cursor: pointer;
  transition: color 0.2s;
}

.cruelty-toggle:hover { color: var(--color-danger); }

.theme-toggle {
  background: none;
  border: none;
  color: var(--text-secondary);
  cursor: pointer;
  display: flex;
  align-items: center;
}

.theme-toggle:hover { color: var(--text-primary); }

.upload-btn {
  padding: 0.5rem 1.25rem;
}

.content-canvas {
  flex: 1;
  padding: 2rem;
  width: 100%;
}

@media (min-width: 1024px) {
  .content-canvas { padding: 3rem 4rem; }
}

/* --- CRUELTY OVERRIDES --- */
:root.cruelty .top-nav { background: #FF0000; border-bottom: 4px solid var(--border-strong); padding: 1.5rem 2rem; }
:root.cruelty .logo { font-family: 'Impact'; font-style: normal; color: #fff; font-size: 3rem; transform: rotate(-3deg); }
:root.cruelty .global-search { background: #000; border-radius: 0; border: 4px solid var(--color-accent); }
:root.cruelty .global-search input { color: #00FF00; font-family: 'Impact'; font-size: 1.25rem; }
:root.cruelty .autocomplete-dropdown { background: #000; border: 4px solid #fff; border-radius: 0; }
:root.cruelty .autocomplete-dropdown li { font-family: 'Impact'; color: #00FF00; }
:root.cruelty .autocomplete-dropdown li:hover { background: #FF00FF; color: #fff; }
:root.cruelty .nav-links a { font-family: 'Impact'; font-size: 1.5rem; color: #fff; text-transform: uppercase; }
:root.cruelty .cruelty-toggle { background: #000; color: #00FF00; font-family: 'Impact'; font-size: 1rem; padding: 0.5rem 1rem; border: 2px solid #fff; animation: pulse 0.5s infinite; }

@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.1); }
  100% { transform: scale(1); }
}
</style>