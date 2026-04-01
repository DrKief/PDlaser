CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'ROLE_USER'
);

-- Seed a default admin account. Password is 'admin123'
-- ON CONFLICT DO UPDATE ensures it fixes the password if it was previously broken
INSERT INTO users (username, password, role) 
VALUES ('admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HCGF.O0TKX0Z/0N8o5yH.', 'ROLE_ADMIN') 
ON CONFLICT (username) DO UPDATE SET password = EXCLUDED.password, role = EXCLUDED.role;

ALTER TABLE images ADD COLUMN IF NOT EXISTS user_id BIGINT REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE images ADD COLUMN IF NOT EXISTS is_private BOOLEAN DEFAULT false;

-- Enforce strict GLOBAL deduplication.
ALTER TABLE images DROP CONSTRAINT IF EXISTS images_hash_user_unique;
ALTER TABLE images ADD CONSTRAINT images_hash_key UNIQUE (hash);