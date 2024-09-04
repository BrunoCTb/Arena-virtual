package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.dto.TeamDto;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.repository.TeamRepository;
import com.arenavirtual.backend.service.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/create")
    public ResponseEntity<String> createTeam(@RequestBody TeamDto dto) {
        if (teamService.existsByName(dto.name())) {
            return ResponseEntity.badRequest().body("Nome do time já cadastrado!");
        }

        // add
        Team team = new Team();
        BeanUtils.copyProperties(dto, team);

        teamService.save(team);

        return ResponseEntity.ok("Time criado com sucesso!");
    }


}
