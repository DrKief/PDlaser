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
      <button class="theme-toggle" @click="toggleTheme" title="Toggle theme">
        {{ theme === 'dark' ? '☀️' : '🌙' }}
      </button>
    </nav>
    <main>
      <suspense>
        <router-view />
      </suspense>
    </main>
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
  color: var(--color-primary);
}


.theme-toggle {
  background: none;
  border: 1px solid transparent;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  font-size: 1.2rem;
  padding: 0;
  margin-left: 20px;
  color: var(--text-primary);
  transition: background-color 0.3s ease, color 0.3s ease;
}

.theme-toggle:hover {
  background-color: var(--bg-tertiary);
}

main {
  padding: 20px;
}
</style>
