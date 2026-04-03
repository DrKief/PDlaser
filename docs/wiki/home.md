# PDLaser Documentation Wiki

Welcome to the official documentation for the **PDLaser Image Management System (v3a)**.

This wiki contains the comprehensive technical documentation detailing our architectural decisions, backend API, frontend Vue structure, database schema tuning, and deployment infrastructure.

## 📚 Navigation

### [API Reference](API-Reference)

Comprehensive guide to the Spring Boot 4.0.5 REST API, including core image operations, ephemeral similarity endpoints, JWT authentication, and administrative dataset ingestion.

### [Frontend Architecture & Views](Frontend-Architecture-and-Views)

Detailed overview of the Vue 3.5 SPA, exploring the component structure, Vue Router 5 configuration, HTTP Long-Polling logic, Axios interceptors, and the Cruelty Squad easter-egg theme (HUX).

### [Frontend Views Routing](Frontend-views)

In-depth breakdown of the specific route targets (`/gallery`, `/search`, `/profile`, `/admin`, etc.) and the expected client-side data flows on each page.

### [Database Schema & HNSW](Database-Schema)

Extensive details on the PostgreSQL 18 implementation, including the `pgvector` extension usage, table structures (`images`, `imagedescriptors`, `imagekeywords`, `users`), and the mathematically tuned multi-dimensional HNSW vector indexing parameters.

### [Docker & Deployment](Docker-and-Deployment)

Guide to our containerized microservices architecture, explaining the different Docker Compose environments (local `tmpfs`, preview, production), Dokploy integration, Nginx reverse proxying, and Cryptographic Subresource Integrity checks.

---

## ✍️ Wiki Usage

**Make sure to keep this wiki updated at all times.**

### Adding pages

Follow the current format. A wiki page should contain a list of features, technical details, and how to use them. Every wiki page must be written in Markdown. Wiki pages should only be added for **LARGE** architectural parts of the project.

To create a new wiki page, follow these instructions:

1. Edit this home wiki page.
2. Create a new `### [Title](Slug)` link under the Navigation section.
3. Save changes to home.
4. Click on your newly created link.
5. Create the page and add your markdown documentation.
