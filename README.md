# Software Development Project - L3 (v3a)

**Live Environments (Coming Soon):**

- Production URL: `[Insert Public Production Link Here]`
- Preview URL: `[Insert Public Preview Link Here]`

## Overview

This repository contains the full-stack source code for the L3 Software Development Project. The application is an image management system built using Java 21 and Spring Boot for the backend, and Vue.js 3 with TypeScript for the frontend. The infrastructure uses Docker and a PostgreSQL database equipped with the pgvector extension for image descriptor storage.

## Prerequisites

Ensure the following dependencies are installed on your local environment:

- Java (JDK 21)
- Maven (version 3.9+)
- Node.js (version 20+) and npm
- Docker and Docker Compose

## Running the Project

The application can be run in three different environments depending on your current development or deployment needs.

### 1. Local Development (CREMI Database Tunnel)

This is the primary method for active backend development. It runs the Spring Boot application locally while connecting to the remote CREMI PostgreSQL database via an SSH tunnel.

**Step 1: Establish the SSH Tunnel**
Bind your local port 5432 to the CREMI database server. Leave this terminal window running in the background.

```bash
ssh <your_cremi_username>@ssh.emi.u-bordeaux.fr -L 5432:pgsql:5432

```

**Step 2: Run the Backend**
Open a new terminal, navigate to the backend directory, and start the application. Pass your CREMI credentials as environment variables.

```bash
cd backend
DATABASE_USER="<your_cremi_username>" \
DATABASE_PASSWORD="<your_cremi_password>" \
DATABASE_NAME="<your_cremi_username>" \
./mvnw clean spring-boot:run

```

The backend API will be available at `http://localhost:8080`.

**Step 3: Run the Frontend**
Open another terminal, navigate to the frontend directory, install dependencies, and start the Vite development server.

```bash
cd frontend
npm install
npm run dev

```

### 2. Local Containerized Environment (Docker)

_Note: This local deployment method is currently untested and pending validation._

To launch the entire stack locally (database, backend, and frontend) without relying on the CREMI network, use Docker Compose.

```bash
docker-compose up --build

```

This provisions a local PostgreSQL container, starts the backend on port 8080, and serves the frontend via Nginx on port 80.

### 3. Cloud Deployment (Dokploy)

Our production and staging environments are hosted on a private Oracle Cloud Ubuntu instance, provisioned via Terraform.

- **Management:** Services, reverse proxying, and domain routing are managed via Dokploy.
- **Access:** The Dokploy instance is secured and restricted to authorized project members.
- **Environments:**
  - **Production:** Deploys automatically from the `main` branch.
  - **Preview/Staging:** Deploys from the `preview` branch, utilizing a separate subdomain for testing before merging into production.

## Development Workflow

Our team follows a strict issue-driven workflow.

1. **Issue Tracking:** Every task or bug must be logged as a GitLab Issue, categorized with labels, assigned to a team member, and attached to a milestone.
2. **Branching:** Work must be done on isolated feature or fix branches created directly from the corresponding issue. Direct commits to `main` are restricted.
3. **Integration:** Code must pass the GitLab CI/CD pipeline before being merged into the `preview` or `main` branches.

## Commit Standards

We enforce the [Conventional Commits 1.0.0 specification](https://www.conventionalcommits.org/en/v1.0.0/#examples) to maintain a readable, machine-parseable commit history and simplify changelog generation.

**Format:** `<type>[optional scope]: <description>`

- Use `feat:` for new features and `fix:` for bug fixes.
- Indicate breaking changes with a `!` after the type/scope (e.g., `feat!: upgrade framework`) or via a `BREAKING CHANGE:` footer.

## CI/CD Pipeline

The project utilizes GitLab CI/CD, configured in `.gitlab-ci.yml`. The pipeline handles:

- **Compilation:** Verifies that the backend compiles successfully on every push.
- **Testing:** Executes automated tests (database-dependent tests are isolated or disabled until the test database container configuration is finalized).
