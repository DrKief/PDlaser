# Frontend Views & Routing

This document outlines the Vue Router configuration and the expected behavioral flow of the client-side pages.

### 1. Gallery View (`/` or `/gallery`)

The main browsing interface.

- **Component:** `src/views/GalleryView.vue`
- **Functionality:** Renders a masonry grid of image cards fetched via pagination. Displays tags and extraction statuses.
- **Interaction:** Clicking an image navigates to its detail view. Authenticated users can permanently delete their own uploads directly from this grid. Features an integrated off-canvas sidebar (`AdvancedSearchSidebar.vue`) for triggering Similarity searches.

### 2. Upload View (`/upload`)

Interface for adding new images to the server. Protected by Vue Router guards (Requires Authentication).

- **Component:** `src/views/UploadView.vue`
- **Functionality:** Tracks a queue of up to 10 incoming files. Generates temporary `URL.createObjectURL()` previews. Handles the batch POST requests and visually tracks the Long-Polling completion status of the backend's AI extraction pipeline.

### 3. Image Detail View (`/image/:id`)

Deep-dive inspection for a single artifact.

- **Component:** `src/views/ImageDetailView.vue`
- **Functionality:** Displays the high-resolution image, exact metadata dimensions, and allows the rapid inline addition or removal of keywords/tags. Includes a "Find Visually Similar" trigger button.

### 4. Authentication Views (`/login` & `/register`)

- **Components:** `LoginView.vue`, `RegisterView.vue`
- **Functionality:** Standardized forms that POST to the backend `/auth` endpoints. On successful login, the JWT is stored in `localStorage` and the user is redirected to their intended route (or Home).

### 5. My Archive (`/profile`)

Personal management dashboard.

- **Component:** `src/views/ProfileView.vue`
- **Functionality:** Automatically calls the `/images?mine=true` endpoint to filter the gallery exclusively to images owned by the currently authenticated JWT subject.

### 6. Admin Dashboard (`/admin`)

Infrastructure management interface. Restricted to accounts possessing the `ROLE_ADMIN` JWT claim.

- **Component:** `src/views/AdminView.vue`
- **Functionality:** Exposes inputs to trigger the backend's background Unsplash Dataset Ingestion engine (`/admin/unsplash/import`). Polls the active status of the worker threads.

### 7. About / System Architecture (`/about`)

- **Component:** `src/views/AboutView.vue`
- **Functionality:** A technical summary page explaining the pgvector indexing, HNSW algorithms, ResNet-50 implementations, and providing live mathematical estimations of the current Database Tensor Memory Size based on active indexing dimensions.
