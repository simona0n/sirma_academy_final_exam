package com.sirma.footballApp.model;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "matches")
@JsonPropertyOrder({ "id", "aTeamId", "bTeamId", "date", "score" })
public class Match {
    @Id
    private Long id;
    private Long aTeamId;
    private Long bTeamId;
    private LocalDate date;
    private String score;

    public Match() {
    }
}

