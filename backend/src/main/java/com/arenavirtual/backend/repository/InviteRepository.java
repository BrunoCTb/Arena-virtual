package com.arenavirtual.backend.repository;

import com.arenavirtual.backend.model.entity.team.InviteTeam;
import com.arenavirtual.backend.model.entity.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InviteRepository extends JpaRepository<InviteTeam, UUID> {

    List<InviteTeam> findByTeamTarget(Team team);

}
