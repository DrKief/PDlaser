<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useAuth } from "./composables/useAuth";
import { useGallerySearch } from "./composables/useGallerySearch";
import TagAutocomplete from "./shared/TagAutocomplete.vue";
import http from "./api/http-client";

const { isLoggedIn, logout } = useAuth();
const { clearSimilaritySearch } = useGallerySearch();
const router = useRouter();
const route = useRoute();
const theme = ref("light");
const isMobileMenuOpen = ref(false);
let audioCtx: AudioContext | null = null;
const popularTags = ref<string[]>([]);

const isAdmin = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return false;
  try {
    const payload = JSON.parse(atob(token.split(".")[1] || ""));
    return payload.role === "ROLE_ADMIN";
  } catch (e) {
    return false;
  }
});

const username = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return "";
  try {
    const payload = JSON.parse(atob(token.split(".")[1] || ""));
    return payload.sub || payload.username || "User";
  } catch (e) {
    return "";
  }
});

const fetchPopularTags = async () => {
  try {
    const res = await http.get("/images/keywords/popular?limit=15");
    popularTags.value = res.data;
  } catch (e) {
    console.error(e);
  }
};

const executeSearch = (tag: string) => {
  clearSimilaritySearch();
  if (!tag) {
    router.push({ path: "/" });
  } else {
    router.push({ path: "/", query: { tags: tag } });
  }
};

const playPainSound = () => {
  if (theme.value !== "cruelty") return;
  if (!audioCtx) {
    audioCtx = new (window.AudioContext || (window as any).webkitAudioContext)();
  }
  if (audioCtx.state === "suspended") {
    audioCtx.resume();
  }
  const osc = audioCtx.createOscillator();
  const gain = audioCtx.createGain();
  osc.type = "sawtooth";
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
  localStorage.setItem("theme", theme.value);

  const linkId = "cruelty-theme-link";
  let link = document.getElementById(linkId) as HTMLLinkElement;

  if (target === "cruelty") {
    if (!link) {
      link = document.createElement("link");
      link.id = linkId;
      link.rel = "stylesheet";
      document.head.appendChild(link);
      const cssUrl = (await import("./assets/theme-cruelty.css?url")).default;
      link.href = cssUrl;
    }
  } else {
    if (link) link.remove();
  }
};

onMounted(() => {
  const savedTheme = localStorage.getItem("theme");
  if (savedTheme) {
    theme.value = savedTheme;
  } else if (window.matchMedia("(prefers-color-scheme: dark)").matches) {
    theme.value = "dark";
  } else {
    theme.value = "light";
  }
  toggleTheme(theme.value);
  document.addEventListener("click", handleGlobalClick);
  fetchPopularTags();
});
</script>

