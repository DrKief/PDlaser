<script setup lang="ts">
import { ref, onMounted } from "vue";

const theme = ref("light"); // Defaulting to light as it best shows the Braun beige
let audioCtx: AudioContext | null = null;

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

  const types: OscillatorType[] = ["sawtooth", "square", "triangle"];
  const type = types[Math.floor(Math.random() * types.length)];
  if (type) osc.type = type;

  osc.frequency.setValueAtTime(Math.random() * 1000 + 100, audioCtx.currentTime);
  osc.frequency.exponentialRampToValueAtTime(Math.random() * 50 + 10, audioCtx.currentTime + 0.1);

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

const toggleCruelty = () => {
  if (theme.value === "cruelty") {
    const prev = localStorage.getItem("lastNormalTheme") || "light";
    theme.value = prev;
  } else {
    localStorage.setItem("lastNormalTheme", theme.value);
    theme.value = "cruelty";
  }
  document.documentElement.className = theme.value;
  localStorage.setItem("theme", theme.value);
};

const toggleTheme = () => {
  if (theme.value === "cruelty") {
    const currentStored = localStorage.getItem("lastNormalTheme") || "light";
    const newTheme = currentStored === "light" ? "dark" : "light";
    localStorage.setItem("lastNormalTheme", newTheme);
    return;
  }

  theme.value = theme.value === "light" ? "dark" : "light";
  document.documentElement.className = theme.value;
  localStorage.setItem("theme", theme.value);
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
  document.documentElement.className = theme.value;
  document.addEventListener("click", handleGlobalClick);
});
</script>

<template>
  <div class="app-layout">
    <header class="app-header">
      <div class="logo">
        <div class="logo-mark"></div>
        <h1>{{ theme === "cruelty" ? "პდლ-ლ3" : "pdl." }}</h1>
      </div>
      
      <nav class="main-nav" aria-label="Main Navigation">
        <router-link class="nav-item" to="/">
          {{ theme === "cruelty" ? "PADDED CELL" : "Overview" }}
        </router-link>
        <router-link class="nav-item" to="/upload">
          {{ theme === "cruelty" ? "CRANIAL INTRUSION" : "Upload" }}
        </router-link>
        <router-link class="nav-item" to="/gallery">
          {{ theme === "cruelty" ? "FADED MEMORIES" : "Gallery" }}
        </router-link>
        <router-link class="nav-item" to="/search">
          {{ theme === "cruelty" ? "PAVLOVIAN RECALL" : "Search" }}
        </router-link>
      </nav>
    </header>

    <main class="app-main">
      <suspense>
        <router-view />
      </suspense>
    </main>

    <!-- Floating System Controls -->
    <div class="system-controls">
      <button class="control-btn" @click="toggleCruelty" aria-label="Toggle Cruelty Theme">
        <span v-if="theme === 'cruelty'">○</span>
        <span v-else>●</span>
      </button>
      <button class="control-btn" @click="toggleTheme" aria-label="Toggle Theme">
        <span v-if="theme === 'light'">◐</span>
        <span v-else>◑</span>
      </button>
    </div>
  </div>
</template>

<style scoped>
.app-layout {
  display: flex;
  flex-direction: column;
  min-height: calc(100vh - 5rem);
}

.app-header {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
  margin-bottom: var(--space-2xl);
  padding-bottom: var(--space-md);
}

@media (min-width: 768px) {
  .app-header {
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
  }
}

.logo {
  display: flex;
  align-items: center;
  gap: var(--space-sm);
}

.logo-mark {
  width: 16px;
  height: 16px;
  background-color: var(--color-accent);
  border-radius: 50%;
}

.logo h1 {
  margin: 0;
  font-size: 1.5rem;
  letter-spacing: -0.05em;
  font-weight: 700;
}

.main-nav {
  display: flex;
  flex-wrap: wrap;
  gap: var(--space-xs);
  background: var(--bg-surface);
  padding: 4px;
  border-radius: var(--radius-pill);
  box-shadow: var(--shadow-surface);
}

.nav-item {
  font-size: 0.875rem;
  font-weight: 500;
  color: var(--text-secondary);
  text-decoration: none;
  padding: 6px 16px;
  border-radius: var(--radius-pill);
  transition: all 0.2s var(--ease-standard);
}

.nav-item:hover {
  color: var(--text-primary);
}

.nav-item.router-link-active {
  color: var(--text-on-accent);
  background: var(--color-accent);
  box-shadow: 0 2px 8px color-mix(in oklch, var(--color-accent) 40%, transparent);
}

.app-main {
  flex: 1;
}

/* Floating Controls (designed like physical dials/buttons) */
.system-controls {
  position: fixed;
  bottom: var(--space-lg);
  left: var(--space-lg);
  display: flex;
  gap: var(--space-sm);
  z-index: 50;
}

.control-btn {
  width: 40px;
  height: 40px;
  padding: 0;
  border-radius: 50%;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  color: var(--text-secondary);
  box-shadow: var(--shadow-surface);
  font-size: 1.2rem;
}

.control-btn:hover {
  color: var(--text-primary);
  border-color: var(--text-muted);
}
</style>