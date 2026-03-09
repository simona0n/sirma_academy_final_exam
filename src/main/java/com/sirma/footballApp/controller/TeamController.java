package com.sirma.footballApp.controller;
import com.sirma.footballApp.model.Team;
import com.sirma.footballApp.repository.JpaTeamRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final JpaTeamRepository teamRepository;
    public TeamController(JpaTeamRepository _teamRepository) {
       teamRepository = _teamRepository;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id).orElse(null);
    }
}
