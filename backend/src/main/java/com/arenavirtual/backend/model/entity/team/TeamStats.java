package com.arenavirtual.backend.model.entity.team;


import com.arenavirtual.backend.model.entity.tournament.Tournament;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "team_stats")
public class TeamStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private Integer matchesPlayed;
    private Integer currentPosition;

    @ManyToOne // Um time pode ter várias estatísticas em diferentes campeonatos
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne // Um time pode participar de vários torneios
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public TeamStats(Team team, Tournament tournament) {
        this.team = team;
        this.tournament = tournament;
    }
}
