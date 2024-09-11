package com.arenavirtual.backend.model.entity.team;

import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.model.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private LocalDate createdAt;
    private Boolean openToInvite; // players podem solicitar para entrarem
    private String logoUrl;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @OneToOne
    private TeamStats teamStats;

}
