package com.sirma.footballApp.controller;
import com.sirma.footballApp.model.Player;
import com.sirma.footballApp.repository.JpaPlayerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final JpaPlayerRepository playerRepository;

    public PlayerController(JpaPlayerRepository _playerRepository) {
        this.playerRepository = _playerRepository;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id).orElse(null);
    }
}