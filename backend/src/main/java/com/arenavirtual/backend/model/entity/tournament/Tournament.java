package com.arenavirtual.backend.model.entity.tournament;

import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.model.entity.team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title; // campeonato abc 3a edicao...
    private String modality; // futebol, jogo especifico...
    private Boolean onlineMode; // outros usuarios poderao entrar
    private Integer teamsQuantity;
    private String imageRepresentationUrl;

    @ManyToOne
    private Format format; // formato suiço, pontos corridos...

    @OneToOne
    private Player referee;

    @OneToMany
    private List<Team> teams;

    @OneToMany
    private List<Player> players;



}
