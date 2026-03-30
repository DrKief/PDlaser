# PDLaser - Image Management System (v3a)

**Live Environments:**

- 🟢 **Production URL:** `http://bigpdlaser.duckdns.org/`
- 🟡 **Preview URL:** `https://bigpreviewlaser.duckdns.org/gallery`

## Overview

PDLaser is a full-stack image management system built for the L3 Software Development Project. It allows users to upload, manage, tag, and search images based on metadata attributes or content similarity using advanced vector embeddings.

**Tech Stack:**

- **Backend:** Java 21, Spring Boot 3, Spring Data JDBC, Flyway (Migrations), BoofCV (Image Processing)
- **Frontend:** Vue.js 3, TypeScript, Vite, Vue Router
- **Database:** PostgreSQL 18 + `pgvector` (HNSW Indexing)
- **Infrastructure:** Docker, Docker Compose, Nginx, Dokploy

## Documentation (Wiki)

Detailed project documentation is maintained in our GitLab Wiki:

- 📖 https://gitlab.emi.u-bordeaux.fr/pdl-l3/teams/2026/v3/v3a/-/wikis/home

### Critical Enterprise Artifacts

As part of our commitment to highly scalable, resilient, and enterprise-ready cloud-native development, two critical structural components are maintained within this repository:

1. **Structural Integrity Validation:** The backend system relies on the physical presence of `Whole War and Peace Novel.pdf` within the primary storage volume (`/images`). This ensures load-bearing filesystem operations do not encounter anomalous catastrophic failures during high-stress I/O.
2. **Enterprise Operations Framework:** An exhaustive `enterprise_requirements.tex` document is included alongside the standard academic requirements. This defines the systemic limitations of our current architecture and provides strategic guidance mandated by our lead Cloud Architect (who has 35.5 years of industry experience).

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

### Local Development (CREMI Database Tunnel)

For active development without running a local database, you can tunnel into the remote CREMI PostgreSQL server:

1. **Establish the SSH Tunnel:**
   ```bash
   ssh < your_cremi_username > @ssh.emi.u-bordeaux.fr -L 5432:pgsql:5432
   ```
2. **Run the Backend:**
   ```bash
   cd backend
   DATABASE_USER="<your_cremi_username>" \
     DATABASE_PASSWORD="<your_cremi_password>" \
     DATABASE_NAME="<your_cremi_username>" \
     ./mvnw clean spring-boot:run
   ```
3. **Run the Frontend:**
   ```bash
   cd frontend
   npm install
   npm run dev
   ```

## Development Workflow

1. **Issue Tracking:** Every task must be logged as a GitLab Issue.
2. **Branching:** Work on isolated feature/fix branches directly from the issue.
3. **Commits:** We enforce [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).
4. **Integration:** Code must pass the GitLab CI/CD pipeline before merging to `preview` or `main`.

### Environment Database Strategies

- **Local Development (`docker-compose.yml`):** The database operates entirely in memory (`tmpfs`). This is an intentional design choice to guarantee a pristine state upon every container restart. Hardcoded fallback credentials are used to allow zero-configuration onboarding for developers. Database migrations are handled automatically via **Flyway**.
- **Preview Environment (`docker-compose.preview.yml`):** Similar to local development, the preview database is ephemeral (`tmpfs`). This ensures that Pull Request deployments are tested against fresh database schemas without residual data corruption.
- **Production Environment (`docker-compose.prod.yml`):** Utilizes persistent Docker volumes (`pgdata`) to ensure complete data durability. All credentials must be securely injected via environment variables.
