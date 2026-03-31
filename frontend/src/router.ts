import { createWebHistory, createRouter } from "vue-router";
import type { RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "gallery",
    component: () => import("./components/Gallery.vue"),
  },
  {
    path: "/image/:id",
    name: "image-detail",
    component: () => import("./components/ImageDetail.vue"),
  },
  {
    path: "/search",
    name: "search",
    component: () => import("./components/Search.vue"),
  },
  {
    path: "/upload",
    name: "upload",
    component: () => import("./components/Upload.vue"),
  },
  {
    path: "/about",
    name: "about",
    component: () => import("./components/About.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;