<template>
  <div class="crt-overlay"></div>
  <div class="app-layout">
    <header class="top-nav">
      <div class="nav-left">
        <router-link to="/" class="logo" @click="clearSimilaritySearch">pdl.</router-link>
      </div>

      <div class="nav-right" :class="{ 'mobile-open': isMobileMenuOpen }">
        <button class="hamburger-btn" @click="isMobileMenuOpen = !isMobileMenuOpen">
          <span class="material-symbols-outlined">{{ isMobileMenuOpen ? "close" : "menu" }}</span>
        </button>

        <div class="nav-content" :class="{ 'is-open': isMobileMenuOpen }">
          <nav class="nav-links">
            <router-link to="/about" @click="isMobileMenuOpen = false">About</router-link>
            <router-link v-if="isAdmin" to="/admin" @click="isMobileMenuOpen = false"
              >Admin</router-link
            >
          </nav>
          <div class="divider desktop-only"></div>
          <button
            class="cruelty-toggle"
            @click="
              toggleTheme(theme === 'cruelty' ? 'light' : 'cruelty');
              isMobileMenuOpen = false;
            "
          >
            {{ theme === "cruelty" ? "REVERT CRUELTY" : "CRUELTY" }}
          </button>
          <button
            class="theme-toggle"
            @click="
              toggleTheme(theme === 'light' ? 'dark' : 'light');
              isMobileMenuOpen = false;
            "
            v-if="theme !== 'cruelty'"
            title="Toggle Theme"
          >
            <span class="material-symbols-outlined">contrast</span>
          </button>

          <template v-if="isLoggedIn">
            <router-link
              to="/profile"
              class="user-badge"
              @click="isMobileMenuOpen = false"
              style="
                font-family: var(--font-mono);
                color: var(--text-muted);
                font-size: 0.85rem;
                text-decoration: none;
              "
              >@{{ username }}</router-link
            >
            <button
              @click="
                logout();
                isMobileMenuOpen = false;
              "
              class="btn logout-btn"
              style="
                background: none;
                border: none;
                cursor: pointer;
                color: var(--text-secondary);
                font-family: var(--font-sans);
                font-weight: 500;
              "
            >
              Logout
            </button>
            <router-link to="/upload" class="btn upload-btn" @click="isMobileMenuOpen = false"
              >Upload</router-link
            >
          </template>
          <template v-else>
            <router-link
              to="/login"
              class="btn login-btn"
              @click="isMobileMenuOpen = false"
              style="
                color: var(--text-secondary);
                font-family: var(--font-sans);
                font-weight: 500;
                text-decoration: none;
                background: transparent;
                border: none;
              "
              >Login</router-link
            >
            <router-link
              to="/register"
              class="btn btn-outline"
              @click="isMobileMenuOpen = false"
              style="padding: 0.4rem 1rem; border-radius: 4px"
              >Register</router-link
            >
          </template>
        </div>
      </div>
    </header>

    <div class="sub-nav">
      <div class="tags-scroll">
        <button
          @click="executeSearch('')"
          class="popular-tag-btn"
          :class="{ 'active-tag': !route.query.tags && route.path === '/' }"
        >
          Home
        </button>
        <button
          v-for="tag in popularTags"
          :key="tag"
          @click="executeSearch(tag)"
          class="popular-tag-btn"
          :class="{ 'active-tag': route.query.tags === tag }"
        >
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
  position: relative; /* Ensure absolute children position relative to this */
  z-index: 51;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.75rem max(1rem, env(safe-area-inset-right)) 0.75rem
    max(1rem, env(safe-area-inset-left));
  background: var(--bg-surface);
  border-bottom: 1px solid var(--border-subtle);
}

@media (min-width: 768px) {
  .top-nav {
    padding: 0.75rem max(2rem, env(safe-area-inset-right)) 0.75rem
      max(2rem, env(safe-area-inset-left));
  }
}

.nav-left {
  display: flex;
  align-items: center;
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

.logo:hover {
  opacity: 0.7;
}

/* Sub Navigation */
.sub-nav {
  position: sticky;
  top: 0; /* Sits at the top since top-nav scrolls away */
  z-index: 50;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: stretch;
  padding: 0.5rem max(1rem, env(safe-area-inset-right)) 0.5rem max(1rem, env(safe-area-inset-left));
  background: var(--bg-element);
  border-bottom: 1px solid var(--border-subtle);
  gap: 1rem;
}

@media (min-width: 768px) {
  .sub-nav {
    flex-direction: row;
    align-items: center;
    padding: 0.5rem max(2rem, env(safe-area-inset-right)) 0.5rem
      max(2rem, env(safe-area-inset-left));
  }
}

.tags-scroll {
  display: flex;
  gap: 1rem;
  overflow-x: auto;
  white-space: nowrap;
  scrollbar-width: none;
  align-items: center;
  padding-bottom: 0.25rem;
  padding-left: max(1rem, env(safe-area-inset-left));
  padding-right: max(1rem, env(safe-area-inset-right));
  margin-left: calc(-1 * max(1rem, env(safe-area-inset-left)));
  margin-right: calc(-1 * max(1rem, env(safe-area-inset-right)));
}

@media (min-width: 768px) {
  .tags-scroll {
    gap: 1.5rem;
    padding-bottom: 0;
    margin: 0;
    padding: 0;
    width: auto;
  }
}
.tags-scroll::-webkit-scrollbar {
  display: none;
}

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
.popular-tag-btn:hover {
  color: var(--text-primary);
}
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
  width: 100%;
  max-width: none;
  transition: border-color 0.2s;
  order: -1; /* move search above tags on mobile */
}

