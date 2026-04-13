# PDLaser Documentation Wiki

This is the documentation for the **PDLaser Image Management System (v3a)**.

This wiki contains technical documentation for the backend API, frontend SPA, Garage S3 storage, database schema, and deployment infrastructure.

## Navigation

### [API Reference](api-reference)

Comprehensive guide to the Spring Boot 4.0.5 REST API, including core S3 image streams, ephemeral similarity endpoints, stateless JWT authentication, and the background Unsplash dataset ingestion engine.

### [Frontend Architecture & Views](frontend-architecture)

Detailed overview of the Vue.js ^3.5.32 SPA, exploring the component structure, Vue Router 5.0.4 routing, HTTP Long-Polling logic for async tasks, Axios 1.15.0 interceptors, and the Cruelty Squad easter-egg aesthetic (HUX).

### [Frontend Views Routing](frontend-views)

In-depth breakdown of specific route targets (`/gallery`, `/search`, `/profile`, `/admin`, etc.) and the expected client-side data flows executing against the backend.

### [Database Schema & HNSW](database-schema)

Extensive details on the PostgreSQL 18 implementation utilizing the `pgvector` 0.1.6 extension. Explains the transition to Garage (S3) for blob storage, relational table structures (`images`, `imagedescriptors`), and mathematically tuned multi-dimensional HNSW vector indexing algorithms.

### [Docker, Deployment & Twelve-Factor](deployment)

Guide to our containerized microservices orchestration and **Twelve-Factor App** compliance. Explains the multi-tier Docker Compose environments, Garage S3 blob storage initialization, Prometheus 3.5.0 / Grafana 12.4.2 telemetry observability, and Cryptographic Subresource Integrity limits.

---

## Wiki Usage

**Keep this wiki updated at all times.**

### Code Formatting

The project uses a unified Prettier configuration defined in the root `package.json` to enforce styling rules across the Java backend, Vue frontend, and documentation markdown. **Before committing code, always ensure your files are formatted:**

```bash
npm run format
```
