CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'ROLE_USER'
);

CREATE TABLE IF NOT EXISTS images (
    id BIGSERIAL PRIMARY KEY,
    filename VARCHAR(255) NOT NULL,
    format VARCHAR(10) NOT NULL,
    width INT NOT NULL DEFAULT 0,
    height INT NOT NULL DEFAULT 0,
    hash VARCHAR(64) UNIQUE,
    extraction_status VARCHAR(20) DEFAULT 'PENDING',
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    is_private BOOLEAN DEFAULT false,
    provider VARCHAR(50) DEFAULT 'LOCAL',
    provider_id VARCHAR(100),
    description TEXT,
    photographer_name VARCHAR(255),
    photographer_username VARCHAR(255),
    camera_make VARCHAR(255),
    camera_model VARCHAR(255),
    iso INT,
    location_country VARCHAR(255),
    location_city VARCHAR(255),
    stats_downloads BIGINT DEFAULT 0,
    remote_url TEXT
);

CREATE TABLE IF NOT EXISTS imagedescriptors (
    imageid BIGINT REFERENCES images(id) ON DELETE CASCADE,
    hogvector vector(31),
    hsvvector vector(256),
    rgbvector vector(512),
    labvector vector(512),
    semanticvector vector(768),
    PRIMARY KEY (imageid)
);

CREATE TABLE IF NOT EXISTS imagekeywords (
    imageid BIGINT REFERENCES images(id) ON DELETE CASCADE,
    keyword VARCHAR(255) NOT NULL,
    is_ai_generated BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (imageid, keyword)
);

CREATE INDEX IF NOT EXISTS idx_hnsw_hog ON imagedescriptors USING hnsw (hogvector vector_l2_ops) WITH (m=4, ef_construction=32);
CREATE INDEX IF NOT EXISTS idx_hnsw_hsv ON imagedescriptors USING hnsw (hsvvector vector_l2_ops) WITH (m=16, ef_construction=128);
CREATE INDEX IF NOT EXISTS idx_hnsw_rgb ON imagedescriptors USING hnsw (rgbvector vector_l2_ops) WITH (m=32, ef_construction=256);
CREATE INDEX IF NOT EXISTS idx_hnsw_lab ON imagedescriptors USING hnsw (labvector vector_cosine_ops) WITH (m=32, ef_construction=256);
CREATE INDEX IF NOT EXISTS idx_hnsw_semantic ON imagedescriptors USING hnsw (semanticvector vector_cosine_ops) WITH (m=32, ef_construction=256);

CREATE INDEX IF NOT EXISTS idx_images_provider_id ON images(provider_id);
CREATE INDEX IF NOT EXISTS idx_images_status ON images(extraction_status);
