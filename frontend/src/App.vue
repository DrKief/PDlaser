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
    // If currently cruelty, just switch normal theme setting
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
  <div>
    <h1>{{ theme === "cruelty" ? "პდლ-ლ3 " : "PDL - L3" }}</h1>
    <nav>
      <router-link to="/">{{ theme === "cruelty" ? "PADDED CELL" : "Home" }}</router-link> |
      <router-link to="/upload">{{
        theme === "cruelty" ? "CRANIAL INTRUSION" : "Upload"
      }}</router-link>
      |
      <router-link to="/gallery">{{
        theme === "cruelty" ? "FADED MEMORIES" : "Gallery"
      }}</router-link>
      |
      <router-link to="/search">{{
        theme === "cruelty" ? "PAVLOVIAN RECALL" : "Search"
      }}</router-link>
    </nav>
    <main>
      <suspense>
        <router-view />
      </suspense>
    </main>
    <footer>
      <p>
        {{
          theme === "cruelty"
            ? "CONSUMER SOFTPRODUCTS // L3 // 666"
            : "Created by Lucas Koumasonas and Nikita Semenov"
        }}
      </p>
    </footer>
    <div class="theme-controls">
      <button class="theme-toggle" @click="toggleTheme" title="Toggle Light/Dark">
        <span v-if="theme === 'light'">🌙</span>
        <span v-else>☀️</span>
      </button>
    </div>
    <div class="cruelty-control">
      <button class="cruelty-toggle" @click="toggleCruelty" title="Toggle DIVINE LIGHT">
        <span v-if="theme === 'cruelty'">😇</span>
        <span v-else>👹</span>
      </button>
    </div>
  </div>
</template>

<style scoped>
.theme-controls {
  position: fixed;
  bottom: 20px;
  right: 20px;
  display: flex;
  gap: 10px;
  z-index: 1001;
}

.cruelty-control {
  position: fixed;
  bottom: 20px;
  left: 20px;
  z-index: 1001;
}

.theme-toggle,
.cruelty-toggle {
  background: var(--bg-secondary);
  border: 1px solid var(--border-color);
  border-radius: 50%;
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 1.2rem;
  transition: transform 0.2s;
}

.theme-toggle:hover,
.cruelty-toggle:hover {
  transform: scale(1.1);
}

nav {
  margin-bottom: 20px;
  padding: 10px;
  border-radius: 4px;
}

nav a {
  margin: 0 10px;
  text-decoration: none;
  font-weight: bold;
}

nav a.router-link-active {
  color: var(--color-primary);
}

footer {
  text-align: center;
  margin-top: 60px;
  padding: 20px;
  font-size: 0.9rem;
  opacity: 0.7;
}

main {
  padding: 20px;
  min-height: 60vh;
}
</style>
