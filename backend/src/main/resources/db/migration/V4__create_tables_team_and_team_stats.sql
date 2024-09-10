-- criar a tabela team e team_status sem foreign keys, pois sera relacao one-to-one
CREATE TABLE team_stats (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    wins INTEGER,
    losses INTEGER,
    draws INTEGER,
    matches_played INTEGER,
    current_position INTEGER,
    team_id BINARY(16) UNIQUE,
    tournament_id BINARY(36)
);

CREATE TABLE team (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(250),
    created_at TIMESTAMP,
    open_to_invite BOOLEAN,
    logo_url VARCHAR(250),
    created_by BINARY(16),
    team_stats_id BINARY(16) UNIQUE,
    FOREIGN KEY (created_by) REFERENCES users(id)
);


-- adicionar as foreign keys depois de criar as tabelas
ALTER TABLE team_stats
ADD CONSTRAINT fk_team_stats_team_id
FOREIGN KEY (team_id) REFERENCES team(id);

ALTER TABLE team
ADD CONSTRAINT fk_team_team_stats_id
FOREIGN KEY (team_stats_id) REFERENCES team_stats(id);