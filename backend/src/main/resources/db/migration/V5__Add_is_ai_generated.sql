ALTER TABLE imagekeywords ADD COLUMN IF NOT EXISTS is_ai_generated BOOLEAN DEFAULT FALSE;
UPDATE imagekeywords SET is_ai_generated = TRUE, keyword = substring(keyword FROM 4) WHERE keyword LIKE 'ai:%';
