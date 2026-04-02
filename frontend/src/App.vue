<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useAuth } from "./composables/useAuth";
import TagAutocomplete from "./shared/TagAutocomplete.vue";
import http from "./api/http-client";

const { isLoggedIn, logout } = useAuth();
const router = useRouter();
const route = useRoute();
const theme = ref("light");
let audioCtx: AudioContext | null = null;
const popularTags = ref<string[]>([]);

const isAdmin = computed(() => {
  const token = localStorage.getItem('token');
  if (!token) return false;
  try {
    const payload = JSON.parse(atob(token.split(".")[1] || ""));
    return payload.role === 'ROLE_ADMIN';
  } catch (e) { 
    return false; 
  }
});

const username = computed(() => {
  const token = localStorage.getItem('token');
  if (!token) return '';
  try {
    const payload = JSON.parse(atob(token.split(".")[1] || ""));
    return payload.sub || payload.username || 'User';
  } catch (e) {
    return '';
  }
});

const fetchPopularTags = async () => {
  try {
    const res = await http.get('/images/keywords/popular?limit=15');
    popularTags.value = res.data;
  } catch (e) { console.error(e); }
};

const executeSearch = (tag: string) => {
  if (!tag) {
    router.push({ path: '/' });
  } else {
    router.push({ path: '/', query: { tags: tag } });
  }
};

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

const toggleTheme = async (target: string) => {
  theme.value = target;
  document.documentElement.className = theme.value;
  localStorage.setItem('theme', theme.value);

  const linkId = 'cruelty-theme-link';
  let link = document.getElementById(linkId) as HTMLLinkElement;
  
  if (target === 'cruelty') {
    if (!link) {
      link = document.createElement('link');
      link.id = linkId;
      link.rel = 'stylesheet';
      document.head.appendChild(link); 
      const cssUrl = (await import('./assets/theme-cruelty.css?url')).default;
      link.href = cssUrl;
    }
  } else {
    if (link) link.remove();
  }
};

onMounted(() => {
  const savedTheme = localStorage.getItem('theme');
  if (savedTheme) {
    theme.value = savedTheme;
  } else if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
    theme.value = 'dark';
  } else {
    theme.value = 'light';
  }
  toggleTheme(theme.value);
  document.addEventListener('click', handleGlobalClick);
  fetchPopularTags();
});
</script>

<template>
  <div class="crt-overlay"></div>
  <div class="app-layout">
    
    <header class="top-nav">
      <div class="nav-left">
        <router-link to="/" class="logo">pdl.</router-link>
      </div>
      
      <div class="nav-right">
        <nav class="nav-links">
          <router-link to="/about">About</router-link>
          <router-link v-if="isAdmin" to="/admin">Admin</router-link>
        </nav>
        <div class="divider"></div>
        <button class="cruelty-toggle" @click="toggleTheme(theme === 'cruelty' ? 'light' : 'cruelty')">
          {{ theme === 'cruelty' ? 'REVERT CRUELTY' : 'CRUELTY' }}
        </button>
        <button class="theme-toggle" @click="toggleTheme(theme === 'light' ? 'dark' : 'light')" v-if="theme !== 'cruelty'" title="Toggle Theme">
          <span class="material-symbols-outlined">contrast</span>
        </button>
        
        <template v-if="isLoggedIn">
          <router-link to="/profile" class="user-badge" style="font-family: var(--font-mono); color: var(--text-muted); font-size: 0.85rem; text-decoration: none;">@{{ username }}</router-link>
          <button @click="logout" class="btn logout-btn" style="background: none; border: none; cursor: pointer; color: var(--text-secondary); font-family: var(--font-sans); font-weight: 500;">Logout</button>
          <router-link to="/upload" class="btn upload-btn">Upload</router-link>
        </template>
        <template v-else>
          <router-link to="/login" class="btn login-btn" style="color: var(--text-secondary); font-family: var(--font-sans); font-weight: 500; text-decoration: none; background: transparent; border: none;">Login</router-link>
          <router-link to="/register" class="btn btn-outline" style="padding: 0.4rem 1rem; border-radius: 4px;">Register</router-link>
        </template>
      </div>
    </header>

    <div class="sub-nav">
      <div class="tags-scroll">
        <button 
          @click="executeSearch('')" 
          class="popular-tag-btn" 
          :class="{ 'active-tag': !route.query.tags && route.path === '/' }">
          Home
        </button>
        <button 
          v-for="tag in popularTags" 
          :key="tag" 
          @click="executeSearch(tag)" 
          class="popular-tag-btn" 
          :class="{ 'active-tag': route.query.tags === tag }">
          #{{ tag }}
        </button>
      </div>
      
      <div class="global-search">
        <span class="material-symbols-outlined search-icon">search</span>
        <TagAutocomplete @select="executeSearch" placeholder="Search any tag..." />
      </div>
    </div>

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

