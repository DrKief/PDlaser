import { createWebHistory, createRouter } from "vue-router";
import type { RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  { path: "/", name: "gallery", component: () => import("../views/GalleryView.vue") },
  { path: "/login", name: "login", component: () => import("../views/LoginView.vue") },
  { path: "/register", name: "register", component: () => import("../views/RegisterView.vue") },
  {
    path: "/image/:id",
    name: "image-detail",
    component: () => import("../views/ImageDetailView.vue"),
  },
  { path: "/upload", name: "upload", component: () => import("../views/UploadView.vue") },
  { path: "/about", name: "about", component: () => import("../views/AboutView.vue") },
  { path: "/admin", name: "admin", component: () => import("../views/AdminView.vue") },
  { path: "/profile", name: "profile", component: () => import("../views/ProfileView.vue") },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

function getUserRole() {
  const token = localStorage.getItem("token");
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const payloadStr = atob(payloadBase64.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(payloadStr).role;
  } catch (e) {
    return null;
  }
}

router.beforeEach((to, _from, next) => {
  const isAuthenticated = !!localStorage.getItem("token");
  const role = getUserRole();

  if (to.name === "admin" && role !== "ROLE_ADMIN") {
    next({ name: "gallery" });
  } else if (to.name === "upload" && !isAuthenticated) {
    // Save the intended destination before redirecting
    localStorage.setItem("intendedRoute", to.fullPath);
    next({ name: "login" });
  } else {
    next();
  }
});

export default router;
