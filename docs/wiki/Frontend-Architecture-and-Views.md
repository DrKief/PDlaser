# Frontend Architecture & Views

The frontend is a Single Page Application (SPA) built with **Vue.js 3 (Composition API)**, **TypeScript**, and **Vite**. It uses Vue Router for client-side navigation.

## Views & Routing

### 1. Home View (`/`)
* **Component:** `src/components/Home.vue`
* **Purpose:** The landing page to inspect individual images.
* **Behavior:** On load, it fetches all images and populates a custom dropdown. When an image is selected, it constructs the URL (`/images/{id}`) and renders the image dynamically on the screen.

### 2. Upload View (`/upload`)
* **Component:** `src/components/Upload.vue`
* **Purpose:** Interface for adding new images to the server.
* **Behavior:** Provides a file input with a real-time image preview generated via `URL.createObjectURL()`. Validates selection, posts data as `multipart/form-data`. Because the backend processes vectors asynchronously, the frontend tracks upload progress and uses the `useImageStatus` composable to poll the `/images/{id}/status` endpoint, indicating to the user when feature extraction is fully completed.

### 3. Gallery View (`/gallery`)
* **Component:** `src/components/Gallery.vue`
* **Purpose:** Browsing interface to view and manage the entire image collection.
* **Behavior:** Renders a responsive grid of image cards. Includes a "Delete" button per card with a native browser confirmation dialog. Deletions update the UI optimistically (removing the image from the DOM array) without requiring a full page refresh.

### 4. Search View (`/search`)
* **Component:** `src/components/Search.vue`
* **Purpose:** Advanced querying interface split into two tabs:
  * **By Attributes:** Form to search by Name, Format, Size, or Keywords. Calls `GET /images/search`.
  * **By Similarity:** Allows the user to select a source image from the database, pick an algorithm (`gradient`, `saturation`, `rgb`), and define a result limit to find visually similar images. It relies on pre-computed vectors from the backend.

---

## Global Features

### Asynchronous Status Tracking (`useImageStatus.ts`)
Due to the heavy processing required for feature extraction, the frontend uses a custom composable (`useImageStatus`) to handle the background tracking of uploaded images. This relies on an optimized **HTTP Long Polling** loop. It allows the interface to remain responsive, hanging the API requests against the server which suspends HTTP threads using Spring's `DeferredResult` until BoofCV descriptors are finished.

### API Proxying & Interceptors
API calls are routed through a configured Axios instance (`src/http-api.ts`). 
* **Local Development:** To prevent CORS errors, Vite (`vite.config.ts`) proxies all requests starting with `/images` directly to the backend.
* **Production:** The Nginx container handles this routing via a `proxy_pass` directive.

### Global Themes & Easter Egg (Cruelty Squad)
The `App.vue` component contains a global theme manager featuring standard **Light** and **Dark** modes, alongside a hidden **"Cruelty"** aesthetic mode (inspired by the game *Cruelty Squad*). 
* **Toggles:** Handled via bottom-fixed floating buttons.
* **Effects:** The Cruelty theme overrides CSS variables to bright neon colors, pixelates images, skews fonts, applies background animations, and implements custom `AudioContext` sound effects on every mouse click.
* **Persistence:** Theme state is saved in `localStorage` to persist across reloads.