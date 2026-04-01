import { createWebHistory, createRouter } from "vue-router";
import type { RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  { path: "/", name: "gallery", component: () => import("./components/Gallery.vue") },
  { path: "/login", name: "login", component: () => import("./components/Login.vue") },
  { path: "/register", name: "register", component: () => import("./components/Register.vue") },
  { path: "/image/:id", name: "image-detail", component: () => import("./components/ImageDetail.vue") },
  { path: "/search", name: "search", component: () => import("./components/Search.vue") },
  { path: "/upload", name: "upload", component: () => import("./components/Upload.vue") },
  { path: "/about", name: "about", component: () => import("./components/About.vue") },
  { path: "/admin", name: "admin", component: () => import("./components/Admin.vue") },
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