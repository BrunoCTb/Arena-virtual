CREATE TABLE player (

    id CHAR(36) PRIMARY KEY,
    username VARCHAR(30),
    birth DATE,
    image_url VARCHAR(250),
    modalities VARCHAR(500),
    team_id CHAR(36),
    FOREIGN KEY (team_id) REFERENCES team(id)

)