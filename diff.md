diff --git a/.env.example b/.env.example
index 70e2d4b..7cdb761 100644
--- a/.env.example
+++ b/.env.example
@@ -9,7 +9,7 @@ DATABASE_PASSWORD=pdl_secure_password
 ADMIN_USERNAME=admin
 ADMIN_PASSWORD=admin
 
-# S3 (Garage) keys
+# S3 (RustFS) keys
 S3_ACCESS_KEY=your_access_key_here
 S3_SECRET_KEY=your_secret_key_here
 ACCESS_KEY=your_access_key_here
@@ -17,4 +17,3 @@ SECRET_KEY=your_secret_key_here
 
 # Security
 JWT_SECRET=your_jwt_secret_here
-GARAGE_RPC_SECRET=your_rpc_secret_here
diff --git a/README.md b/README.md
index 2608643..7ae00e5 100644
--- a/README.md
+++ b/README.md
@@ -14,7 +14,7 @@ PDLaser is an image management system built for visual archiving. It executes co
 - **Backend:** Java 21, Spring Boot 4.0.5, Spring Data JDBC, Flyway, BoofCV 1.3.0, ONNX Runtime 1.24.3, Twelvemonkeys ImageIO 3.13.1
 - **Frontend:** Vue.js ^3.5.32, TypeScript 6.0.2, Vite 8.0.8, Vue Router 5.0.4, Axios 1.15.0
 - **Database:** PostgreSQL 18 + `pgvector` 0.1.6 (HNSW Indexing)
-- **Infrastructure:** Docker, Nginx (alpine), Garage S3 (v2.2.0)
+- **Infrastructure:** Docker, Nginx (alpine), RustFS
 
 ---
 
@@ -24,7 +24,7 @@ This application follows the Twelve-Factor App methodology for portability and d
 
 - **I. Codebase:** One repository tracks all microservices, triggering isolated CI/CD builds.
 - **III. Config:** Routing and S3 keys are passed via environment variables.
-- **IV. Backing Services:** PostgreSQL and Garage S3 are treated as detached, swappable resources.
+- **IV. Backing Services:** PostgreSQL and RustFS are treated as detached, swappable resources.
 - **VI. Processes:** The Java Spring Boot API and Vue SPA are completely stateless. JWT tokens manage sessions.
 - **VIII. Concurrency:** Workloads scale horizontally. Java 21 Virtual Threads handle ONNX tensor extraction.
 - **IX. Disposability:** Database row locks (`FOR UPDATE SKIP LOCKED`) allow the extraction queue to recover if a container crashes.
@@ -91,10 +91,10 @@ docker compose -f docker-compose.prod.yml up -d --build
 
 If you need to run the code natively on your host OS (Arch/Ubuntu) for debugging or IDE breakpoints, you must still spin up the backing services.
 
-**Step 1: Start the Backing Services (DB & Garage S3)**
+**Step 1: Start the Backing Services (DB & RustFS)**
 
 ```bash
-docker compose -f docker-compose.dev.yml up db garage garage-init -d
+docker compose -f docker-compose.dev.yml up db rustfs rustfs-init -d
 ```
 
 **Step 2: Run the Java Backend natively**
diff --git a/backend/src/main/java/pdl/backend/config/S3Config.java b/backend/src/main/java/pdl/backend/config/S3Config.java
index 47f3608..f04d8e3 100644
--- a/backend/src/main/java/pdl/backend/config/S3Config.java
+++ b/backend/src/main/java/pdl/backend/config/S3Config.java
@@ -33,7 +33,7 @@ public class S3Config {
       .credentialsProvider(
         StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))
       )
-      .forcePathStyle(true) // Crucial for Garage
+      .forcePathStyle(true) // Crucial for RustFS S3 compatibility
       .serviceConfiguration(
         software.amazon.awssdk.services.s3.S3Configuration.builder()
           .chunkedEncodingEnabled(false)
diff --git a/backend/src/main/java/pdl/backend/gallery/core/FileStorageService.java b/backend/src/main/java/pdl/backend/gallery/core/FileStorageService.java
index 0d3c0c4..120e71f 100644
--- a/backend/src/main/java/pdl/backend/gallery/core/FileStorageService.java
+++ b/backend/src/main/java/pdl/backend/gallery/core/FileStorageService.java
@@ -93,7 +93,7 @@ public class FileStorageService {
         img.setData(bytes);
         return Optional.of(img);
       } catch (S3Exception e) {
-        log.error("Failed to load from Garage S3 for ID: " + id, e);
+        log.error("Failed to load from RustFS for ID: " + id, e);
       }
     }
     return Optional.empty();
