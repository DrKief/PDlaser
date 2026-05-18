# Frontend Architecture

The frontend is a Single Page Application (SPA) built with **Vue.js 3.5 (Composition API)**, **TypeScript 6.0**, and **Vite 8.0.8**.

## Global Systems & Architectural Decisions

### Axios Interceptors & Authorization (`http-client.ts` & `useAuth.ts`)

API calls are routed through a globally configured Axios 1.15.0 instance.

- **Proxying:** In production, an Nginx container serves the static Vue artifacts and reverse-proxies `/images` and `/api` to the backend to prevent CORS issues.
- **Interceptors:** A request interceptor attaches `Bearer <token>` automatically. If the server yields a `401 Unauthorized`, a response interceptor clears `localStorage` and redirects to `/login`.

### Real-Time Asynchronous Tracking (`useImageStatus.ts`)

Feature extraction takes time.

**Decision (HTTP Long-Polling):** The frontend uses long-polling to check image status. `useImageStatus` sends requests that the backend holds via `DeferredResult` until the threads complete their tasks, updating the UI badges upon status changes.

### Global Themes & HUX (Cruelty Squad Easter Egg)

The UI incorporates standard Light and Dark color schemes computed via `oklch()`, and a **"Cruelty"** mode.

- **Effects (Hostile User Experience):** Overrides CSS properties with Impact fonts, neon high-contrast filtering, CRT animated scanlines, and taps into the Web Audio API to procedurally emit a sawtooth waveform tone on every DOM click interaction.
