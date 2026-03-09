package com.sirma.footballApp.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "players")
@JsonPropertyOrder({ "id", "teamNumber", "position", "fullName", "teamId" })
public class Player {

    @Id
    private Long id;
    private Integer teamNumber;
    private String position;
    private String fullName;
    private Long teamId;

    public Player() {
    }

}

