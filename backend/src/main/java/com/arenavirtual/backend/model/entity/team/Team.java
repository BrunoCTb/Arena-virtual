package com.arenavirtual.backend.model.entity.team;

import com.arenavirtual.backend.model.entity.player.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String logoUrl;

    @OneToOne
    private TeamStats stats;

    @OneToMany
    private Set<Player> players;



}
