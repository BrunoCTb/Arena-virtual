package com.arenavirtual.backend.repository;

import com.arenavirtual.backend.model.entity.invite.InviteTournament;
import com.arenavirtual.backend.model.inviteType.TournamentInviteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TournamentInviteRepository extends JpaRepository<InviteTournament, UUID> {

    boolean existsByTournamentIdAndTeamIdAndSenderType(UUID senderId, UUID receiverId, TournamentInviteType type);
}
