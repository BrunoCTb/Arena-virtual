CREATE TABLE tournament (

    id CHAR(36) PRIMARY KEY,
    title VARCHAR(250),
    modality VARCHAR(100),
    online_mode BOOLEAN,
    teamsQuantity INTEGER,
    imageRepresentationUrl VARCHAR(250),
    format_id CHAR(36),
    FOREIGN KEY (format_id) REFERENCES format(id)

)