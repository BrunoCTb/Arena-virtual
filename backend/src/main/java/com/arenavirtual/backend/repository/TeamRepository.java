package com.arenavirtual.backend.repository;

import com.arenavirtual.backend.model.entity.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {

    boolean existsByName(String name);
}
