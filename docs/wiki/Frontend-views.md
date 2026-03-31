# Frontend Views Documentation

This document outlines the available views (pages) in the frontend application, their corresponding routes, and their expected behavior.

## Overview

The application is built with **Vue.js 3** and uses **Vue Router** for navigation. It interacts with the backend API to manage images.

---

## Views

### 1. Home View

The landing page of the application, focused on inspecting individual images.

- **Route:** `/`
- **Component:** `src/components/Home.vue`

#### Functionality
- **Initialization:** On load, it fetches the list of available images from the backend (`GET /images`).
- **Image Selection:** Displays a dropdown menu containing the names of all available images.
- **Image Display:**
  - When a user selects an image from the dropdown, the component constructs the image URL (`/images/{id}`).
  - The selected image is displayed below the dropdown.

#### Interaction Flow
1. User navigates to Home.
2. User clicks the dropdown and chooses a file.
3. The browser requests the image binary from the server.
4. The image renders on the screen.

---

### 2. Upload View

A dedicated page for adding new images to the system.

- **Route:** `/upload`
- **Component:** `src/components/Upload.vue`

#### Functionality
- **File Selection:** Provides a standard file input to allow users to choose an image from their local device.
- **Upload Action:**
  - Validates that a file has been selected.
  - Sends a `POST` request to `/images` with the file as `multipart/form-data`.
- **Async Processing Feedback:**
  - Because feature extraction happens in the background, the server responds with a `202 Accepted`.
  - The frontend then polls the `/images/{id}/status` endpoint (via `useImageStatus`).
  - Displays processing badges until the status reaches `COMPLETED` or `FAILED`.

---

### 3. Gallery View

A browsing interface to view and manage the entire collection of images.

- **Route:** `/gallery`
- **Component:** `src/components/Gallery.vue`

#### Functionality
- **Grid Display:** Fetches all images (`GET /images`) and renders them as a grid of cards.
- **Image Cards:** Each card contains:
  - A thumbnail preview of the image.
  - The image name.
  - Associated tags (with interactive buttons to instantly delete tags).
  - A **Delete** button for the image itself.
- **Deletion:**
  - Clicking "Delete" triggers a confirmation dialog.
  - If confirmed, sends a `DELETE` request to `/images/{id}`.
  - Upon success, the image is immediately removed from the grid without requiring a page reload.

---

### 4. Search View

An advanced querying interface to find specific images based on text attributes or content similarity.

- **Route:** `/search`
- **Component:** `src/components/Search.vue`

#### Functionality
- **By Attributes:** Form to search by Name, Format, Size, or Keywords.
- **By Similarity:**
  - Allows selecting a source image.
  - User chooses a specific feature descriptor (Gradient/HOG, Saturation/HSV, RGB Distribution, CIELAB Distribution).
  - Specifies the number of results to retrieve.
  - Relies on the database's `hnsw` indexes for rapid visual matching.