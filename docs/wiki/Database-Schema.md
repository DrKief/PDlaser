# Database Schema & HNSW Graph Indexing

The application persists data using **PostgreSQL 18**. It heavily leverages the `pgvector` extension to store multi-dimensional mathematical vectors for content-based image retrieval (similarity search).

_Note: The database schema is strictly version-controlled and automatically migrated on initialization using **Flyway** (`src/main/resources/db/migration`)._

## Tables

### 1. `users` (Introduced V2)

Handles authentication and role-based access control.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | BIGSERIAL | PRIMARY KEY | Unique user identifier. |
| `username` | VARCHAR(255) | UNIQUE, NOT NULL | Account login name. |
| `password` | VARCHAR(255) | NOT NULL | BCrypt encoded password hash. |
| `role` | VARCHAR(50) | DEFAULT 'ROLE_USER' | JWT Authority claim (e.g., `ROLE_ADMIN`). |

### 2. `images` (Introduced V1)

Stores core metadata. The actual binary content is safely persisted on the filesystem (`/var/lib/pdl/images`), while this table maintains references to prevent DB bloat.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | BIGSERIAL | PRIMARY KEY | Unique identifier. |
| `filename` | VARCHAR(255) | NOT NULL | Original filename. |
| `format` | VARCHAR(10) | NOT NULL | File extension (`jpeg`, `png`). |
| `width` | INT | DEFAULT 0 | Image width in pixels (Extracted via fast headers). |
| `height` | INT | DEFAULT 0 | Image height in pixels. |
| `hash` | VARCHAR(64) | UNIQUE | SHA-256 hash used to prevent storage duplicate uploads. |
| `extraction_status` | VARCHAR(20)| DEFAULT 'PENDING' | Status of async descriptor extraction. |
| `user_id` | BIGINT | FK (`users.id`) | Links to the uploader account (`ON DELETE CASCADE`). |
| `is_private` | BOOLEAN | DEFAULT false | Controls public visibility scoping across queries. |

### 3. `imagedescriptors` (Introduced V1, Expanded V3)

Stores the mathematically extracted feature vectors (calculated asynchronously via BoofCV 1.3.0 and ONNX 1.17.1) used for visual similarity scoring.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `imageid` | BIGINT | PK, FK (`images.id`) | Links to the parent image (`ON DELETE CASCADE`). |
| `hogvector` | vector(31) | | Histogram of Oriented Gradients (Shape/Edge data). |
| `hsvvector` | vector(256) | | Hue/Saturation/Value histogram (Color Intensity). |
| `rgbvector` | vector(512) | | Standard RGB color distribution. |
| `labvector` | vector(512) | | CIELAB color space distribution (Human vision mapping). |
| `semanticvector`| vector(768)| | Semantic Logits extracted from SigLIP 2 AI model. |

## Architectural Decision: HNSW Indexes

Instead of utilizing standard B-Trees or flat IVFFlat indices, this table utilizes `hnsw` (Hierarchical Navigable Small World) indexes on all vector columns to radically accelerate database-level querying without pulling data into Java memory.

The index parameters were carefully tuned based on their respective dimensions and distance operators to balance build-time RAM with ultra-fast search recall:

- **HOG (31D):** `vector_l2_ops` | `m=4, ef_construction=32`
- **HSV (256D):** `vector_l2_ops` | `m=16, ef_construction=128`
- **RGB (512D):** `vector_l2_ops` | `m=32, ef_construction=256`
- **CIELAB (512D):** `vector_cosine_ops` | `m=32, ef_construction=256`
- **Semantic (1000D):** `vector_cosine_ops` | `m=32, ef_construction=256`

### 4. `imagekeywords` (Introduced V1)

Handles many-to-many tag relationships. Tags are automatically normalized (converted to lowercase, spaces replaced by underscores) before insertion. AI tags are prefixed with `ai:`.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `imageid` | BIGINT | PK, FK (`images.id`) | Links to the parent image (`ON DELETE CASCADE`). |
| `keyword` | VARCHAR(255) | PK | The text tag (e.g., `nature`, `ai:car`). |
