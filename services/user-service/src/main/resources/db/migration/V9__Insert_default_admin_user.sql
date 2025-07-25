-- Insert default admin user
-- Password: admin123 (hashed with BCrypt)
INSERT INTO users (email, password, name, enabled, token_expired, deleted)
VALUES ('admin@streamix.com', '$2a$12$AyFQShXEFil4EMqFvfDntupvKx1em.NDZ5o6HqVl6kc8.S8IMnt1C', 'System Administrator',
        true, false, false);

-- Assign ADMIN role to the default admin user
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u,
     roles r
WHERE u.email = 'admin@streamix.com'
  AND r.name = 'ADMIN';