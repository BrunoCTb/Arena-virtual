package com.arenavirtual.backend.model.entity.tournament;

import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.model.entity.team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String currentStatus; // a iniciar, em andamento, finalizada
    private String result;
    private String bestOf; // melhor de 1, 3, 5...
    private Boolean hasRounds;
    private LocalDate startsAt;
    private LocalDate endsAt;
    private Integer durationMinutes;

    @OneToOne
    private Team winnerTeam;

    @ManyToOne
    private Team team1;

    @ManyToOne
    private Team team2;

    @ManyToOne
    private Tournament tournament;

    @OneToOne
    private Player host;



}
