CREATE TABLE format (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(250),
    min_teams_quantity INTEGER,
    max_teams_quantity INTEGER,
    matches_by_round INTEGER,
    number_of_divisions INTEGER,
    teams_by_division INTEGER,
    advance_to_next INTEGER,
    format_description VARCHAR(250)

)