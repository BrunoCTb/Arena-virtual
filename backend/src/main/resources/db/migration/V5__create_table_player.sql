CREATE TABLE player (

    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    username VARCHAR(30),
    birth DATE,
    image_url VARCHAR(250),
    modalities VARCHAR(500),
    team_id BINARY(16),
    FOREIGN KEY (team_id) REFERENCES team(id)

)