package com.arenavirtual.backend.model.entity.player;

import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.model.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long publicId;

    private String username; // apenas um nome local que pode ser modificado que será acompanhado do
    private String imageUrl;


    @ManyToOne
    private Team team;

    @OneToOne
    private User user;

    @OneToMany
    private List<Tournament> tournaments;


}
