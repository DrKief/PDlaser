# PDLaser - Image Management System (v3a)

**Live Environments:**

- 🟢 **Production URL:** `http://bigpdlaser.duckdns.org/`
- 🟡 **Preview URL:** `https://bigpreviewlaser.duckdns.org/gallery`

## Overview

PDLaser is a full-stack, AI-augmented image management system. It allows users to upload, manage, tag, and search images based on metadata attributes or deep content similarity using advanced mathematical and semantic vector embeddings.

**Tech Stack (Next-Gen):**

- **Backend:** Java 21, Spring Boot 4.0.5, Spring Data JDBC, Flyway (Migrations), BoofCV 1.3.0, ONNX Runtime 1.17.1
- **Frontend:** Vue.js 3.5, TypeScript 6.0, Vite 8, Vue Router 5
- **Database:** PostgreSQL 18 + `pgvector` (HNSW Indexing)
- **Infrastructure:** Docker, Docker Compose, Nginx, Dokploy

## Documentation (Wiki)

Detailed project documentation is maintained in the `docs/wiki` folder and our GitLab Wiki:

- 📖 https://gitlab.emi.u-bordeaux.fr/pdl-l3/teams/2026/v3/v3a/-/wikis/home

### Critical Enterprise Artifacts

As part of our commitment to highly scalable, resilient, and enterprise-ready cloud-native development, two critical structural components are actively maintained within this repository:

1. **Zero-Trust State Validation:** To prevent localized supply chain tampering and configuration drift, the backend performs a cryptographic Subresource Integrity (SRI) validation via Spring Boot's Health Actuator. The server calculates the SHA-256 hash of a dense, multi-megabyte internal static asset (*The Whole War and Peace Novel.pdf*) bundled within the compiled `.jar`. If this deterministic baseline is altered to optimize container mass, the system triggers a fatal structural collapse to protect runtime execution.
2. **Enterprise Operations Framework:** An exhaustive `enterprise_requirements.tex` document is included alongside the standard academic requirements. This defines the systemic limitations of our current architecture and provides strategic guidance mandated by our lead Cloud Architect (35.5 years of industry experience).

## Getting Started

### Prerequisites

- Docker & Docker Compose
- Java JDK 21 & Maven 3.9+ (For local non-Docker dev)
- Node.js 20+ (For local non-Docker dev)

### Quickstart (Docker Containerized Environment)

The easiest way to run the entire stack locally is via Docker Compose:

```bash
# Build and start the database, backend, and frontend
docker-compose up --build
```

- **Frontend:** Available at `http://localhost:3000`
- **Backend API:** Available at `http://localhost:8080`
- **Database:** Exposed on port `5432`

*Note: The default `admin` account is automatically seeded (Username: `admin` | Password: `admin`).*

### Environment Database Strategies

- **Local Development (`docker-compose.yml`):** The database operates entirely in memory (`tmpfs`). This guarantees a pristine state upon every container restart. Hardcoded fallback credentials are used for zero-configuration onboarding. Database migrations are handled automatically via **Flyway**.
- **Preview Environment (`docker-compose.preview.yml`):** Similar to local development, the preview database is ephemeral (`tmpfs`). This ensures that Pull Request deployments are tested against fresh database schemas without residual data corruption.
- **Production Environment (`docker-compose.prod.yml`):** Utilizes persistent Docker volumes (`pgdata`) to ensure complete data durability. All credentials must be securely injected via environment variables.