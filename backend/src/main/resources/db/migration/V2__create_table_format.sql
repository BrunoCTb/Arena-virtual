CREATE TABLE format (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(250),
    
    teamsDivision INTEGER,
    teamsForNextStage INTEGER,
    
    formatDescription VARCHAR(250)

)