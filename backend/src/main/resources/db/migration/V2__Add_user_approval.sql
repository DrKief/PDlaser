ALTER TABLE users ADD COLUMN is_approved BOOLEAN DEFAULT FALSE;

-- Ensure existing users (like the original admin) are approved so no one is locked out
UPDATE users SET is_approved = TRUE;
