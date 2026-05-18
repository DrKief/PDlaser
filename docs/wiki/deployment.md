# Docker, Deployment & Twelve-Factor Methodology

The application is containerized and follows the **Twelve-Factor App** methodology. Configuration is split across three environments (`docker-compose.dev.yml`, `docker-compose.preview.yml`, `docker-compose.prod.yml`).

## The Twelve-Factor Adherence

Key aspects include:

1. **I. Codebase:** One repository contains the frontend, backend, and infrastructure code.
2. **II. Dependencies:** All backend and frontend dependencies are explicitly declared in `pom.xml` and `package.json`, isolated entirely within Docker multi-stage builds. No system-level packages leak into the containers.
3. **III. Config:** Zero hardcoded credentials exist in the source. S3 keys, Postgres passwords, and JWT Secrets are injected dynamically into the `application.yaml` via OS-level Environment Variables.
4. **IV. Backing Services:** Garage S3 and PostgreSQL are treated as attached resources.
5. **VI. Processes:** The backend and frontend are stateless. Authentication is handled client-side via JSON Web Tokens (JWT).
6. **VIII. Concurrency:** The application uses Java 21 Virtual Threads for ONNX model execution.
7. **IX. Disposability:** Database transactions use `FOR UPDATE SKIP LOCKED` for task queues.
8. **X. Dev/Prod Parity:** Developers use the exact same Docker Images locally as the production servers use. The only difference is the deployment topology (e.g., using `tmpfs` mounts in local dev vs named physical volumes in production).
9. **XI. Logs:** Logs are written to `stdout` and managed by Docker.
10. **XII. Identity:** Stateless JWTs establish workload identity between the client and the backend API.

---

## Microservice Tiers

### 1. Relational Database (`db`)

- **Image:** `pgvector/pgvector:pg18`
- **Function:** Handles all relational constraints and `hnsw` distance mathematics.
- **Storage Parity:** Uses `tmpfs` mounts in local/preview environments to enforce deterministic zero-state testing of Flyway migrations, and physical `pgdata` volumes in production.

### 2. Object Storage (`garage` & `garage-init`)

- **Images:** `dxflrs/garage:v2.2.0` (Storage Node) and `alpine:3.23` (Init Job)
- **Function:** Replaces local filesystem IO with an S3-compatible API cluster. High-performance, distributed blob storage for images.
- **Provisioning:** A lightweight alpine init container automatically pings the Garage RPC daemon, configures the single-node deployment layout, and provisions the access keys / buckets upon startup.

### 3. Java Backend (`backend`)

- **Build Stage:** `maven:3.9.6-eclipse-temurin-21-alpine`
- **Runtime Image:** `eclipse-temurin:21-jre`
- **Function:** Houses the Spring Boot 4.0.5 executable, DJL Tokenizers 0.36.0, and ONNX Runtime 1.24.3 engines. Uses AWS SDK BOM v2.42.32 to interface with the Garage container.
- **Integrity Safeguard:** Incorporates a rigorous Cryptographic Subresource Integrity check on boot. Validates a hardcoded SHA-256 hash against an internal multi-megabyte PDF payload to guarantee deployment immutability.

### 4. Telemetry Observability (`prometheus` & `grafana`)

- **Images:** `prom/prometheus:v3.5.0` and `grafana/grafana:12.4.2`
- **Function:** Prometheus scrapes the Spring Boot actuator (`/actuator/prometheus`) every 10 seconds to generate deep system insight on Virtual Thread loads, S3 transaction latency, and memory utilization, visualized globally in Grafana.

### 5. Frontend & Reverse Proxy (`frontend`)

- **Build Stage:** `node:24-alpine`
- **Runtime Image:** `nginx:alpine`
- **Function:** Nginx is configured to serve the Vue.js ^3.5.32 static files directly.
- **Proxy Configuration:** Configured to internally intercept API calls (`/images`, `/auth`, `/admin`) and proxy them directly over the docker bridge network to the Java Backend, completely obfuscating the backend from public internet exposure and resolving CORS issues.
