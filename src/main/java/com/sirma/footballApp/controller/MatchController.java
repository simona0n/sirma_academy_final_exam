package com.sirma.footballApp.controller;
import com.sirma.footballApp.model.Match;
import com.sirma.footballApp.repository.JpaMatchRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final JpaMatchRepository matchRepository;

    public MatchController(JpaMatchRepository _matchRepository) {
        matchRepository = _matchRepository;
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id).orElse(null);
    }
}