-- Insert initial privileges
INSERT INTO privileges (name, deleted) VALUES
('USER_READ', false),
('USER_WRITE', false),
('USER_DELETE', false),
('ROLE_READ', false),
('ROLE_WRITE', false),
('ROLE_DELETE', false),
('ADMIN_ALL', false),
('PROFILE_READ', false),
('PROFILE_WRITE', false),
('CONTENT_READ', false),
('CONTENT_WRITE', false),
('CONTENT_DELETE', false); 