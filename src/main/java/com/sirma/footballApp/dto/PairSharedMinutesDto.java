package com.sirma.footballApp.dto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Setter
@Getter
@JsonPropertyOrder({ "player1Id", "player2Id", "totalSharedMinutes", "matches" })
public class PairSharedMinutesDto {
    private long player1Id;
    private long player2Id;
    private int totalSharedMinutes;
    private List<MatchInfoDto> matches;

    public PairSharedMinutesDto(long _player1Id, long _player2Id,
                                int _totalSharedMinutes, List<MatchInfoDto> _matches) {
        player1Id = _player1Id;
        player2Id = _player2Id;
        totalSharedMinutes = _totalSharedMinutes;
        matches = _matches;
    }

}