ALTER TABLE images ADD COLUMN IF NOT EXISTS provider VARCHAR(50) DEFAULT 'LOCAL';
ALTER TABLE images ADD COLUMN IF NOT EXISTS provider_id VARCHAR(100);
ALTER TABLE images ADD COLUMN IF NOT EXISTS description TEXT;
ALTER TABLE images ADD COLUMN IF NOT EXISTS photographer_name VARCHAR(255);
ALTER TABLE images ADD COLUMN IF NOT EXISTS photographer_username VARCHAR(255);
ALTER TABLE images ADD COLUMN IF NOT EXISTS camera_make VARCHAR(255);
ALTER TABLE images ADD COLUMN IF NOT EXISTS camera_model VARCHAR(255);
ALTER TABLE images ADD COLUMN IF NOT EXISTS iso INT;
ALTER TABLE images ADD COLUMN IF NOT EXISTS location_country VARCHAR(255);
ALTER TABLE images ADD COLUMN IF NOT EXISTS location_city VARCHAR(255);
ALTER TABLE images ADD COLUMN IF NOT EXISTS stats_downloads BIGINT DEFAULT 0;
ALTER TABLE images ADD COLUMN IF NOT EXISTS remote_url TEXT;

-- Create indexes to make the Admin Catalog searching lightning fast
CREATE INDEX IF NOT EXISTS idx_images_provider_id ON images(provider_id);
CREATE INDEX IF NOT EXISTS idx_images_status ON images(extraction_status);