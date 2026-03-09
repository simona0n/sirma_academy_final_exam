package com.sirma.footballApp.service;
import com.sirma.footballApp.model.Player;
import com.sirma.footballApp.repository.JpaPlayerRepository;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final JpaPlayerRepository jpaPlayerRepository;
    public PlayerService(JpaPlayerRepository _jpaPlayerRepository) {
       jpaPlayerRepository = _jpaPlayerRepository;
    }

    public void readPlayers() throws Exception {
        File filePath = new File("src/main/resources/players.csv");
        List<Player> playersList = new ArrayList<>();

        try (BufferedReader myReader = new BufferedReader(new FileReader(filePath))) {
            myReader.readLine();
            String line;
            while ((line = myReader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                Player player = new Player();
                player.setId(Long.parseLong(data[0].trim()));
                player.setTeamNumber(Integer.parseInt(data[1].trim()));
                player.setPosition(data[2].trim());
                player.setFullName(data[3].trim());
                player.setTeamId(Long.parseLong(data[4].trim()));
                playersList.add(player);
            }
        }
        jpaPlayerRepository.saveAll(playersList);
    }
}