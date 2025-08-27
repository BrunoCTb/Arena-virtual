package com.arenavirtual.backend.model.entity.invite;

import com.arenavirtual.backend.model.inviteStatus.InviteStatus;
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
@MappedSuperclass
public class BaseInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private InviteStatus inviteStatus;

    public BaseInvite(LocalDate createdAt, InviteStatus inviteStatus) {
        this.createdAt = createdAt;
        this.inviteStatus = inviteStatus;
    }
}
