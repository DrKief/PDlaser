CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS images (
    id BIGSERIAL PRIMARY KEY,
    filename VARCHAR(255) NOT NULL,
    format VARCHAR(10) NOT NULL,
    width INT NOT NULL DEFAULT 0,
    height INT NOT NULL DEFAULT 0,
    hash VARCHAR(64) UNIQUE,
    extraction_status VARCHAR(20) DEFAULT 'PENDING'
);

CREATE TABLE IF NOT EXISTS imagedescriptors (
    imageid BIGINT REFERENCES images(id) ON DELETE CASCADE,
    hogvector vector(31),
    hsvvector vector(256),
    rgbvector vector(512),
    labvector vector(512),
    PRIMARY KEY (imageid)
);

CREATE TABLE IF NOT EXISTS imagekeywords (
    imageid BIGINT REFERENCES images(id) ON DELETE CASCADE,
    keyword VARCHAR(255) NOT NULL,
    PRIMARY KEY (imageid, keyword)
);

CREATE INDEX IF NOT EXISTS idx_hnsw_hog ON imagedescriptors USING hnsw (hogvector vector_l2_ops) WITH (m=4, ef_construction=32);
CREATE INDEX IF NOT EXISTS idx_hnsw_hsv ON imagedescriptors USING hnsw (hsvvector vector_l2_ops) WITH (m=16, ef_construction=128);
CREATE INDEX IF NOT EXISTS idx_hnsw_rgb ON imagedescriptors USING hnsw (rgbvector vector_l2_ops) WITH (m=32, ef_construction=256);