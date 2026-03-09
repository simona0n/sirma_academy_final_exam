package com.sirma.footballApp.runner;
import com.sirma.footballApp.service.MatchService;
import com.sirma.footballApp.service.PlayerService;
import com.sirma.footballApp.service.RecordService;
import com.sirma.footballApp.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private final TeamService teamService;
    private final PlayerService playerService;
    private final MatchService matchService;
    private final RecordService recordService;

    public DataInitializer(TeamService _teamService, PlayerService _playerService,
                           MatchService _matchService, RecordService _recordService) {
        teamService = _teamService;
        playerService = _playerService;
        matchService = _matchService;
        recordService = _recordService;
    }

    @Override
    public void run(String... args) {
        try {
            logger.info("...........................");
            teamService.readTeams();
            logger.info("Teams - successfully :) .");
            playerService.readPlayers();
            logger.info("Players- successfully :) ");
            matchService.readMatches();
            logger.info("Matches- successfully :) ");
            recordService.readRecords();
            logger.info("Records - successfully :) ");
            logger.info("Good job :)))");
        } catch (Exception e) {
            logger.error("errror {}", e.getMessage(), e);
        }
    }
}