/* Slimmer Top Nav */
.top-nav {
  position: sticky;
  top: 0;
  z-index: 51;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.75rem 2rem;
  background: color-mixin(in oklch, var(--bg-surface) 95%, transparent);
  backdrop-filter: blur(12px);
  border-bottom: 1px solid var(--border-subtle);
}

.nav-left { display: flex; align-items: center; }

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

/* Sub Navigation */
.sub-nav {
  position: sticky;
  top: 66px; /* Sits directly beneath top-nav */
  z-index: 50;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem 2rem;
  background: var(--bg-element);
  border-bottom: 1px solid var(--border-subtle);
  gap: 1rem;
}

.tags-scroll {
  display: flex;
  gap: 1.5rem;
  overflow-x: auto;
  white-space: nowrap;
  scrollbar-width: none;
  align-items: center;
}
.tags-scroll::-webkit-scrollbar { display: none; }

.popular-tag-btn {
  background: none;
  border: none;
  font-family: var(--font-sans);
  font-size: 0.9rem;
  color: var(--text-secondary);
  cursor: pointer;
  font-weight: 500;
  transition: color 0.2s;
  padding: 0.25rem 0;
}
.popular-tag-btn:hover { color: var(--text-primary); }
.popular-tag-btn.active-tag { 
  color: var(--color-accent); 
  border-bottom: 2px solid var(--color-accent); 
}

.global-search {
  display: flex;
  align-items: center;
  background: var(--bg-surface);
  border-radius: 4px;
  padding: 0.25rem 0.75rem;
  border: 1px solid var(--border-subtle);
  max-width: 250px;
  width: 100%;
  transition: border-color 0.2s;
}
.global-search:focus-within { border-color: var(--color-accent); }
.search-icon { color: var(--text-secondary); font-size: 1.25rem; margin-right: 0.5rem; }

/* Right Nav Tools */
.nav-right { display: flex; align-items: center; gap: 1.5rem; }
.nav-links { display: none; gap: 1.5rem; }
@media (min-width: 768px) { .nav-links { display: flex; } }
.nav-links a {
  font-family: var(--font-sans);
  color: var(--text-secondary);
  text-decoration: none;
  font-size: 0.95rem;
  font-weight: 500;
  transition: color 0.2s;
}
.nav-links a:hover, .nav-links a.router-link-active { color: var(--text-primary); }

.divider { height: 24px; width: 1px; background: var(--border-subtle); }

.cruelty-toggle {
  background: none; border: none; font-family: var(--font-sans);
  text-transform: uppercase; letter-spacing: 0.1em; font-size: 0.7rem;
  font-weight: 700; color: var(--text-muted); cursor: pointer; transition: color 0.2s;
}
.cruelty-toggle:hover { color: var(--color-danger); }

.theme-toggle {
  background: none; border: none; color: var(--text-secondary); cursor: pointer; display: flex; align-items: center;
}
.theme-toggle:hover { color: var(--text-primary); }

.upload-btn { padding: 0.5rem 1.25rem; }
.btn-outline {
  border: 1px solid var(--border-strong); color: var(--text-primary); text-decoration: none;
  font-family: var(--font-sans); font-size: 0.9rem; font-weight: 500; transition: background 0.2s;
}
.btn-outline:hover { background: var(--bg-element); }

.content-canvas { flex: 1; padding: 2rem; width: 100%; }
@media (min-width: 1024px) { .content-canvas { padding: 3rem 4rem; } }

@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.1); }
  100% { transform: scale(1); }
}
</style>