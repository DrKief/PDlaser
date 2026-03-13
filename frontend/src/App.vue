<script setup lang="ts">
import { ref, onMounted } from 'vue'

const theme = ref('dark')

const toggleTheme = () => {
  theme.value = theme.value === 'dark' ? 'light' : 'dark'
  document.documentElement.className = theme.value
  localStorage.setItem('theme', theme.value)
}

onMounted(() => {
  const savedTheme = localStorage.getItem('theme')
  if (savedTheme) {
    theme.value = savedTheme
  } else if (window.matchMedia('(prefers-color-scheme: light)').matches) {
    theme.value = 'light'
  } else {
    theme.value = 'dark'
  }
  document.documentElement.className = theme.value
})
</script>

<template>  
  <div>
    <h1>PDL - L3</h1>
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/upload">Upload</router-link> |
      <router-link to="/gallery">Gallery</router-link>
    </nav>
    <main>
      <suspense>
        <router-view />
      </suspense>
    </main>
    <footer>
      <p>Created by Lucas Koumasonas and Nikita Semenov</p>
    </footer>
    <button class="theme-toggle" @click="toggleTheme" title="Toggle theme">
      {{ theme === 'dark' ? '☀️' : '🌙' }}
    </button>
  </div>
</template>

<style scoped>
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
  color: #42b983;
}

.theme-toggle {
  position: fixed;
  bottom: 20px;
  right: 20px;
  background: rgba(128, 128, 128, 0.15);
  backdrop-filter: blur(5px);
  border: 1px solid rgba(128, 128, 128, 0.2);
  width: 50px;
  height: 50px;
  border-radius: 50%;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  padding: 0;
  z-index: 1000;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.theme-toggle:hover {
  background-color: rgba(128, 128, 128, 0.3);
  transform: scale(1.1);
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
