-- Insert default admin user
-- Password: admin123 (hashed with BCrypt)
INSERT INTO users (email, password, name, enabled, token_expired, deleted, created, modified)
VALUES ('admin@streamix.com', '$2a$12$AyFQShXEFil4EMqFvfDntupvKx1em.NDZ5o6HqVl6kc8.S8IMnt1C', 'System Administrator',
        true, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Assign ADMIN role to the default admin user
INSERT INTO user_roles (user_id, role_id, created, modified)
SELECT u.id, r.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM users u,
     roles r
WHERE u.email = 'admin@streamix.com'
  AND r.name = 'ADMIN';