-- criar a tabela team e team_status sem foreign keys, pois sera relacao one-to-one
CREATE TABLE team_stats (
    id CHAR(36) PRIMARY KEY,
    wins INTEGER,
    losses INTEGER,
    draws INTEGER,
    matchesPlayed INTEGER,
    currentPosition INTEGER,
    team_id CHAR(36) UNIQUE,
    tournament_id CHAR(36)
);

CREATE TABLE team (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(250),
    logo_url VARCHAR(250),
    team_stats_id CHAR(36) UNIQUE
);


-- adicionar as foreign keys depois de criar as tabelas
ALTER TABLE team_stats
ADD CONSTRAINT fk_team_stats_team_id
FOREIGN KEY (team_id) REFERENCES team(id);

ALTER TABLE team
ADD CONSTRAINT fk_team_team_stats_id
FOREIGN KEY (team_stats_id) REFERENCES team_stats(id);