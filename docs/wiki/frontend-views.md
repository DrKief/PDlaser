# Frontend Views & Routing

This document outlines the Vue Router 5 configuration mapping the SPA interface.

### 1. Gallery View (`/` or `/gallery`)

The primary browsing interface for the dataset.

- **Component:** `src/views/GalleryView.vue`
- **Functionality:** Renders a CSS masonry grid with backend pagination. Displays tags and async polling badges.
- **Interaction:** Features an integrated off-canvas component (`AdvancedSearchSidebar.vue`) for triggering algorithmic similarity searches via uploaded files or existing database context.

### 2. Upload View (`/upload`)

- **Component:** `src/views/UploadView.vue`
- **Functionality:** Upload queue for up to 10 `File` objects. Generates local `URL.createObjectURL()` previews, executes `multipart/form-data` batch uploads, and tracks S3 transmission and ONNX extraction progress.

### 3. Image Detail View (`/image/:id`)

- **Component:** `src/views/ImageDetailView.vue`
- **Functionality:** Connects to `/images/{id}/metadata` and streams the full-scale original artifact from the S3 backend via `/images/{id}`. Includes triggers to initiate browser downloads and real-time inline keyword mutation.

### 4. Authentication Views (`/login` & `/register`)

- **Components:** `LoginView.vue`, `RegisterView.vue`
- **Functionality:** Secure POST handlers updating JWT state in `localStorage` and routing the user to their previously intended destination path.

### 5. My Archive (`/profile`)

- **Component:** `src/views/ProfileView.vue`
- **Functionality:** Dynamically queries the API passing `?mine=true`, rendering an isolated gallery strictly matching the `user_id` mapped inside the user's JWT. Offers direct destructive DELETE capabilities.

### 6. Admin Dataset Orchestrator (`/admin`)

- **Component:** `src/views/AdminView.vue`
- **Functionality:** Available to `ROLE_ADMIN`. Allows uploading of raw `.tsv` files to map remote URL attributes into the local database, and provides tools to batch download those assets into the S3 backend. Also controls user account approvals.

### 7. Architecture Overview (`/about`)

- **Component:** `src/views/AboutView.vue`
- **Functionality:** Dashboard displaying matrix sizing. Computes the estimated RAM cost of the tensor arrays based on the total number of images $\times$ 3,831 vectors $\times$ 4 bytes (float32).
