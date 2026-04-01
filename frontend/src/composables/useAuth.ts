import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';

export function useAuth() {
  const router = useRouter();
  const token = ref(localStorage.getItem('token'));

  const isLoggedIn = computed(() => !!token.value);
  
  const username = computed(() => {
    if (!token.value) return null;
    try {
      const payload = JSON.parse(atob(token.value.split('.')[1]));
      return payload.sub; 
    } catch (e) {
      return null;
    }
  });

  const logout = () => {
    localStorage.removeItem('token');
    token.value = null;
    router.push('/');
    window.location.reload(); 
  };

  return {
    isLoggedIn,
    username,
    logout
  };
}