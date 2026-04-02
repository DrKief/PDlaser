<script setup lang="ts">
import { ref } from 'vue';
import http from '../api/http-client';

const username = ref('');
const password = ref('');
const errorMessage = ref('');

const handleLogin = async () => {
  try {
    const res = await http.post('/auth/login', {
      username: username.value,
      password: password.value
    });
    localStorage.setItem('token', res.data.token);
    
    // Retrieve and clear the intended route
    const redirectUrl = localStorage.getItem('intendedRoute') || '/';
    localStorage.removeItem('intendedRoute');
    
    window.location.href = redirectUrl;
  } catch (e) {
    errorMessage.value = "Invalid credentials.";
  }
};
</script>

<template>
  <div class="view-wrapper auth-wrapper">
    <div class="meta-card login-card">
      <h1 class="page-title">Authenticate</h1>
      <form @submit.prevent="handleLogin" class="config-form">
        <div class="form-group">
          <label class="label-text">Username</label>
          <input type="text" v-model="username" required autofocus />
        </div>
        <div class="form-group">
          <label class="label-text">Password</label>
          <input type="password" v-model="password" required />
        </div>
        <p v-if="errorMessage" class="error-text">{{ errorMessage }}</p>
        <button type="submit" class="btn w-full mt-4">Login</button>
        <div style="text-align: center; margin-top: 1rem;">
          <router-link to="/register" class="text-link">Need an account? Register here.</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
.auth-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 60vh;
}

.login-card {
  width: 100%;
  max-width: 400px;
  padding: 2.5rem;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
}

.config-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.text-link {
  color: var(--color-accent);
  font-size: 0.9rem;
  text-decoration: none;
  font-weight: 500;
  transition: opacity 0.2s;
}

.text-link:hover {
  opacity: 0.7;
}
</style>