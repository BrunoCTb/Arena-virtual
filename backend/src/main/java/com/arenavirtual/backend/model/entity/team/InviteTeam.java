package com.arenavirtual.backend.model.entity.team;

import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.model.inviteStatus.InviteStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InviteTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "invitedBy")
    private User invitedBy;

    @ManyToOne
    @JoinColumn(name = "invitedTarget")
    private User invitedTarget;

    @ManyToOne
    @JoinColumn(name = "teamTarget")
    private Team teamTarget;

    @Enumerated(EnumType.STRING)
    private InviteStatus inviteStatus;

    public InviteTeam(LocalDate createdAt, User invitedBy, User invitedTarget, Team teamTarget, InviteStatus inviteStatus) {
        this.createdAt = createdAt;
        this.invitedBy = invitedBy;
        this.invitedTarget = invitedTarget;
        this.teamTarget = teamTarget;
        this.inviteStatus = inviteStatus;
    }
}
