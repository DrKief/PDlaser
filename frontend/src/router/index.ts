import { createWebHistory, createRouter } from "vue-router";
import type { RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  { path: "/", name: "gallery", component: () => import("../views/GalleryView.vue") },
  { path: "/login", name: "login", component: () => import("../views/LoginView.vue") },
  { path: "/register", name: "register", component: () => import("../views/RegisterView.vue") },
  { path: "/image/:id", name: "image-detail", component: () => import("../views/ImageDetailView.vue") },
  { path: "/search", name: "search", component: () => import("../views/SearchView.vue") },
  { path: "/upload", name: "upload", component: () => import("../views/UploadView.vue") },
  { path: "/about", name: "about", component: () => import("../views/AboutView.vue") },
  { path: "/admin", name: "admin", component: () => import("../views/AdminView.vue") },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, _from, next) => {
  const isAuthenticated = !!localStorage.getItem('token');
  if (to.name === 'upload' && !isAuthenticated) {
    next({ name: 'login' });
  } else {
    next();
  }
});

export default router;