CREATE TABLE invite_team (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    invite_status VARCHAR(20),

    invited_by BINARY(16),
    invited_target BINARY(16),
    team_target BINARY(16),

    FOREIGN KEY (invited_by) REFERENCES users(id),
    FOREIGN KEY (invited_target) REFERENCES users(id),
    FOREIGN KEY (team_target) REFERENCES team(id)
);

CREATE TABLE invite_tournament (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    invite_status VARCHAR(20),

    sender_type VARCHAR(20),

    team_id BINARY(16) NOT NULL,
    tournament_id BINARY(16) NOT NULL,

    FOREIGN KEY (team_id) REFERENCES team(id),
    FOREIGN KEY (tournament_id) REFERENCES tournament(id)
);