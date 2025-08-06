CREATE TABLE team (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(250),
    open_to_invite BOOLEAN,
    logo_url VARCHAR(250),
    created_at TIMESTAMP,
    created_by BINARY(16),
    FOREIGN KEY (created_by) REFERENCES users(id)
);

CREATE TABLE team_stats (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    wins INTEGER,
    losses INTEGER,
    draws INTEGER,
    matches_played INTEGER,
    current_position INTEGER,
    team_id BINARY(16),  -- Removido UNIQUE, pois agora é One-to-Many
    tournament_id BINARY(16),
    FOREIGN KEY (team_id) REFERENCES team(id),  -- Muitos team_stats para um team
    FOREIGN KEY (tournament_id) REFERENCES tournament(id)
);