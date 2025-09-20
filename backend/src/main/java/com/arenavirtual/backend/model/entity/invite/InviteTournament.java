package com.arenavirtual.backend.model.entity.invite;

import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.model.inviteType.TournamentInviteType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
 import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class InviteTournament extends BaseInvite{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // seta quem envia, caso for 'A', o 'B' que recebe o convite
    @Column(name = "sender_type")
    @Enumerated(EnumType.STRING)
    private TournamentInviteType senderType;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Tournament tournament;


}
