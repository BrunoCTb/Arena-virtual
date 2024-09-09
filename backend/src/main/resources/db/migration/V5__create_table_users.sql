CREATE TABLE users (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    username VARCHAR(30),
    first_name VARCHAR(100),
    last_name VARCHAR(250),
    email VARCHAR(250),
    password VARCHAR(250),
    birth DATE,
    image_url VARCHAR(250)
);

CREATE TABLE player (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    username VARCHAR(30),
    image_url VARCHAR(250),
    user_id BINARY(16),
    team_id BINARY(16),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (team_id) REFERENCES team(id)
);