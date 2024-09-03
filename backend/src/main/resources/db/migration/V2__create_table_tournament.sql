CREATE TABLE tournament (

    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    title VARCHAR(250),
    modality VARCHAR(100),
    online_mode BOOLEAN,
    teams_quantity INTEGER,
    image_representation_url VARCHAR(250),
    format_id BINARY(16),
    FOREIGN KEY (format_id) REFERENCES format(id)

)