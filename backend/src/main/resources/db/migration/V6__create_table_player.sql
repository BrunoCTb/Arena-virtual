CREATE TABLE player (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    public_id BIGINT AUTO_INCREMENT UNIQUE,
    username VARCHAR(30),
    image_url VARCHAR(250),
    user_id BINARY(16),
    team_id BINARY(16),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (team_id) REFERENCES team(id)
);