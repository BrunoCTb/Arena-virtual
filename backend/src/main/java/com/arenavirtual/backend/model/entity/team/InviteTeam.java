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
    private User invitedBy;

    @ManyToOne
    private User invitedTarget;

    @ManyToOne
    private Team teamTarget;

    @Enumerated(EnumType.STRING)
    private InviteStatus inviteStatus;


}