@media (min-width: 768px) {
  .global-search {
    max-width: 250px;
    order: 0;
  }
}
.global-search:focus-within {
  border-color: var(--color-accent);
}
.search-icon {
  color: var(--text-secondary);
  font-size: 1.25rem;
  margin-right: 0.5rem;
}

/* Right Nav Tools */
.nav-right {
  display: flex;
  align-items: center;
  gap: 1.5rem;
}
.hamburger-btn {
  display: block;
  background: none;
  border: none;
  color: var(--text-primary);
  cursor: pointer;
  padding: 0.5rem;
}
@media (min-width: 768px) {
  .hamburger-btn {
    display: none;
  }
}

.nav-content {
  display: none;
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  background: var(--bg-surface);
  flex-direction: column;
  align-items: stretch;
  padding: 1.5rem 1rem;
  gap: 1rem;
  border-bottom: 1px solid var(--border-subtle);
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.5);
  z-index: 60;
}

.nav-content.is-open {
  display: flex;
}

@media (min-width: 768px) {
  .nav-content {
    display: flex;
    position: static;
    flex-direction: row;
    align-items: center;
    padding: 0;
    box-shadow: none;
    border-bottom: none;
    background: transparent;
  }
}

.nav-links {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
@media (min-width: 768px) {
  .nav-links {
    flex-direction: row;
    gap: 1.5rem;
  }
}
.nav-links a {
  font-family: var(--font-sans);
  color: var(--text-secondary);
  text-decoration: none;
  font-size: 0.95rem;
  font-weight: 500;
  transition: color 0.2s;
  padding: 0.5rem 0; /* Larger touch targets */
}
@media (min-width: 768px) {
  .nav-links a {
    padding: 0;
  }
}
.nav-links a:hover,
.nav-links a.router-link-active {
  color: var(--text-primary);
}

.divider {
  height: 1px;
  width: 100%;
  background: var(--border-subtle);
  margin: 0.5rem 0;
}
@media (min-width: 768px) {
  .divider {
    height: 24px;
    width: 1px;
    margin: 0;
  }
  .divider.desktop-only {
    display: block;
  }
}
@media (max-width: 767px) {
  .divider.desktop-only {
    display: none;
  }
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
  padding: 0.75rem 0; /* Larger touch target */
  text-align: left;
}
@media (min-width: 768px) {
  .cruelty-toggle {
    padding: 0;
    text-align: center;
  }
}

.theme-toggle {
  background: none;
  border: none;
  color: var(--text-secondary);
  cursor: pointer;
  display: flex;
  align-items: center;
  padding: 0.75rem 0;
}
@media (min-width: 768px) {
  .theme-toggle {
    padding: 0;
  }
}

.upload-btn {
  padding: 0.75rem;
  text-align: center;
}
@media (min-width: 768px) {
  .upload-btn {
    padding: 0.5rem 1.25rem;
  }
}
.btn-outline {
  border: 1px solid var(--border-strong);
  color: var(--text-primary);
  text-decoration: none;
  font-family: var(--font-sans);
  font-size: 0.9rem;
  font-weight: 500;
  transition: background 0.2s;
  padding: 0.75rem;
  text-align: center;
  border-radius: 4px;
}
@media (min-width: 768px) {
  .btn-outline {
    padding: 0.4rem 1rem;
  }
}
.btn-outline:hover {
  background: var(--bg-element);
}

.content-canvas {
  flex: 1;
  padding: 1rem max(1rem, env(safe-area-inset-right)) env(safe-area-inset-bottom)
    max(1rem, env(safe-area-inset-left));
  width: 100%;
}
@media (min-width: 768px) {
  .content-canvas {
    padding: 2rem max(2rem, env(safe-area-inset-right)) env(safe-area-inset-bottom)
      max(2rem, env(safe-area-inset-left));
  }
}
@media (min-width: 1024px) {
  .content-canvas {
    padding: 3rem max(4rem, env(safe-area-inset-right)) env(safe-area-inset-bottom)
      max(4rem, env(safe-area-inset-left));
  }
}

@keyframes pulse {
  0% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.1);
  }
  100% {
    transform: scale(1);
  }
}
</style>
