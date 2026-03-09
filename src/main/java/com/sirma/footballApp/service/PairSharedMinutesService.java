package com.sirma.footballApp.service;
import com.sirma.footballApp.dto.MatchInfoDto;
import com.sirma.footballApp.dto.PairSharedMinutesDto;
import com.sirma.footballApp.model.Record;
import com.sirma.footballApp.repository.JpaMatchRepository;
import com.sirma.footballApp.repository.JpaRecordRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PairSharedMinutesService {

    private final JpaMatchRepository matchRepository;
    private final JpaRecordRepository recordRepository;

    public PairSharedMinutesService(JpaMatchRepository _matchRepository, JpaRecordRepository _recordRepository) {
        matchRepository = _matchRepository;
        recordRepository = _recordRepository;
    }
    public List<PairSharedMinutesDto> longestSharedMinutesPair() {
        List<Long> allMatchIds = matchRepository.findAllMatchIds();
        if (allMatchIds.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, Integer> pairTotalSharedMinutes = new HashMap<>();

        for (Long matchId : allMatchIds) {
            List<Record> playersInCurrentMatch = recordRepository.findByMatchId(matchId);

            for (int i = 0; i < playersInCurrentMatch.size(); i++) {
                for (int j = i + 1; j < playersInCurrentMatch.size(); j++) {
                    Record player1Record = playersInCurrentMatch.get(i);
                    Record player2Record = playersInCurrentMatch.get(j);
                    int sharedMinutesInCurrentMatch = overlapPairTime(player1Record, player2Record);

                    if (sharedMinutesInCurrentMatch > 0) {
                        String pairKey = getPairKey(player1Record.getPlayerId(), player2Record.getPlayerId());
                        pairTotalSharedMinutes.put(pairKey,
                                pairTotalSharedMinutes.getOrDefault(pairKey,
                                        0) + sharedMinutesInCurrentMatch);
                    }
                }
            }
        }

        if (pairTotalSharedMinutes.isEmpty()) {
            return Collections.emptyList();
        }

        int maxSharedMinutesFromAllPairs = Collections.max(pairTotalSharedMinutes.values());
        Set<String> winersPairKeys = new HashSet<>();

        for (Map.Entry<String, Integer> entry : pairTotalSharedMinutes.entrySet()) {
            if (entry.getValue() == maxSharedMinutesFromAllPairs) {
                winersPairKeys.add(entry.getKey());
            }
        }
        Map<String, List<MatchInfoDto>> winnersPairMatchDetails = new HashMap<>();
        for (String key : winersPairKeys) {
            winnersPairMatchDetails.put(key, new ArrayList<>());
        }
        for (Long matchId : allMatchIds) {
            List<Record> players = recordRepository.findByMatchId(matchId);
            for (int i = 0; i < players.size(); i++) {
                for (int j = i + 1; j < players.size(); j++) {
                    Record player1Record = players.get(i);
                    Record player2Record = players.get(j);
                    String pairKey = getPairKey(player1Record.getPlayerId(), player2Record.getPlayerId());

                    if (winersPairKeys.contains(pairKey)) {
                        int pairSharedTime = overlapPairTime(player1Record, player2Record);
                        if (pairSharedTime > 0) {
                            winnersPairMatchDetails.get(pairKey).add(new MatchInfoDto(matchId, pairSharedTime));
                        }
                    }
                }
            }
        }
        List<PairSharedMinutesDto> finalResults = new ArrayList<>();
        for (String pairKey : winersPairKeys) {
            String[] ids = pairKey.split("-");
            long pairOne = Long.parseLong(ids[0]);
            long pairTwo = Long.parseLong(ids[1]);
            finalResults.add(new PairSharedMinutesDto(pairOne, pairTwo, maxSharedMinutesFromAllPairs, winnersPairMatchDetails.get(pairKey)));
        }

        return finalResults;
    }

    private int overlapPairTime(Record recordOne, Record recordTwo) {
        int start = Math.max(recordOne.getFromMinutes(), recordTwo.getFromMinutes());
        int end1 = (recordOne.getToMinutes() == null) ? 90 : recordOne.getToMinutes();
        int end2 = (recordTwo.getToMinutes() == null) ? 90 : recordTwo.getToMinutes();
        int end = Math.min(end1, end2);
        return Math.max(0, end - start);
    }

    private String getPairKey(long pair1, long pair2) {
        return Math.min(pair1, pair2) + "-" + Math.max(pair1, pair2);
    }
}