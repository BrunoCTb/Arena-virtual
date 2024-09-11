CREATE TABLE invite_team (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    created_at TIMESTAMP,
    invite_status VARCHAR(20),

    invited_by BINARY(16),
    invited_target BINARY(16),
    team_target BINARY(16),

    FOREIGN KEY (invited_by) REFERENCES users(id),
    FOREIGN KEY (invited_target) REFERENCES users(id),
    FOREIGN KEY (team_target) REFERENCES team(id)
);