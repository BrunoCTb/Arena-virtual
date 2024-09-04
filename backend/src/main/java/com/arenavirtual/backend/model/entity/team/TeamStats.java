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
public class TeamStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private Integer matchesPlayed;
    private Integer currentPosition;

    @OneToOne
    private Team team;

    @OneToOne
    private Tournament Tournament;

    public TeamStats(Team team) {
        this.team = team;
    }
}
