<script setup lang="ts">
import { ref } from 'vue';
import http from '../http-api';

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
    // Hard reload forces the App.vue state to instantly re-evaluate the admin token
    window.location.href = '/'; 
  } catch (e) {
    errorMessage.value = "Invalid credentials.";
  }
};
</script>

<template>
  <div class="view-wrapper login-wrapper">
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
      </form>
    </div>
  </div>
</template>

<style scoped>
.login-wrapper { display: flex; justify-content: center; align-items: center; min-height: 60vh; }
.login-card { width: 100%; max-width: 400px; padding: 2.5rem; background: var(--bg-surface); border: 1px solid var(--border-subtle); border-radius: 8px; }
.page-title { text-align: center; margin-bottom: 2rem; font-family: var(--font-headline); font-size: 2.5rem; }
.config-form { display: flex; flex-direction: column; gap: 1.5rem; }
.error-text { color: var(--color-danger); text-align: center; margin-top: 0.5rem; font-size: 0.9rem; }
.w-full { width: 100%; }

/* Cruelty Overrides */
:root.cruelty .login-card { background: #000; border: 4px solid var(--color-accent); border-radius: 0; }
:root.cruelty .page-title { font-family: 'Impact'; color: #00FF00; text-transform: uppercase; }
</style>