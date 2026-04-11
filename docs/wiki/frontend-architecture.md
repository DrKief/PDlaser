# Frontend Architecture

The frontend is a strictly-typed Single Page Application (SPA) built with **Vue.js 3.5 (Composition API)**, **TypeScript 6.0**, and **Vite 8.0.8**.

## Global Systems & Architectural Decisions

### Axios Interceptors & Authorization (`http-client.ts` & `useAuth.ts`)

API calls are routed through a globally configured Axios 1.15.0 instance.

- **Proxying:** In production, an Nginx container serves the static Vue artifacts and dynamically reverse-proxies `/images` and `/api` to the backend. This natively eliminates CORS preflight restrictions.
- **Interceptors:** A request interceptor attaches `Bearer <token>` automatically. If the server yields a `401 Unauthorized`, a response interceptor instantly flushes `localStorage` state and forces a hard navigation to `/login`.

### Real-Time Asynchronous Tracking (`useImageStatus.ts`)

Feature extraction (Lanczos3 resampling, CIELAB computations, and 768D SigLIP 2 ONNX logits) takes significant compute time.

**Decision (HTTP Long-Polling):** Instead of over-engineering the application with WebSockets and Redis, we built an optimized Long-Polling loop. `useImageStatus` fires requests that the backend purposefully "hangs" via `DeferredResult` until the Virtual Threads complete their tasks. This limits network spam while offering sub-second state reaction times to update the UI badges (Uploading $\rightarrow$ Extracting $\rightarrow$ Completed).

### Global Themes & HUX (Cruelty Squad Easter Egg)

The UI incorporates standard Light and Dark color schemes computed via `oklch()`, and an aggressively styled **"Cruelty"** mode.

- **Effects (Hostile User Experience):** Overrides CSS properties with Impact fonts, neon high-contrast filtering, CRT animated scanlines, and taps into the Web Audio API to procedurally emit a sawtooth waveform tone on every DOM click interaction.
