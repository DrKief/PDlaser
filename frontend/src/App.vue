<script setup lang="ts">
import { ref, onMounted } from "vue";

const theme = ref("dark");
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
    const prev = localStorage.getItem("lastNormalTheme") || "dark";
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
    const currentStored = localStorage.getItem("lastNormalTheme") || "dark";
    const newTheme = currentStored === "dark" ? "light" : "dark";
    localStorage.setItem("lastNormalTheme", newTheme);
    return;
  }

  theme.value = theme.value === "dark" ? "light" : "dark";
  document.documentElement.className = theme.value;
  localStorage.setItem("theme", theme.value);
};

onMounted(() => {
  const savedTheme = localStorage.getItem("theme");
  if (savedTheme) {
    theme.value = savedTheme;
  } else if (window.matchMedia("(prefers-color-scheme: light)").matches) {
    theme.value = "light";
  } else {
    theme.value = "dark";
  }
  document.documentElement.className = theme.value;
  document.addEventListener("click", handleGlobalClick);
});
</script>

<template>
  <div class="app-layout">
    <header class="app-header">
      <div class="logo">
        <h1>{{ theme === "cruelty" ? "პდლ-ლ3" : "PDL_L3" }}</h1>
        <span class="system-status">SYS.ONLINE</span>
      </div>
      
      <nav class="main-nav" aria-label="Main Navigation">
        <router-link class="nav-item" to="/">
          {{ theme === "cruelty" ? "PADDED CELL" : "INDEX" }}
        </router-link>
        <router-link class="nav-item" to="/upload">
          {{ theme === "cruelty" ? "CRANIAL INTRUSION" : "INGEST" }}
        </router-link>
        <router-link class="nav-item" to="/gallery">
          {{ theme === "cruelty" ? "FADED MEMORIES" : "ARCHIVE" }}
        </router-link>
        <router-link class="nav-item" to="/search">
          {{ theme === "cruelty" ? "PAVLOVIAN RECALL" : "QUERY" }}
        </router-link>
      </nav>
    </header>

    <main class="app-main">
      <suspense>
        <router-view />
      </suspense>
    </main>

    <footer class="app-footer">
      <p class="footer-text">
        {{
          theme === "cruelty"
            ? "CONSUMER SOFTPRODUCTS // L3 // 666"
            : "CONSUMER SOFTPRODUCTS // L3 // CREATED BY L. KOUMASONAS & N. SEMENOV"
        }}
      </p>
    </footer>

    <!-- Floating System Controls -->
    <div class="system-controls">
      <button class="control-btn cruelty-toggle" @click="toggleCruelty" title="Override Protocol" aria-label="Toggle Cruelty Theme">
        <span v-if="theme === 'cruelty'">😇</span>
        <span v-else>⚠️</span>
      </button>
      <button class="control-btn theme-toggle" @click="toggleTheme" title="Cycle Optics" aria-label="Toggle Light/Dark Theme">
        <span v-if="theme === 'light'">🌙</span>
        <span v-else>☀️</span>
      </button>
    </div>
  </div>
</template>

<style scoped>
.app-layout {
  display: flex;
  flex-direction: column;
  min-height: calc(100vh - 4rem);
}

.app-header {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
  margin-bottom: var(--space-2xl);
  border-bottom: 2px solid var(--border-color);
  padding-bottom: var(--space-md);
}

@media (min-width: 768px) {
  .app-header {
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-end;
  }
}

.logo {
  display: flex;
  align-items: baseline;
  gap: var(--space-md);
}

.logo h1 {
  margin: 0;
  color: var(--color-primary);
}

.system-status {
  font-family: var(--font-mono);
  font-size: 0.75rem;
  color: var(--text-secondary);
  background: var(--bg-tertiary);
  padding: 2px 6px;
  border: 1px solid var(--border-color);
}

.main-nav {
  display: flex;
  flex-wrap: wrap;
  gap: var(--space-sm);
}

.nav-item {
  font-family: var(--font-mono);
  font-size: 0.875rem;
  font-weight: bold;
  text-transform: uppercase;
  color: var(--text-secondary);
  text-decoration: none;
  padding: var(--space-xs) var(--space-sm);
  border: 1px solid transparent;
  transition: all 0.2s var(--ease-out-expo);
}

.nav-item:hover {
  color: var(--text-primary);
  border-color: var(--border-color);
  background: var(--bg-secondary);
}

.nav-item.router-link-active {
  color: var(--color-primary);
  border-color: var(--color-primary);
  background: color-mix(in oklch, var(--color-primary) 10%, transparent);
}

.app-main {
  flex: 1;
}

.app-footer {
  margin-top: var(--space-2xl);
  padding-top: var(--space-lg);
  border-top: 1px solid var(--border-color);
}

.footer-text {
  font-family: var(--font-mono);
  font-size: 0.75rem;
  color: var(--text-muted);
  text-align: right;
  margin: 0;
}

/* Floating Controls */
.system-controls {
  position: fixed;
  bottom: var(--space-lg);
  right: var(--space-lg);
  display: flex;
  flex-direction: column;
  gap: var(--space-sm);
  z-index: 50;
}

.control-btn {
  width: 44px;
  height: 44px;
  padding: 0;
  border-radius: 0;
  background: var(--bg-primary);
}
</style>