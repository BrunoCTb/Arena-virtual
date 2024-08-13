package com.arenavirtual.backend.model.entity.team;


import com.arenavirtual.backend.model.entity.tournament.Tournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
    private Tournament currentTournament;


}
