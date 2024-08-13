CREATE TABLE matches (
    id CHAR(36) PRIMARY KEY,
    current_status VARCHAR(100),
    result VARCHAR(100),
    best_of VARCHAR(100),
    has_rounds CHAR(0),
    starts_at TIMESTAMP,
    ends_at TIMESTAMP,
    duration_minutes INTEGER,
    team1_id CHAR(36),
    team2_id CHAR(36),
    tournament_id CHAR(36),
    FOREIGN KEY (team1_id) REFERENCES team(id),
    FOREIGN KEY (team2_id) REFERENCES team(id),
    FOREIGN KEY (tournament_id) REFERENCES tournament(id)

)