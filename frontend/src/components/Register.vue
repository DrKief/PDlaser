<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import http from '../http-api';

const username = ref('');
const password = ref('');
const errorMessage = ref('');
const successMessage = ref('');
const router = useRouter();

const handleRegister = async () => {
  errorMessage.value = "";
  successMessage.value = "";
  try {
    await http.post('/auth/register', {
      username: username.value,
      password: password.value
    });
    successMessage.value = "Registration successful! Redirecting to login...";
    setTimeout(() => {
      router.push('/login');
    }, 1500);
  } catch (e: any) {
    errorMessage.value = e.response?.data?.error || "Registration failed. Username might be taken.";
  }
};
</script>

<template>
  <div class="view-wrapper login-wrapper">
    <div class="meta-card login-card">
      <h1 class="page-title">Create Account</h1>
      <form @submit.prevent="handleRegister" class="config-form">
        <div class="form-group">
          <label class="label-text">Username</label>
          <input type="text" v-model="username" required autofocus minlength="3" />
        </div>
        <div class="form-group">
          <label class="label-text">Password</label>
          <input type="password" v-model="password" required minlength="5" />
        </div>
        <p v-if="errorMessage" class="error-text">{{ errorMessage }}</p>
        <p v-if="successMessage" class="success-text">{{ successMessage }}</p>
        <button type="submit" class="btn w-full mt-4">Register</button>
        
        <div style="text-align: center; margin-top: 1rem;">
          <router-link to="/login" class="text-link">Already have an account? Login here.</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
.login-wrapper { display: flex; justify-content: center; align-items: center; min-height: 60vh; animation: fadeIn 0.4s ease-out; }
@keyframes fadeIn { from { opacity: 0; transform: translateY(10px); } to { opacity: 1; transform: translateY(0); } }
.login-card { width: 100%; max-width: 400px; padding: 2.5rem; background: var(--bg-surface); border: 1px solid var(--border-subtle); border-radius: 8px; }
.page-title { text-align: center; margin-bottom: 2rem; font-family: var(--font-headline); font-size: 2.5rem; }
.config-form { display: flex; flex-direction: column; gap: 1.5rem; }
.error-text { color: var(--color-danger); text-align: center; margin-top: 0.5rem; font-size: 0.9rem; }
.success-text { color: var(--color-success); text-align: center; margin-top: 0.5rem; font-size: 0.9rem; font-weight: bold; }
.text-link { color: var(--color-accent); font-size: 0.9rem; text-decoration: none; font-weight: 500; transition: opacity 0.2s; }
.text-link:hover { opacity: 0.7; }
.w-full { width: 100%; }

/* Cruelty Overrides */
:root.cruelty .login-card { background: #000; border: 4px solid var(--color-accent); border-radius: 0; }
:root.cruelty .page-title { font-family: 'Impact'; color: #00FF00; text-transform: uppercase; }
</style>