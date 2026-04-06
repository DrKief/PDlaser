# Frontend Architecture

The frontend is a strictly-typed Single Page Application (SPA) built with **Vue.js 3.5 (Composition API)**, **TypeScript 6.0**, and **Vite 8.0**.

## Global Systems & Architectural Decisions

### Axios Interceptors & State (`http-client.ts` & `useAuth.ts`)

API calls are routed through a globally configured Axios instance.

- **Proxying:** During local development, Vite proxies requests to the backend. In production, the Nginx container proxies these routes. This removes the need for CORS headers.
- **Authorization Header:** The request interceptor automatically attaches `Bearer <token>` to outbound requests if a JWT exists in `localStorage`.
- **401 Handlers:** If the server rejects a token (expired/invalid signature), the response interceptor instantly flushes `localStorage` and forces a hard redirect to the `/login` view, maintaining strict state security across the entire SPA.

### Asynchronous Status Tracking (`useImageStatus.ts`)

Due to the intense computational processing required for feature extraction (Lanczos3 downsampling, BoofCV histograms, SigLIP 2 AI logits), the frontend utilizes a custom composable (`useImageStatus`) to handle background tracking of uploaded images.

**Decision (HTTP Long-Polling):** Instead of utilizing WebSockets (which require complex state management and infrastructure like Redis), we built an optimized Long-Polling loop. The client sends a request to `/images/{id}/status`, and the Spring Boot backend (`UploadStatusTracker.java`) hangs the connection securely using `DeferredResult` until the Virtual Threads finish the descriptor extraction. This updates the UI instantly without aggressively spamming the network with polling requests.

### Global Themes & Easter Egg (Cruelty Squad HUX)

The `App.vue` component contains a global theme manager featuring standard **Light** and **Dark** modes, alongside a hidden **"Cruelty"** aesthetic mode (inspired by the game _Cruelty Squad_).

- **Toggles:** Handled via top-nav buttons.
- **Effects (Hostile User Experience):** The Cruelty theme overrides CSS variables to bright neon colors, pixelates images with CRT scanline masks, skews fonts to 'Impact', and implements custom procedural `AudioContext` sawtooth sound effects on every mouse click.
- **Persistence:** Theme state is saved in `localStorage` to persist seamlessly across reloads.
