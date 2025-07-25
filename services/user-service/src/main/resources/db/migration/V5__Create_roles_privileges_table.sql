-- Create roles_privileges junction table
CREATE TABLE roles_privileges (
    role_id UUID NOT NULL,
    privilege_id UUID NOT NULL,
    PRIMARY KEY (role_id, privilege_id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (privilege_id) REFERENCES privileges(id) ON DELETE CASCADE
); 