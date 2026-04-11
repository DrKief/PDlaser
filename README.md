# PDLaser - Image Management System (v3a)

**Live Environments:**

- **Production URL:** `http://bigpdlaser.duckdns.org/`
- **Preview URL:** `https://bigpreviewlaser.duckdns.org/gallery`

## Overview

PDLaser is a full-stack, AI-augmented image management system built for high-performance visual archiving. Moving beyond basic metadata storage, it executes deep content-based image retrieval (CBIR) using advanced mathematical histograms and localized SigLIP 2 semantic embeddings.

**Next-Gen Tech Stack:**

- **Backend:** Java 21, Spring Boot 4.0.5, Spring Data JDBC, Flyway, BoofCV 1.3.0, ONNX Runtime 1.24.3, Twelvemonkeys ImageIO 3.13.1
- **Frontend:** Vue.js 3.5.32, TypeScript 6.0.2, Vite 8.0.8, Vue Router 5.0.4, Axios 1.15.0
- **Database:** PostgreSQL 18 + `pgvector` 0.1.6 (HNSW Indexing)
- **Infrastructure:** Docker, Docker Compose, Nginx (alpine), Garage S3 (v2.2.0)

---

## [The Twelve-Factor App](https://12factor.net/) Methodology

This application strictly adheres to the modern Twelve-Factor App methodology, ensuring maximum portability, disposability, and dev/prod parity.

- **I. Codebase:** One repository tracks all microservices, triggering isolated CI/CD builds.
- **III. Config:** Zero hardcoded credentials. All routing and S3 keys are passed dynamically via environment variables (`application.yaml` / Docker).
- **IV. Backing Services:** PostgreSQL and Garage S3 are treated as detached, swappable resources.
- **VI. Processes:** The Java Spring Boot API and Vue SPA are completely stateless. JWT tokens manage sessions.
- **VIII. Concurrency:** Workloads are scaled horizontally via the process model, heavily utilizing Java 21 Virtual Threads for concurrent ONNX tensor extraction.
- **IX. Disposability:** Atomic DB row locks (`FOR UPDATE SKIP LOCKED`) and `DeferredResult` ensure the ML extraction queue recovers gracefully from sudden container death.

---

## Development Standards & Contributing

To maintain code quality and a clean Git history, this project enforces strict standards for all contributions:

- **[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/):** All commit messages must follow the Conventional Commits specification (e.g., `feat: add SigLIP extraction`, `fix: correct HNSW indexing parameters`). This allows for automated semantic versioning and changelog generation.
- **Code Formatting:** We use Prettier to enforce a universal, standardized code style across the entire stack (Java, TypeScript, Vue, Markdown). Before submitting any commits, you must format your files by running the following command from the root directory:
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

In accordance with the project specifications, the client, server, and distributed object storage infrastructure have been rigorously compiled, tested, and validated across multiple architectures:

**Server / Infrastructure Environments (x86_64 & ARM64):**

- **Ubuntu 24.04.4 LTS (aarch64):** Oracle Cloud (Neoverse-N1, Linux 6.17.0-1009-oracle). Proves ARM64 Docker compilation and ONNX/pgvector cross-architecture stability.
- **Arch Linux (x86_64):** AMD Ryzen 5 7640U, Linux 6.19.11-zen1-1-zen.

**Client Environments:**

- **OS:** Arch Linux (KDE Plasma 6.6.4 / Wayland)
- **Browsers Tested:** Mozilla Firefox, Zen Browser
