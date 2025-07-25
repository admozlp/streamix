-- Create privileges table
CREATE TABLE privileges (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    deleted BOOLEAN DEFAULT FALSE
); 