CREATE TABLE inviteTeam (
    id BINARY(16) PRIMARY KEY DEFAULT (UUID()),
    created_at TIMESTAMP,

    invitedBy BINARY(16),
    invitedTarget BINARY(16),
    teamTarget BINARY(16),

    FOREIGN KEY (invitedBy) REFERENCES users(id),
    FOREIGN KEY (invitedTarget) REFERENCES users(id),
    FOREIGN KEY (teamTarget) REFERENCES team(id)
);