-- V6__MigrateToSiglip.sql
-- Drop the old 1000-dimension vector
ALTER TABLE imagedescriptors DROP COLUMN semanticvector;

-- Add the new 768-dimension vector for SigLIP 2 Base
ALTER TABLE imagedescriptors ADD COLUMN semanticvector vector(768);

-- Rebuild the HNSW index for lightning-fast Cosine Similarity searches
CREATE INDEX IF NOT EXISTS idx_hnsw_semantic 
ON imagedescriptors USING hnsw (semanticvector vector_cosine_ops) 
WITH (m=32, ef_construction=256);
