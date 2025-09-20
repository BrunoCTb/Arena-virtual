package com.arenavirtual.backend.repository;

import com.arenavirtual.backend.model.entity.invite.InviteTournament;
import com.arenavirtual.backend.model.inviteType.TournamentInviteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TournamentInviteRepository extends JpaRepository<InviteTournament, UUID> {

    boolean existsByTournamentIdAndTeamIdAndSenderType(UUID senderId, UUID receiverId, TournamentInviteType type);

    @Query("""
        SELECT it FROM InviteTournament it
        WHERE it.team.createdBy.id = :userId
           OR it.tournament.createdBy.id = :userId
    """)
    List<InviteTournament> findAllByUser(@Param("userId") UUID userId);

}
