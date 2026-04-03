# Frontend Architecture

The frontend is a strictly-typed Single Page Application (SPA) built with **Vue.js 3.5 (Composition API)**, **TypeScript 6.0**, and **Vite 8.0**. 

## Global Systems

### Axios Interceptors & State (`http-client.ts` & `useAuth.ts`)
API calls are routed through a configured Axios instance. 
* **Proxying:** During local development, Vite proxies requests to the backend. In production, the Nginx container proxies these routes.
* **Authorization Header:** The interceptor automatically attaches the `Bearer <token>` to outbound requests if a JWT exists in `localStorage`.
* **401 Handlers:** If the server rejects a token (expired/invalid), the response interceptor instantly flushes `localStorage` and forces a hard redirect to the `/login` view, maintaining strict state security.

### Asynchronous Status Tracking (`useImageStatus.ts`)
Due to the heavy processing required for feature extraction (HOG, ResNet-50 AI logits), the frontend uses a custom composable (`useImageStatus`) to handle background tracking of uploaded images. This relies on an optimized **HTTP Long Polling** loop. The client sends a request to `/images/{id}/status`, and the Spring Boot backend (`UploadStatusTracker.java`) hangs the connection securely using `DeferredResult` until the descriptors are finished, updating the UI instantly without spamming the network.

### Global Themes & Easter Egg (Cruelty Squad)
The `App.vue` component contains a global theme manager featuring standard **Light** and **Dark** modes, alongside a hidden **"Cruelty"** aesthetic mode (inspired by the game *Cruelty Squad*). 
* **Toggles:** Handled via top-nav buttons.
* **Effects:** The Cruelty theme overrides CSS variables to bright neon colors, pixelates images with CRT scanline masks, skews fonts to 'Impact', and implements custom procedural `AudioContext` sawtooth sound effects on every mouse click.
* **Persistence:** Theme state is saved in `localStorage` to persist across reloads.