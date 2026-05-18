# PDLaser - Image Management System (v3a)

**Live Environments:**

- **Production URL:** `http://bigpdlaser.duckdns.org/`
- **Preview URL:** `https://bigpreviewlaser.duckdns.org/gallery`

## Overview

PDLaser is an image management system built for visual archiving. It executes content-based image retrieval (CBIR) using mathematical histograms and SigLIP 2 semantic embeddings.

**Tech Stack:**

- **Backend:** Java 21, Spring Boot 4.0.5, Spring Data JDBC, Flyway, BoofCV 1.3.0, ONNX Runtime 1.24.3, Twelvemonkeys ImageIO 3.13.1
- **Frontend:** Vue.js ^3.5.32, TypeScript 6.0.2, Vite 8.0.8, Vue Router 5.0.4, Axios 1.15.0
- **Database:** PostgreSQL 18 + `pgvector` 0.1.6 (HNSW Indexing)
- **Infrastructure:** Docker, Nginx (alpine), Garage S3 (v2.2.0)

---

## [The Twelve-Factor App](https://12factor.net/) Methodology

This application follows the Twelve-Factor App methodology for portability and dev/prod parity.

- **I. Codebase:** One repository tracks all microservices, triggering isolated CI/CD builds.
- **III. Config:** Routing and S3 keys are passed via environment variables.
- **IV. Backing Services:** PostgreSQL and Garage S3 are treated as detached, swappable resources.
- **VI. Processes:** The Java Spring Boot API and Vue SPA are completely stateless. JWT tokens manage sessions.
- **VIII. Concurrency:** Workloads scale horizontally. Java 21 Virtual Threads handle ONNX tensor extraction.
- **IX. Disposability:** Database row locks (`FOR UPDATE SKIP LOCKED`) allow the extraction queue to recover if a container crashes.

---

## Development Standards & Contributing

This project enforces the following standards for contributions:

- **[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/):** Commit messages must follow the Conventional Commits specification.
- **Code Formatting:** We use Prettier to format code (Java, TypeScript, Vue, Markdown). Format your files before committing:
  ```bash
  npm run format
  ```

---

## Getting Started & Running the Application

### Prerequisites

- **Docker** & **Docker Compose**
- **Java JDK 21** & **Maven 3.9+** (For local native backend development)
- **Node.js 24+** (For local native frontend development)

### Method A: Fully Containerized (Recommended)

The project utilizes explicit docker-compose overrides to guarantee Dev/Prod parity.

**1. Development Environment (Hot-reloading simulated, tmpfs RAM storage):**

```bash
docker compose -f docker-compose.dev.yml up --build
```

**2. Preview Environment (Logging enabled, isolated network tests):**

```bash
docker compose -f docker-compose.preview.yml up --build
```

**3. Production Environment (Persistent SSD Volumes, auto-restart policies):**

```bash
docker compose -f docker-compose.prod.yml up -d --build
```

- **Frontend UI:** `http://localhost:3000`
- **Backend API:** `http://localhost:8080`
- **Grafana Telemetry:** `http://localhost:3001` (Requires Prometheus integration)

> **Note:** The default Administrator account is automatically seeded into the database on the first boot (Username: `admin` | Password: `admin`).

---

### Method B: Local Native Execution (Without full Docker)

If you need to run the code natively on your host OS (Arch/Ubuntu) for debugging or IDE breakpoints, you must still spin up the backing services.

**Step 1: Start the Backing Services (DB & Garage S3)**

```bash
docker compose -f docker-compose.dev.yml up db garage garage-init -d
```

**Step 2: Run the Java Backend natively**
The `application.yaml` is pre-configured with default fallbacks to `localhost` for native execution.

```bash
cd backend
./mvnw spring-boot:run
```

**Step 3: Run the Vue Frontend natively**
_(Note: If running natively, temporarily edit `frontend/vite.config.ts` and change `target: "http://backend:8080"` to `target: "http://localhost:8080"` so Vite proxies to your local host instead of the docker bridge)._

```bash
cd frontend
npm install
npm run dev
```

---

## System Compatibility & Validation (Requirement 27)

The client, server, and object storage infrastructure have been tested across the following architectures:

**Server / Infrastructure Environments (x86_64 & ARM64):**

- **Ubuntu 24.04.4 LTS (aarch64):** Oracle Cloud (Neoverse-N1, Linux 6.17.0-1009-oracle). Validates ARM64 Docker/ONNX/pgvector stability.
- **Arch Linux (x86_64):** AMD Ryzen 5 7640U, Linux 6.19.11-zen1-1-zen.

**Client Environments:**

- **OS:** Arch Linux (KDE Plasma 6.6.4 / Wayland)
- **Browsers Tested:** Mozilla Firefox, Zen Browser
