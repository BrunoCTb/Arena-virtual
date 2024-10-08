package com.arenavirtual.backend.repository;

import com.arenavirtual.backend.model.entity.team.TeamStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamStatsRepository extends JpaRepository<TeamStats, UUID> {
}
