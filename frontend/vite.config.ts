import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import vueDevTools from "vite-plugin-vue-devtools";

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), vueDevTools()],
  server: {
    proxy: {
      "/images": {
        target: "http://backend:8080/images",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/images/, ""),
      },
      "/auth": {
        target: "http://backend:8080/auth",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/auth/, ""),
      },
      "/admin": {
        target: "http://backend:8080/admin",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/admin/, ""),
      },
    },
  },
});