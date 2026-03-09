package com.sirma.footballApp.service;
import com.sirma.footballApp.model.Match;
import com.sirma.footballApp.repository.JpaMatchRepository;
import com.sirma.footballApp.helper.DateFormatHelper;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    private final JpaMatchRepository jpaMatchRepository;
    public MatchService(JpaMatchRepository _jpaMatchRepository) {
        jpaMatchRepository = _jpaMatchRepository;
    }

    public void readMatches() throws Exception {
        File filePath = new File("src/main/resources/matches.csv");
        List<Match> matchesList = new ArrayList<>();

        try (BufferedReader myReader = new BufferedReader(new FileReader(filePath))) {
            myReader.readLine();
            String line;
            while ((line = myReader.readLine()) != null) {
                if (line.trim().isEmpty()) {

                    continue;
                }

                String[] data = line.split(",");
                Match match = new Match();
                match.setId(Long.parseLong(data[0].trim()));
                match.setATeamId(Long.parseLong(data[1].trim()));
                match.setBTeamId(Long.parseLong(data[2].trim()));
                match.setDate(DateFormatHelper.dateHelper(data[3].trim()));
                match.setScore(data[4].trim());
                matchesList.add(match);
            }
        }

        jpaMatchRepository.saveAll(matchesList);
    }
}