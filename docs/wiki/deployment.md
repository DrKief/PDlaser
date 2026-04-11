# Docker, Deployment & Twelve-Factor Methodology

The application operates as a mature, containerized poly-service ecosystem strictly adhering to the **Twelve-Factor App** methodology. Configuration is elegantly split across three isolated environments (`docker-compose.dev.yml`, `docker-compose.preview.yml`, `docker-compose.prod.yml`).

## The Twelve-Factor Adherence

We have engineered the system to maximize disposability, parity, and horizontal scalability:

1. **I. Codebase:** One repository tracks the entirety of the architecture (Frontend, Backend, and Infrastructure as Code).
2. **II. Dependencies:** All backend and frontend dependencies are explicitly declared in `pom.xml` and `package.json`, isolated entirely within Docker multi-stage builds. No system-level packages leak into the containers.
3. **III. Config:** Zero hardcoded credentials exist in the source. S3 keys, Postgres passwords, and JWT Secrets are injected dynamically into the `application.yaml` via OS-level Environment Variables.
4. **IV. Backing Services:** PostgreSQL and Garage S3 are treated as detached resources. If Garage goes down, it can be swapped with AWS S3 simply by changing the `S3_ENDPOINT` environment variable without altering a single line of Java code.
5. **VI. Processes:** The Java API and the Vue SPA execute as entirely stateless processes. Authentication states are handled client-side via JSON Web Tokens (JWT).
6. **VIII. Concurrency:** Workloads scale effortlessly via the process model. The application leverages Java 21 Virtual Threads, ensuring high-latency ONNX vision extraction does not block the Tomcat HTTP event loop.
7. **IX. Disposability:** The extraction worker queues use atomic `FOR UPDATE SKIP LOCKED` database transactions. If the backend container is killed mid-extraction, the job safely rolls back to `PENDING` state with zero data corruption.
8. **X. Dev/Prod Parity:** Developers use the exact same Docker Images locally as the production servers use. The only difference is the deployment topology (e.g., using `tmpfs` mounts in local dev vs named physical volumes in production).
9. **XI. Logs:** The Spring Boot backend streams unbuffered logs straight to `stdout`, completely ignoring local logfile management, deferring to the Docker daemon (`json-file` driver) to manage log rotation.
10. **XII. Identity:** Secure, short-lived stateless JWTs establish workload identity between the client, backend, and external backing services.

---

## Microservice Tiers

### 1. Relational Database (`db`)

- **Image:** `pgvector/pgvector:pg18`
- **Function:** Handles all relational constraints and `hnsw` distance mathematics.
- **Storage Parity:** Uses `tmpfs` mounts in local/preview environments to enforce deterministic zero-state testing of Flyway migrations, and physical `pgdata` volumes in production.

### 2. Object Storage (`garage` & `garage-init`)

- **Image:** `dxflrs/garage:v2.2.0`
- **Function:** Replaces local filesystem IO with an S3-compatible API cluster. High-performance, distributed blob storage for images.
- **Provisioning:** A lightweight `alpine:3.23` init container automatically pings the Garage RPC daemon, configures the single-node deployment layout, and provisions the access keys / buckets upon startup.

### 3. Java Backend (`backend`)

- **Image:** Multi-stage `maven:3.9.6-eclipse-temurin-21-alpine` build $\rightarrow$ `eclipse-temurin:21-jre` runtime.
- **Function:** Houses the Spring Boot 4.0.5 executable, DJL 0.36.0, and ONNX Runtime 1.24.3 engines. Uses AWS SDK v2.42.32 to interface seamlessly with the Garage container.
- **Integrity Safeguard:** Incorporates a rigorous Cryptographic Subresource Integrity check on boot. Validates a hardcoded SHA-256 hash against an internal multi-megabyte PDF payload to guarantee deployment immutability.

### 4. Telemetry Observability (`prometheus` & `grafana`)

- **Images:** `prom/prometheus:v3.5.0` and `grafana/grafana:12.4.2`
- **Function:** Prometheus scrapes the Spring Boot actuator (`/actuator/prometheus`) every 10 seconds to generate deep system insight on Virtual Thread loads, S3 transaction latency, and memory utilization, visualized globally in Grafana.

### 5. Frontend & Reverse Proxy (`frontend`)

- **Image:** Multi-stage `node:24-alpine` build (Vite 8.0.8) $\rightarrow$ `nginx:alpine` runtime.
- **Function:** Nginx is configured to serve the Vue 3.5 static files directly.
- **Proxy Configuration:** Configured to internally intercept API calls (`/images`, `/auth`, `/admin`) and proxy them directly over the docker bridge network to the Java Backend, completely obfuscating the backend from public internet exposure and resolving CORS issues.
