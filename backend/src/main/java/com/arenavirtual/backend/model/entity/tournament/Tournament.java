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
@Table(name = "tournament")
@NoArgsConstructor
@Getter
@Setter
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title; // campeonato abc 3a edicao...
    private String modality; // futebol, jogo especifico...
    @Column(name = "online_mode")
    private Boolean onlineMode; // outros usuarios poderao entrar
    @Column(name = "teams_quantity")
    private Integer teamsQuantity;
    @Column(name = "image_representation_url")
    private String imageRepresentationUrl;

    @ManyToOne
    @JoinColumn(name="format_id")
    private Format format; // formato suiço, pontos corridos...

    @OneToMany
    private List<Team> teams;

    @OneToMany
    private List<Player> players;

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", modality='" + modality + '\'' +
                ", onlineMode=" + onlineMode +
                ", teamsQuantity=" + teamsQuantity +
                ", imageRepresentationUrl='" + imageRepresentationUrl + '\'' +
                ", format=" + format +
                '}';
    }
}
