# Database Schema

The application persists data using **PostgreSQL 18**. It leverages the `pgvector` extension to store mathematical vector embeddings for content-based image retrieval (similarity search). 

*Note: The database schema is version-controlled and automatically migrated on startup using **Flyway** (`src/main/resources/db/migration/V1__Initial_schema.sql`).*

## Tables

### 1. `images`
Stores core metadata. The actual binary content is stored on the filesystem (e.g., `/var/lib/pdl/images`), while this table maintains references.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | BIGSERIAL | PRIMARY KEY | Unique identifier. |
| `filename` | VARCHAR(255) | NOT NULL | Original filename. |
| `format` | VARCHAR(10) | NOT NULL | File extension (`jpeg`, `png`). |
| `width` | INT | DEFAULT 0 | Image width in pixels. |
| `height` | INT | DEFAULT 0 | Image height in pixels. |
| `hash` | VARCHAR(64) | UNIQUE | SHA-256 hash used to prevent duplicate uploads. |
| `extraction_status` | VARCHAR(20)| DEFAULT 'PENDING' | Status of async descriptor extraction (`PENDING`, `PROCESSING`, `COMPLETED`, `FAILED`). |

### 2. `imagedescriptors`
Stores the mathematically extracted feature vectors (calculated asynchronously via BoofCV in Java) used for visual similarity scoring.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `imageid` | BIGINT | PK, FK (`images.id`) | Links to the parent image (`ON DELETE CASCADE`). |
| `hogvector` | vector(31) | | Histogram of Oriented Gradients (Shape/Edge data). |
| `hsvvector` | vector(256) | | Hue/Saturation/Value histogram (Color Intensity). |
| `rgbvector` | vector(512) | | Standard RGB color distribution. |
| `labvector` | vector(512) | | CIELAB color space distribution (Human vision mapping). |

**Indexes:** This table utilizes `hnsw` (Hierarchical Navigable Small World) indexes on all vector columns. The index parameters are optimized for their respective dimensions and distance operators to maintain high accuracy and query speed:
- HOG (31D): `vector_l2_ops`, `m=4, ef_construction=32`
- HSV (256D): `vector_l2_ops`, `m=16, ef_construction=128`
- RGB (512D): `vector_l2_ops`, `m=32, ef_construction=256`
- CIELAB (512D): `vector_cosine_ops`, `m=32, ef_construction=256`

### 3. `imagekeywords`
Handles many-to-many tag relationships. Tags are automatically normalized (converted to lowercase, spaces replaced by underscores) before insertion.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `imageid` | BIGINT | PK, FK (`images.id`) | Links to the parent image (`ON DELETE CASCADE`). |
| `keyword` | VARCHAR(255) | PK | The text tag (e.g., `nature`, `vacation`). |