@@ -112,7 +112,7 @@ public class FileStorageService {
           .build();
         s3Client.deleteObject(delReq);
       } catch (S3Exception e) {
-        log.error("Failed to delete from Garage S3 for ID: " + id, e);
+        log.error("Failed to delete from RustFS for ID: " + id, e);
       }
       recordRepository.delete(img);
       return true;
diff --git a/backend/src/main/resources/application.yaml b/backend/src/main/resources/application.yaml
index a688aa7..2e29bc5 100644
--- a/backend/src/main/resources/application.yaml
+++ b/backend/src/main/resources/application.yaml
@@ -47,8 +47,8 @@ management:
         enabled: true
 
 s3:
-  endpoint: ${S3_ENDPOINT:http://localhost:3900}
-  region: ${S3_REGION:garage}
+  endpoint: ${S3_ENDPOINT:http://localhost:9000}
+  region: ${S3_REGION:us-east-1}
   access-key: ${S3_ACCESS_KEY}
   secret-key: ${S3_SECRET_KEY}
   bucket: ${S3_BUCKET:pdl-images}
diff --git a/docker-compose.dev.yml b/docker-compose.dev.yml
index bd08500..c15dffd 100644
--- a/docker-compose.dev.yml
+++ b/docker-compose.dev.yml
@@ -21,30 +21,33 @@ services:
       retries: 5
     restart: unless-stopped
 
-  garage:
-    image: dxflrs/garage:v2.2.0
+  rustfs:
+    image: rustfs/rustfs:latest
     env_file:
       - path: .env.example
         required: true
       - path: .env
         required: false
+    environment:
+      RUSTFS_ACCESS_KEY: ${S3_ACCESS_KEY}
+      RUSTFS_SECRET_KEY: ${S3_SECRET_KEY}
+      RUSTFS_CONSOLE_ENABLE: "true"
+      RUSTFS_ADDRESS: ":9000"
     ports:
-      - "3900:3900" # S3 API
-    tmpfs:
-      - /var/lib/garage/data
-      - /var/lib/garage/meta
+      - "3900:9000" # S3 API
+      - "3901:9001" # Web UI Console
     volumes:
-      - ./garage.toml:/etc/garage.toml
-    command: ["/garage", "server"]
+      - type: tmpfs
+        target: /data
+        tmpfs:
+          mode: 0777
+    command: ["/data"]
     restart: unless-stopped
 
-  garage-init:
-    image: alpine:3.23
+  rustfs-init:
+    image: minio/mc:latest
     depends_on:
-      garage:
-        condition: service_started
-    network_mode: "service:garage"
-    pid: "service:garage"
+      - rustfs
     env_file:
       - path: .env.example
         required: true
@@ -58,30 +61,13 @@ services:
       - -eu
       - -c
       - |
-        # Use the binary from the live Garage container
-        G="chroot /proc/1/root /garage"
-
-        echo "Waiting for Garage daemon..."
-        for i in $$(seq 1 20); do
-          if $$G node id -q 2>/dev/null; then break; fi
+        echo "Waiting for RustFS to start..."
+        until mc alias set localrustfs http://rustfs:9000 "$${S3_ACCESS_KEY}" "$${S3_SECRET_KEY}" > /dev/null 2>&1; do
           sleep 2
         done
-
-        # 1. Initialize the layout if it hasn't been assigned yet
-        if $$G status 2>&1 | grep -q "NO ROLE ASSIGNED"; then
-          echo "Initializing single-node layout..."
-          NODE_ID=$$($$G node id -q)
-          $$G layout assign "$$NODE_ID" -z dc1 -c 1G
-          $$G layout apply --version 1
-        fi
-
-        # 2. Provision Bucket and Key
-        echo "Configuring bucket and credentials..."
-        $$G key import --yes -n pdl-key "$$ACCESS_KEY" "$$SECRET_KEY" || true
-        $$G bucket create "$$BUCKET_NAME" || true
-        $$G bucket allow "$$BUCKET_NAME" --read --write --owner --key pdl-key || true
-
-        echo "Garage S3 initialized successfully."
+        echo "Creating bucket $${BUCKET_NAME}..."
+        mc mb localrustfs/$${BUCKET_NAME} || true
+        echo "RustFS Initialized successfully."
 
   backend:
     build:
@@ -98,8 +84,8 @@ services:
       - DATABASE_USER=pdl_user
       - APP_UNSPLASH_DATASET_DIR=/var/lib/pdl/datasets
       - APP_MODELS_DIR=/var/lib/pdl/models
-      - S3_ENDPOINT=http://garage:3900
-      - S3_REGION=garage
+      - S3_ENDPOINT=http://rustfs:9000
+      - S3_REGION=us-east-1
       - S3_BUCKET=pdl-images
       - LOGGING_LEVEL_ORG_SPRINGFRAMEWORK=WARN
     volumes:
@@ -110,7 +96,7 @@ services:
     depends_on:
       db:
         condition: service_healthy
-      garage:
+      rustfs:
         condition: service_started
     restart: unless-stopped
 
diff --git a/docker-compose.preview.yml b/docker-compose.preview.yml
index 9654a9f..cfeb012 100644
--- a/docker-compose.preview.yml
+++ b/docker-compose.preview.yml
@@ -22,71 +22,60 @@ services:
         max-size: "10m"
         max-file: "3"
 
-  garage:
-    image: dxflrs/garage:v2.2.0
+  rustfs:
+    image: rustfs/rustfs:latest
     env_file:
       - path: .env.example
         required: true
       - path: .env
         required: false
     environment:
-      GARAGE_RPC_SECRET: ${GARAGE_RPC_SECRET}
+      RUSTFS_ACCESS_KEY: ${S3_ACCESS_KEY}
+      RUSTFS_SECRET_KEY: ${S3_SECRET_KEY}
+      RUSTFS_CONSOLE_ENABLE: "true"
+      RUSTFS_ADDRESS: ":9000"
     expose:
-      - "3900"
-    tmpfs:
-      - /var/lib/garage/data
-      - /var/lib/garage/meta
+      - "9000"
+      - "9001"
     volumes:
-      - ./garage.toml:/etc/garage.toml
-    command: ["/garage", "server"]
-    healthcheck: # FIX: Direct array execution, no shell interpretation
-      test: ["CMD", "/garage", "status"]
+      - type: tmpfs
+        target: /data
+        tmpfs:
+          mode: 0777
+    command: ["/data"]
+    healthcheck:
+      test: ["CMD", "curl", "-f", "http://localhost:9000/minio/health/live"]
       interval: 5s
       timeout: 3s
       retries: 5
     restart: unless-stopped
     logging: *default-logging
 
-  garage-init:
-    image: alpine:3.23
+  rustfs-init:
+    image: minio/mc:latest
     env_file:
       - path: .env.example
         required: true
       - path: .env
         required: false
     depends_on:
-      garage:
+      rustfs:
         condition: service_healthy
-    network_mode: "service:garage"
-    pid: "service:garage"
     environment:
-      - ACCESS_KEY=${S3_ACCESS_KEY}
-      - SECRET_KEY=${S3_SECRET_KEY}
       - BUCKET_NAME=pdl-images
-      - GARAGE_RPC_SECRET=${GARAGE_RPC_SECRET}
     restart: "on-failure:3"
     entrypoint:
       - /bin/sh
       - -eu
       - -c
       - |
-        G="chroot /proc/1/root /garage"
-        echo "Waiting for Garage daemon..."
-        for i in $$(seq 1 20); do
-          if $$G node id -q 2>/dev/null; then break; fi
+        echo "Waiting for RustFS to start..."
+        until mc alias set localrustfs http://rustfs:9000 "$${S3_ACCESS_KEY}" "$${S3_SECRET_KEY}" > /dev/null 2>&1; do
           sleep 2
         done
-        if $$G status 2>&1 | grep -q "NO ROLE ASSIGNED"; then
-          echo "Initializing single-node layout..."
-          NODE_ID=$$($$G node id -q)
-          $$G layout assign "$$NODE_ID" -z dc1 -c 1G
-          $$G layout apply --version 1
-        fi
-        echo "Configuring bucket and credentials..."
-        $$G key import --yes -n pdl-key "$$ACCESS_KEY" "$$SECRET_KEY" || true
-        $$G bucket create "$$BUCKET_NAME" || true
-        $$G bucket allow "$$BUCKET_NAME" --read --write --owner --key pdl-key || true
-        echo "Garage S3 initialized successfully."
+        echo "Creating bucket $${BUCKET_NAME}..."
+        mc mb localrustfs/$${BUCKET_NAME} || true
+        echo "RustFS Initialized successfully."
 
   backend:
     build:
@@ -106,8 +95,8 @@ services:
       - APP_MODELS_DIR=/var/lib/pdl/models
       - ADMIN_USERNAME=${ADMIN_USERNAME}
       - ADMIN_PASSWORD=${ADMIN_PASSWORD}
-      - S3_ENDPOINT=http://garage:3900
-      - S3_REGION=garage
+      - S3_ENDPOINT=http://rustfs:9000
+      - S3_REGION=us-east-1
       - S3_ACCESS_KEY=${S3_ACCESS_KEY}
       - S3_SECRET_KEY=${S3_SECRET_KEY}
       - S3_BUCKET=pdl-images
@@ -121,7 +110,7 @@ services:
     depends_on:
       db:
         condition: service_healthy
-      garage:
+      rustfs:
         condition: service_healthy
     restart: unless-stopped
     logging: *default-logging
diff --git a/docker-compose.prod.yml b/docker-compose.prod.yml
index 5d04949..10d3212 100644
--- a/docker-compose.prod.yml
+++ b/docker-compose.prod.yml
@@ -22,70 +22,57 @@ services:
         max-size: "10m"
         max-file: "3"
 
-  garage:
-    image: dxflrs/garage:v2.2.0
+  rustfs:
+    image: rustfs/rustfs:latest
     env_file:
       - path: .env.example
         required: true
       - path: .env
         required: false
     environment:
-      GARAGE_RPC_SECRET: ${GARAGE_RPC_SECRET}
+      RUSTFS_ACCESS_KEY: ${S3_ACCESS_KEY}
+      RUSTFS_SECRET_KEY: ${S3_SECRET_KEY}
+      RUSTFS_CONSOLE_ENABLE: "true"
+      RUSTFS_ADDRESS: ":9000"
     expose:
-      - "3900"
+      - "9000"
+      - "9001"
     volumes:
-      - garagedata:/var/lib/garage/data
-      - garagemeta:/var/lib/garage/meta
-      - ./garage.toml:/etc/garage.toml
-    command: ["/garage", "server"]
+      - rustfsdata:/data
+    command: ["/data"]
     healthcheck:
-      test: ["CMD", "/garage", "status"]
+      test: ["CMD", "curl", "-f", "http://localhost:9000/minio/health/live"]
       interval: 5s
       timeout: 3s
       retries: 5
     restart: unless-stopped
     logging: *default-logging
 
-  garage-init:
-    image: alpine:3.23
+  rustfs-init:
+    image: minio/mc:latest
     env_file:
       - path: .env.example
         required: true
       - path: .env
         required: false
     depends_on:
-      garage:
+      rustfs:
         condition: service_healthy
-    network_mode: "service:garage"
-    pid: "service:garage"
     environment:
-      - ACCESS_KEY=${S3_ACCESS_KEY}
-      - SECRET_KEY=${S3_SECRET_KEY}
       - BUCKET_NAME=pdl-images
-      - GARAGE_RPC_SECRET=${GARAGE_RPC_SECRET}
     restart: "on-failure:3"
     entrypoint:
       - /bin/sh
       - -eu
       - -c
       - |
-        G="chroot /proc/1/root /garage"
-        echo "Waiting for Garage daemon..."
-        for i in $$(seq 1 20); do
-          if $$G node id -q 2>/dev/null; then break; fi
+        echo "Waiting for RustFS to start..."
+        until mc alias set localrustfs http://rustfs:9000 "$${S3_ACCESS_KEY}" "$${S3_SECRET_KEY}" > /dev/null 2>&1; do
           sleep 2
         done
-        if $$G status 2>&1 | grep -q "NO ROLE ASSIGNED"; then
-          echo "Initializing single-node layout..."
-          NODE_ID=$$($$G node id -q)
-          $$G layout assign "$$NODE_ID" -z dc1 -c 1G
-          $$G layout apply --version 1
-        fi
-        echo "Configuring bucket and credentials..."
-        $$G key import --yes -n pdl-key "$$ACCESS_KEY" "$$SECRET_KEY" || true
-        $$G bucket create "$$BUCKET_NAME" || true
-        $$G bucket allow "$$BUCKET_NAME" --read --write --owner --key pdl-key || true
-        echo "Garage S3 initialized successfully."
+        echo "Creating bucket $${BUCKET_NAME}..."
+        mc mb localrustfs/$${BUCKET_NAME} || true
+        echo "RustFS Initialized successfully."
 
   backend:
     build:
@@ -105,8 +92,8 @@ services:
       - APP_MODELS_DIR=/var/lib/pdl/models
       - ADMIN_USERNAME=${ADMIN_USERNAME}
       - ADMIN_PASSWORD=${ADMIN_PASSWORD}
-      - S3_ENDPOINT=http://garage:3900
-      - S3_REGION=garage
+      - S3_ENDPOINT=http://rustfs:9000
+      - S3_REGION=us-east-1
       - S3_ACCESS_KEY=${S3_ACCESS_KEY}
       - S3_SECRET_KEY=${S3_SECRET_KEY}
       - S3_BUCKET=pdl-images
@@ -120,7 +107,7 @@ services:
     depends_on:
       db:
         condition: service_healthy
-      garage:
+      rustfs:
         condition: service_healthy
     restart: unless-stopped
     logging: *default-logging
@@ -165,8 +152,7 @@ services:
 
 volumes:
   pgdata:
-  garagedata:
-  garagemeta:
+  rustfsdata:
   promdata:
   grafanadata:
 
diff --git a/docs/requirements.tex b/docs/requirements.tex
index 8b2c3fa..de919d9 100644
--- a/docs/requirements.tex
+++ b/docs/requirements.tex
@@ -23,7 +23,7 @@
 \maketitle
 
 \section*{Introduction}
-This document outlines the requirements for developing an image similarity search application using a containerized architecture backed by PostgreSQL 18, Garage S3 blob storage, and vector mathematics.
+This document outlines the requirements for developing an image similarity search application using a containerized architecture backed by PostgreSQL 18, RustFS blob storage, and vector mathematics.
 
 \subsection*{Requirement Classifications}
 To clarify the state of each requirement relative to the initial specifications (Besoins 1--27), the following tags are used.
@@ -49,7 +49,7 @@ To support computer vision tasks, the following architectural standards were est
 
 \begin{itemize}
     \item \textbf{Decoupled Background Execution:} Image vectorization (BoofCV) and semantic extraction (ONNX) are offloaded to Virtual Threads (Java 21).
-    \item \textbf{Distributed S3 Blob Storage:} The backend uses \textbf{Garage v2.2.0}, an S3-compatible object storage layer, utilizing the AWS Java SDK v2.42.32.
+    \item \textbf{Distributed S3 Blob Storage:} The backend uses \textbf{RustFS}, an S3-compatible object storage layer, utilizing the AWS Java SDK v2.42.32.
     \item \textbf{Twelve-Factor App Compliance:} The infrastructure follows the Twelve-Factor methodology by treating backing services as attached resources, using environment variables for configuration, and executing as stateless processes.
     \item \textbf{HNSW Vector Indexing:} We implemented Hierarchical Navigable Small World (HNSW) graphs in PostgreSQL 18 via \texttt{pgvector} 0.1.6 for indexing semantic vectors.
 \end{itemize}
@@ -61,7 +61,7 @@ To support computer vision tasks, the following architectural standards were est
 \subsection*{Server}
 
 \noindent\textbf{Requirement 1 : [Amended (Up) to Requirement 1] Initialize a set of images present on the server}\\
-\textbf{Description :} The server verifies the connection to the Garage S3 bucket upon launch. \textbf{Amendment:} The server calculates a SHA-256 hash of binary payloads upon upload to deduplicate images. A default \texttt{admin} user is seeded into the database.
+\textbf{Description :} The server verifies the connection to the RustFS bucket upon launch. \textbf{Amendment:} The server calculates a SHA-256 hash of binary payloads upon upload to deduplicate images. A default \texttt{admin} user is seeded into the database.
 \vspace{1em}
 
 \noindent\textbf{Requirement 2 : [Amended (Up) to Requirement 2] Manage images present on the server}\\
@@ -102,7 +102,7 @@ To support computer vision tasks, the following architectural standards were est
 \vspace{1em}
 
 \noindent\textbf{Requirement 9 : [Amended (Up) to Requirement 9] Delete an image}\\
-\textbf{Description :} A \texttt{DELETE /images/id} removes the image. \textbf{Amendment:} Protected by JWT Authority. Deletes records in PostgreSQL and the object in Garage S3.
+\textbf{Description :} A \texttt{DELETE /images/id} removes the image. \textbf{Amendment:} Protected by JWT Authority. Deletes records in PostgreSQL and the object in RustFS.
 \vspace{1em}
 
 \noindent\textbf{Requirement 10 : [Amended (Up) to Requirement 10] Transfer the list of most similar images}\\
@@ -164,7 +164,7 @@ To support computer vision tasks, the following architectural standards were est
 \vspace{1em}
 
 \noindent\textbf{Requirement 25 : [Amended (Up) to Requirement 25] Server compatibility}\\
-\textbf{Description :} Tested on Java 21, Spring Boot 4.0.5, Mockito 5.23.0, AWS SDK BOM 2.42.32, \texttt{pgvector} 0.1.6, BoofCV 1.3.0, ONNX Runtime 1.24.3, PostgreSQL 18, and Garage v2.2.0. Verified on x86\_64 Arch Linux and ARM64 Ubuntu 24.04 LTS environments.
+\textbf{Description :} Tested on Java 21, Spring Boot 4.0.5, Mockito 5.23.0, AWS SDK BOM 2.42.32, \texttt{pgvector} 0.1.6, BoofCV 1.3.0, ONNX Runtime 1.24.3, PostgreSQL 18, and RustFS. Verified on x86\_64 Arch Linux and ARM64 Ubuntu 24.04 LTS environments.
 \vspace{1em}
 
 \noindent\textbf{Requirement 26 : [Amended (Up) to Requirement 26] Client compatibility}\\
diff --git a/docs/wiki/api-reference.md b/docs/wiki/api-reference.md
index 1b5eacc..0c34704 100644
--- a/docs/wiki/api-reference.md
+++ b/docs/wiki/api-reference.md
@@ -39,7 +39,7 @@ Retrieves a paginated list of all images. Filters based on user ownership.
 
 ### Get Image Content
 
-Retrieves the binary payload of an image from Garage S3.
+Retrieves the binary payload of an image from RustFS.
 
 - **URL:** `/images/{id}`
 - **Method:** `GET`
@@ -47,7 +47,7 @@ Retrieves the binary payload of an image from Garage S3.
 
 ### Upload Image (Asynchronous)
 
-Uploads a new image file. Saves the binary to Garage S3 and computes a SHA-256 hash. Feature vectors are extracted asynchronously.
+Uploads a new image file. Saves the binary to RustFS and computes a SHA-256 hash. Feature vectors are extracted asynchronously.
 
 - **URL:** `/images`
 - **Method:** `POST`
@@ -74,7 +74,7 @@ Returns the image with a `Content-Disposition: attachment` header.
 
 ### Delete Image
 
-Deletes the image from PostgreSQL and Garage S3.
+Deletes the image from PostgreSQL and RustFS.
 
 - **URL:** `/images/{id}`
 - **Method:** `DELETE`
diff --git a/docs/wiki/database-schema.md b/docs/wiki/database-schema.md
index 383d4cc..88ae91e 100644
--- a/docs/wiki/database-schema.md
+++ b/docs/wiki/database-schema.md
@@ -4,9 +4,9 @@ The application persists relational and vector data using **PostgreSQL 18** via
 
 _Note: Flyway migrations (`V1` through `V3`) strictly version-control this schema._
 
-## Garage S3 Integration
-
-As of v3a, physical file blobs are no longer stored on the container's local disk or inside the database. Images are stored in **Garage v2.2.0** (an S3-compatible backend).
+## RustFS Integration
+ 
+As of v3a, physical file blobs are no longer stored on the container's local disk or inside the database. Images are stored in **RustFS** (a high-performance S3-compatible backend).
 PostgreSQL stores metadata and `S3 Key` identifiers.
 
 ## Tables
diff --git a/docs/wiki/deployment.md b/docs/wiki/deployment.md
index 896e7a3..85392d6 100644
--- a/docs/wiki/deployment.md
+++ b/docs/wiki/deployment.md
@@ -9,7 +9,7 @@ Key aspects include:
 1. **I. Codebase:** One repository contains the frontend, backend, and infrastructure code.
 2. **II. Dependencies:** All backend and frontend dependencies are explicitly declared in `pom.xml` and `package.json`, isolated entirely within Docker multi-stage builds. No system-level packages leak into the containers.
 3. **III. Config:** Zero hardcoded credentials exist in the source. S3 keys, Postgres passwords, and JWT Secrets are injected dynamically into the `application.yaml` via OS-level Environment Variables.
-4. **IV. Backing Services:** Garage S3 and PostgreSQL are treated as attached resources.
+4. **IV. Backing Services:** RustFS and PostgreSQL are treated as attached resources.
 5. **VI. Processes:** The backend and frontend are stateless. Authentication is handled client-side via JSON Web Tokens (JWT).
 6. **VIII. Concurrency:** The application uses Java 21 Virtual Threads for ONNX model execution.
 7. **IX. Disposability:** Database transactions use `FOR UPDATE SKIP LOCKED` for task queues.
@@ -27,17 +27,17 @@ Key aspects include:
 - **Function:** Handles all relational constraints and `hnsw` distance mathematics.
 - **Storage Parity:** Uses `tmpfs` mounts in local/preview environments to enforce deterministic zero-state testing of Flyway migrations, and physical `pgdata` volumes in production.
 
-### 2. Object Storage (`garage` & `garage-init`)
-
-- **Images:** `dxflrs/garage:v2.2.0` (Storage Node) and `alpine:3.23` (Init Job)
-- **Function:** Replaces local filesystem IO with an S3-compatible API cluster. High-performance, distributed blob storage for images.
-- **Provisioning:** A lightweight alpine init container automatically pings the Garage RPC daemon, configures the single-node deployment layout, and provisions the access keys / buckets upon startup.
+### 2. Object Storage (`rustfs` & `rustfs-init`)
+ 
+- **Images:** `rustfs/rustfs:latest` (Storage Node) and `minio/mc:latest` (Init Job)
+- **Function:** Replaces local filesystem IO with a high-performance, S3-compatible Rust storage engine. 
+- **Provisioning:** A lightweight init container utilizes the MinIO Client (`mc`) to provision access keys, structure layouts, and create buckets immediately upon startup.
 
 ### 3. Java Backend (`backend`)
 
 - **Build Stage:** `maven:3.9.6-eclipse-temurin-21-alpine`
 - **Runtime Image:** `eclipse-temurin:21-jre`
-- **Function:** Houses the Spring Boot 4.0.5 executable, DJL Tokenizers 0.36.0, and ONNX Runtime 1.24.3 engines. Uses AWS SDK BOM v2.42.32 to interface with the Garage container.
+- **Function:** Houses the Spring Boot 4.0.5 executable, DJL Tokenizers 0.36.0, and ONNX Runtime 1.24.3 engines. Uses AWS SDK BOM v2.42.32 to interface with the RustFS container.
 - **Integrity Safeguard:** Incorporates a rigorous Cryptographic Subresource Integrity check on boot. Validates a hardcoded SHA-256 hash against an internal multi-megabyte PDF payload to guarantee deployment immutability.
 
 ### 4. Telemetry Observability (`prometheus` & `grafana`)
diff --git a/docs/wiki/home.md b/docs/wiki/home.md
index 325d0cd..9c94cbd 100644
--- a/docs/wiki/home.md
+++ b/docs/wiki/home.md
@@ -2,7 +2,7 @@
 
 This is the documentation for the **PDLaser Image Management System (v3a)**.
 
-This wiki contains technical documentation for the backend API, frontend SPA, Garage S3 storage, database schema, and deployment infrastructure.
+This wiki contains technical documentation for the backend API, frontend SPA, RustFS storage, database schema, and deployment infrastructure.
 
 ## Navigation
 
@@ -20,11 +20,11 @@ In-depth breakdown of specific route targets (`/gallery`, `/search`, `/profile`,
 
 ### [Database Schema & HNSW](database-schema)
 
-Extensive details on the PostgreSQL 18 implementation utilizing the `pgvector` 0.1.6 extension. Explains the transition to Garage (S3) for blob storage, relational table structures (`images`, `imagedescriptors`), and mathematically tuned multi-dimensional HNSW vector indexing algorithms.
+Extensive details on the PostgreSQL 18 implementation utilizing the `pgvector` 0.1.6 extension. Explains the transition to RustFS for blob storage, relational table structures (`images`, `imagedescriptors`), and mathematically tuned multi-dimensional HNSW vector indexing algorithms.
 
 ### [Docker, Deployment & Twelve-Factor](deployment)
 
-Guide to our containerized microservices orchestration and **Twelve-Factor App** compliance. Explains the multi-tier Docker Compose environments, Garage S3 blob storage initialization, Prometheus 3.5.0 / Grafana 12.4.2 telemetry observability, and Cryptographic Subresource Integrity limits.
+Guide to our containerized microservices orchestration and **Twelve-Factor App** compliance. Explains the multi-tier Docker Compose environments, RustFS blob storage initialization, Prometheus 3.5.0 / Grafana 12.4.2 telemetry observability, and Cryptographic Subresource Integrity limits.
 
 ---
 
diff --git a/garage.toml b/garage.toml
deleted file mode 100644
index 25ec19c..0000000
--- a/garage.toml
+++ /dev/null
@@ -1,20 +0,0 @@
-metadata_dir = "/var/lib/garage/meta"
-data_dir = "/var/lib/garage/data"
-db_engine = "lmdb"
-
-replication_factor = 1
-consistency_mode = "consistent"
-
-rpc_bind_addr = "[::]:3901"
-
-[s3_api]
-api_bind_addr = "[::]:3900"
-s3_region = "garage"
-root_domain = ".s3.garage.localhost"
-
-[s3_web]
-bind_addr = "[::]:3902"
-root_domain = ".web.garage.localhost"
-
-[admin]
-api_bind_addr = "[::]:3903"
diff --git a/generate_env.sh b/generate_env.sh
index d12d254..2383736 100755
--- a/generate_env.sh
+++ b/generate_env.sh
@@ -4,20 +4,17 @@
 PG_PASS=$(openssl rand -base64 33 | tr -dc 'a-zA-Z0-9' | head -c 32)
 ADMIN_PASS=$(openssl rand -base64 33 | tr -dc 'a-zA-Z0-9' | head -c 32)
 
-# Garage S3 Access Key MUST start with "GK" followed by hex characters
-S3_ACCESS="GK$(openssl rand -hex 12)"
-
-# Garage S3 Secret Key and RPC Secret (RPC secret must be exactly 32 bytes hex encoded)
+# RustFS S3 Keys
+S3_ACCESS=$(openssl rand -hex 16)
 S3_SECRET=$(openssl rand -hex 32)
-GARAGE_RPC=$(openssl rand -hex 32)
 
 # Spring Boot JWT Secret (Hex encoding guarantees no special character issues, 32 bytes / 256 bits)
 JWT=$(openssl rand -hex 32)
 
 echo "POSTGRES_PASSWORD=${PG_PASS}"
+echo "DATABASE_PASSWORD=${PG_PASS}"
 echo "ADMIN_USERNAME=admin"
 echo "ADMIN_PASSWORD=${ADMIN_PASS}"
 echo "S3_ACCESS_KEY=${S3_ACCESS}"
 echo "S3_SECRET_KEY=${S3_SECRET}"
 echo "JWT_SECRET=${JWT}"
-echo "GARAGE_RPC_SECRET=${GARAGE_RPC}"
