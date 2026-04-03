# Docker & Deployment Infrastructure

The application utilizes a sophisticated containerized microservices architecture. Configuration is split across three Docker Compose files depending on the target environment to strictly govern database states and port exposures.

## Architectural Environments

1. **`docker-compose.yml` (Local Development)**
   - Uses `pgvector/pgvector:pg18`.
   - **Decision:** Uses `tmpfs` (temporary file storage in RAM) for the database. This enforces a clean slate and fast teardown for local testing environments, guaranteeing Flyway migrations execute flawlessly from zero.
   - Exposes Backend (`8080`) and Frontend (`3000`) locally to the host machine.
2. **`docker-compose.preview.yml` (Staging / PRs)**
   - Similar to local development, utilizes `tmpfs` for ephemeral database testing to prevent pull requests from corrupting standing staging data.
   - Relies on CI/CD to inject passwords via environment variables.
3. **`docker-compose.prod.yml` (Production)**
   - Configures persistent Docker Volumes (`pgdata`, `backend_images`).
   - Designed to be managed by Dokploy without exposing internal Java/Postgres ports to the outside world directly.

---

## Service Breakdown

### 1. Database (`db`)

- **Image:** `pgvector/pgvector:pg18`
- **Role:** Stores all persistent metadata, keywords, and executes lightning-fast vector distance calculations natively using the HNSW graph indices.
- **Volume:** `pgdata:/var/lib/postgresql/data` (in production).

### 2. Backend (`backend`)

- **Build Context:** `./backend`
- **Dockerfile:** Multi-stage optimized build.
  1. Uses `maven:3.9-eclipse-temurin-21-alpine` to download dependencies and compile the `.jar` utilizing layer caching.
  2. Runs securely on a minimized `eclipse-temurin:21-jre` image as a non-root user (`appuser`). (Note: Standard Ubuntu base is used instead of Alpine to provide the necessary dynamic C libraries required for native ONNX AI model execution).
  3. Pre-creates the `/var/lib/pdl/images` directory and guarantees appropriate permissions for `appuser`.
- **Zero-Trust Subresource Integrity (SRI):** To guarantee immutable deployments and defend against localized supply chain tampering, the backend `.jar` is compiled with a dense cryptographic payload (_The Whole War and Peace Novel.pdf_). Upon initialization, the server executes a strict, blocking SHA-256 checksum of this internal asset to verify deterministic state (`HealthCheck.java`). Any compiler optimizations or malicious tampering that alter this payload's byte-stream will instantly trigger a fatal container exit, forcing a secure `CrashLoopBackOff`.

### 3. Frontend (`frontend`)

- **Build Context:** `./frontend`
- **Dockerfile:** Multi-stage build.
  1. Uses `node` (Alpine) to compile the Vite 8.0 build.
  2. The static `dist` folder is served via a lightweight, secure `nginx:alpine` container.
- **Nginx Configuration (`nginx.conf`):**
  - **SPA Routing:** `try_files $uri $uri/ /index.html;` ensures Vue Router 5 handles HTML5 history mode correctly.
  - **Upload Limits:** Sets `client_max_body_size 100M;` to permit large batch image uploads.
  - **Decision (Reverse Proxy):** Directs all traffic for `/images`, `/auth`, and `/admin` to `http://backend:8080`. This architecture completely bypasses CORS requirement complexity and keeps the backend entirely insulated behind the Nginx proxy in production environments.
