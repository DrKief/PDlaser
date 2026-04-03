# PDLaser - Image Management System (v3a)

**Live Environments:**

- 🟢 **Production URL:** `http://bigpdlaser.duckdns.org/`
- 🟡 **Preview URL:** `https://bigpreviewlaser.duckdns.org/gallery`

## Overview

PDLaser is a full-stack, AI-augmented image management system built for high-performance visual archiving. Moving beyond basic metadata storage, it executes deep content-based image retrieval (CBIR) using advanced mathematical histograms and localized ResNet-50 semantic embeddings.

**Next-Gen Tech Stack:**

- **Backend:** Java 21, Spring Boot 4.0.5, Spring Data JDBC, Flyway, BoofCV 1.3.0, ONNX Runtime 1.17.1
- **Frontend:** Vue.js 3.5, TypeScript 6.0, Vite 8.0, Vue Router 5
- **Database:** PostgreSQL 18 + `pgvector` (HNSW Indexing)
- **Infrastructure:** Docker, Docker Compose, Nginx, Dokploy

## Architectural & Organizational Decisions

To accommodate the intense computational requirements of image vectorization and AI inference, several strict architectural decisions were made:

1. **Distributed Monolith over Microservices:** We retained a monolithic Spring Boot architecture to reduce network latency between the database and the vision processor. However, we decoupled the execution context by offloading all BoofCV and ONNX computations to background **Virtual Threads** via a custom `ThreadPoolTaskExecutor`. This prevents the heavy CPU workload from blocking the main Tomcat HTTP event loop.
2. **HNSW Indexing over IVFFlat:** For our `pgvector` database, we chose Hierarchical Navigable Small World (HNSW) indexes. While they require more RAM to build than IVFFlat, they provide drastically superior recall speed for our high-dimensional vectors (e.g., 1000D Semantic, 512D CIELAB).
3. **HTTP Long-Polling (`DeferredResult`):** Instead of introducing the architectural overhead of WebSockets, Redis, and STOMP to track background image extraction, we implemented Long-Polling. The client sends a request to `/status`, and the Spring backend hangs the connection securely in-memory using `DeferredResult` until the virtual thread finishes processing. This maintains statelessness while providing real-time frontend feedback.
4. **Ephemeral Local Storage (`tmpfs`):** Local and preview Docker databases are strictly mounted to RAM (`tmpfs`). This organizational decision enforces a "clean slate" upon every container restart, guaranteeing that our Flyway schema migrations (`V1` through `V3`) are always tested from zero, completely eliminating configuration drift between developer machines.
5. **Nginx Reverse Proxying:** To bypass the complexities and performance hits of CORS preflight `OPTIONS` requests, the production Vue 3.5 SPA is served via Nginx, which internally reverse-proxies `/images`, `/auth`, and `/admin` traffic directly to the backend container.

## Documentation (Wiki)

Detailed project documentation is maintained in the `docs/wiki` folder and our GitLab Wiki:

- 📖 https://gitlab.emi.u-bordeaux.fr/pdl-l3/teams/2026/v3/v3a/-/wikis/home

### Critical Enterprise Artifacts

As part of our commitment to highly scalable, enterprise-ready cloud-native development, two critical components are actively maintained:

1. **Zero-Trust State Validation:** To prevent localized supply chain tampering, the backend performs a cryptographic Subresource Integrity (SRI) validation via the Spring Boot Health Actuator. The server calculates the SHA-256 hash of a dense internal static asset (_The Whole War and Peace Novel.pdf_) bundled within the compiled `.jar`. If this deterministic baseline is altered, the system triggers a fatal structural collapse to protect runtime execution.
2. **Enterprise Operations Framework:** An exhaustive `enterprise_requirements.tex` document is included. This defines the systemic limitations of our current architecture and provides strategic guidance mandated by our lead Cloud Architect.

## Getting Started

### Prerequisites

- Docker & Docker Compose
- Java JDK 21 & Maven 3.9+ (For local non-Docker dev)
- Node.js 25+ (For local non-Docker dev)

### Quickstart (Docker Containerized Environment)

The easiest way to run the entire stack locally is via Docker Compose:

```bash
# Build and start the database, backend, and frontend
docker-compose up --build
```

- **Frontend:** Available at `http://localhost:3000`
- **Backend API:** Available at `http://localhost:8080`
- **Database:** Exposed on port `5432`

_Note: The default `admin` account is automatically seeded (Username: `admin` | Password: `admin`)._
