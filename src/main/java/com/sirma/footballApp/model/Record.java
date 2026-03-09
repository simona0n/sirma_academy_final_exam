package com.sirma.footballApp.model;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "records")
@JsonPropertyOrder({ "id", "playerId", "matchId", "fromMinutes", "toMinutes" })

public class Record {
    @Id
    private Long id;
    private Long playerId;
    private Long matchId;
    private Integer fromMinutes;
    private Integer toMinutes;

    public Record() {
    }
}