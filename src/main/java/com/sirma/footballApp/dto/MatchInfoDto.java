package com.sirma.footballApp.dto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@JsonPropertyOrder({ "matchId", "pairSharedMinutesInThisMatch" })
public class MatchInfoDto {
    private long matchId;
    private int pairSharedMinutesInThisMatch;

    public MatchInfoDto(long _matchId, int _pairSharedMinutesInThisMatch) {
        matchId = _matchId;
        pairSharedMinutesInThisMatch = _pairSharedMinutesInThisMatch;
    }

    public MatchInfoDto(Long matchId, int sharedTime) {
        this.matchId= matchId;
        this.pairSharedMinutesInThisMatch = sharedTime;
    }

}