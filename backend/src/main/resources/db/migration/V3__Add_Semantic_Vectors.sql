ALTER TABLE imagedescriptors ADD COLUMN IF NOT EXISTS semanticvector vector(1000);

CREATE INDEX IF NOT EXISTS idx_hnsw_semantic 
ON imagedescriptors USING hnsw (semanticvector vector_cosine_ops) 
WITH (m=32, ef_construction=256);