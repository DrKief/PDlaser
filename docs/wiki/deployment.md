# Docker & Deployment Infrastructure

The application operates as a mature, containerized poly-service ecosystem. Configuration is split across three isolated environments (`docker-compose.yml`, `docker-compose.preview.yml`, `docker-compose.prod.yml`).

## Microservice Tiers

### 1. Relational Database (`db`)

- **Image:** `pgvector/pgvector:pg18`
- **Function:** Handles all relational constraints and `hnsw` distance mathematics.
- **Storage:** Uses `tmpfs` mounts in local/preview environments to enforce deterministic zero-state testing of Flyway migrations, and physical `pgdata` volumes in production.

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
