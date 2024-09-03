CREATE TABLE matches (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    current_status VARCHAR(100),
    result VARCHAR(100),
    best_of VARCHAR(100),
    has_rounds CHAR(0),
    starts_at TIMESTAMP,
    ends_at TIMESTAMP,
    duration_minutes INTEGER,
    team1_id BINARY(16),
    team2_id BINARY(16),
    tournament_id BINARY(16),
    FOREIGN KEY (team1_id) REFERENCES team(id),
    FOREIGN KEY (team2_id) REFERENCES team(id),
    FOREIGN KEY (tournament_id) REFERENCES tournament(id)

)