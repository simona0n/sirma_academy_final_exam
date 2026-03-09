package com.sirma.footballApp.service;
import com.sirma.footballApp.model.Team;
import com.sirma.footballApp.repository.JpaTeamRepository;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final JpaTeamRepository jpaTeamRepository;

    public TeamService(JpaTeamRepository _jpaTeamRepository) {
        jpaTeamRepository = _jpaTeamRepository;
    }
    public void readTeams() throws Exception {
        File filePath = new File("src/main/resources/teams.csv");
        List<Team> teamsList = new ArrayList<>();
        try (BufferedReader myReader = new BufferedReader(new FileReader(filePath))) {
            myReader.readLine();
            String line;
            while ((line = myReader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                Team team = new Team();
                team.setId(Long.parseLong(data[0].trim()));
                team.setName(data[1].trim());
                team.setManagerFullName(data[2].trim());

                if (data.length > 3) {
                    team.setGroupLetter(data[3].trim());
                }
                teamsList.add(team);
            }
        }

        jpaTeamRepository.saveAll(teamsList);
    }
}