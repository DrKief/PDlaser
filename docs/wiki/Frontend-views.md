# Frontend Views & Routing

This document outlines the Vue Router 5 configuration and the expected behavioral data flows of the client-side pages.

### 1. Gallery View (`/` or `/gallery`)

The primary browsing interface for the archive.

- **Component:** `src/views/GalleryView.vue`
- **Functionality:** Renders a masonry grid of image cards fetched via backend pagination. Displays tags and Long-Polling extraction statuses.
- **Interaction:** Clicking an image navigates to its detail view. Authenticated users can permanently delete their own uploads directly from this grid, triggering optimistic DOM updates. Features an integrated off-canvas sidebar (`AdvancedSearchSidebar.vue`) for configuring Similarity searches.

### 2. Upload View (`/upload`)

Interface for adding new images to the server. Protected by Vue Router guards (Requires Authentication).

- **Component:** `src/views/UploadView.vue`
- **Functionality:** Tracks a queue of up to 10 incoming files. Generates temporary `URL.createObjectURL()` previews. Handles the batch POST requests and visually tracks the Long-Polling completion status of the backend's AI extraction pipeline, showing real-time feedback (Uploading, Extracting, Completed).

### 3. Image Detail View (`/image/:id`)

Deep-dive inspection for a single artifact.

- **Component:** `src/views/ImageDetailView.vue`
- **Functionality:** Displays the high-resolution image, exact metadata dimensions, and allows the rapid inline addition or removal of keywords/tags via the `/keywords` endpoint. Includes a "Find Visually Similar" trigger button to route back to the Gallery with similarity context.

### 4. Authentication Views (`/login` & `/register`)

- **Components:** `LoginView.vue`, `RegisterView.vue`
- **Functionality:** Standardized forms that POST credentials to the backend `/auth` endpoints. On successful login, the JWT is stored in `localStorage` and the user is redirected to their intended route (or Home).

### 5. My Archive (`/profile`)

Personal management dashboard.

- **Component:** `src/views/ProfileView.vue`
- **Functionality:** Automatically calls the `/images?mine=true` endpoint to filter the gallery exclusively to images owned by the currently authenticated JWT subject.

### 6. Admin Dashboard (`/admin`)

Infrastructure management interface. Restricted strictly to accounts possessing the `ROLE_ADMIN` JWT claim.

- **Component:** `src/views/AdminView.vue`
- **Functionality:** Exposes inputs to trigger the backend's background Unsplash Dataset Ingestion engine (`/admin/unsplash/import`). Polls the active status of the worker threads to monitor dataset batching.

### 7. About / System Architecture (`/about`)

- **Component:** `src/views/AboutView.vue`
- **Functionality:** A technical summary page explaining the pgvector indexing, HNSW algorithms, ResNet-50 implementations, and providing live mathematical estimations of the current Database Tensor Memory Size based on active indexing dimensions retrieved dynamically from the API.
