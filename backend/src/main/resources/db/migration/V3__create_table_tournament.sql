CREATE TABLE tournament (

    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    title VARCHAR(250),
    modality VARCHAR(100),
    online_mode BOOLEAN,
    min_teams INTEGER,
    max_teams INTEGER,
    image_representation_url VARCHAR(250),
    format VARCHAR(250),
    created_at TIMESTAMP,
    start_date DATE,
    end_date DATE,
    created_by BINARY(16),

    FOREIGN KEY (created_by) REFERENCES users(id)
);