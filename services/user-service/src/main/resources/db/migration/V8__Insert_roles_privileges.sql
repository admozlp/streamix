-- Insert roles-privileges relationships

-- ADMIN role - all privileges
INSERT INTO roles_privileges (role_id, privilege_id, created, modified)
SELECT r.id, p.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM roles r, privileges p
WHERE r.name = 'ADMIN';

-- USER role - basic user privileges
INSERT INTO roles_privileges (role_id, privilege_id, created, modified)
SELECT r.id, p.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM roles r, privileges p
WHERE r.name = 'USER' 
AND p.name IN ('PROFILE_READ', 'PROFILE_WRITE', 'CONTENT_READ');

-- MODERATOR role - moderate privileges
INSERT INTO roles_privileges (role_id, privilege_id, created, modified)
SELECT r.id, p.id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM roles r, privileges p
WHERE r.name = 'MODERATOR' 
AND p.name IN ('USER_READ', 'PROFILE_READ', 'PROFILE_WRITE', 'CONTENT_READ', 'CONTENT_WRITE', 'CONTENT_DELETE'); 