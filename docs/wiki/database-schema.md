# Database Schema & HNSW Graph Indexing

The application persists relational and vector data using **PostgreSQL 18** via the `pgvector` 0.1.6 extension.

_Note: Flyway migrations (`V1` through `V3`) strictly version-control this schema._

## Garage S3 Integration

As of v3a, physical file blobs are no longer stored on the container's local disk or inside the database. Images are stored in **Garage v2.2.0** (an S3-compatible backend).
PostgreSQL stores metadata and `S3 Key` identifiers.

## Tables

### 1. `images` (Core Metadata)

| Column              | Type         | Constraints       | Description                                        |
| ------------------- | ------------ | ----------------- | -------------------------------------------------- |
| `id`                | BIGSERIAL    | PRIMARY KEY       | Unique identifier. Matches the `S3 Key` prefix.    |
| `filename`          | VARCHAR(255) | NOT NULL          | Original filename.                                 |
| `hash`              | VARCHAR(64)  | UNIQUE            | SHA-256 hash preventing duplicate S3 bloat.        |
| `extraction_status` | VARCHAR(20)  | DEFAULT 'PENDING' | Tracks Async thread state.                         |
| `user_id`           | BIGINT       | FK                | Uploader account relationship.                     |
| `provider_id`       | VARCHAR(100) |                   | External dataset ID (e.g., Unsplash).              |
| `remote_url`        | TEXT         |                   | Used by background workers to buffer S3 downloads. |

### 2. `imagedescriptors` (Vector Indexing)

Stores mathematical arrays extracted via BoofCV 1.3.0 and ONNX 1.24.3.
| Column | Type | Description |
|---|---|---|
| `hogvector` | vector(31) | Histogram of Oriented Gradients (Shape Data). |
| `hsvvector` | vector(256) | Hue/Saturation Histogram. |
| `rgbvector` | vector(512) | Standard RGB color map. |
| `labvector` | vector(512) | CIELAB space distribution (Human vision modeling). |
| `semanticvector`| vector(768)| Deep semantic logits from SigLIP 2 AI model. |

## Architectural Decision: HNSW Indexes

The database uses Hierarchical Navigable Small World (HNSW) indexes for similarity searches.

Parameters tuned for high-dimensional recall vs. build cost:

- **HOG (31D):** `vector_l2_ops` | `m=4, ef_construction=32`
- **HSV (256D):** `vector_l2_ops` | `m=16, ef_construction=128`
- **RGB/LAB (512D):** `vector_cosine_ops` | `m=32, ef_construction=256`
- **Semantic (768D):** `vector_cosine_ops` | `m=32, ef_construction=256`

### 3. `imagekeywords`

Handles string tags and AI inferences.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `imageid` | BIGINT | PK, FK | Parent image linkage. |
| `keyword` | VARCHAR | PK | Tag string (`nature`, `car`). |
| `is_ai_generated`| BOOLEAN | DEFAULT FALSE | Flags tags outputted automatically by the AI. |
