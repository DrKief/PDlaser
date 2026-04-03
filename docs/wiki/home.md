# PDLaser Documentation Wiki

Welcome to the official documentation for the **PDLaser Image Management System (v3a)**.

This wiki contains the technical documentation for the backend API, frontend architecture, database schema, and deployment infrastructure.

## 📚 Navigation

### [API Reference](API-Reference)

Comprehensive guide to the Spring Boot 4.0.5 REST API, including core image operations, ephemeral similarity endpoints, JWT authentication, and administrative dataset ingestion.

### [Frontend Architecture & Views](Frontend-Architecture-and-Views)

Overview of the Vue 3.5 SPA, detailing the component structure, Vue Router configuration, Long-Polling tracking, Axios interceptors, and the Cruelty Squad easter-egg theme.

### [Frontend Views Routing](Frontend-views)

Detailed breakdown of the specific route targets (`/gallery`, `/search`, `/profile`, `/admin`, etc.) and the expected client-side functionality on each page.

### [Database Schema](Database-Schema)

Details on the PostgreSQL 18 implementation, including the `pgvector` extension usage, table structures (`images`, `imagedescriptors`, `imagekeywords`, `users`), and multi-dimensional HNSW vector indexing.

### [Docker & Deployment](Docker-and-Deployment)

Guide to our containerized microservices architecture, explaining the different Docker Compose environments (local, preview, production), Dokploy integration, and Nginx reverse proxying.

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
