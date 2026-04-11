CREATE TABLE app_settings (
    setting_key VARCHAR(255) PRIMARY KEY,
    setting_value VARCHAR(255) NOT NULL
);

INSERT INTO app_settings (setting_key, setting_value) VALUES ('auto_approve_users', 'false');
