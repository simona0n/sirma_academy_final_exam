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
@Table(name = "teams")
@JsonPropertyOrder({ "id", "name", "managerFullName", "groupLetter" })
public class Team {
    @Id
    private Long id;
    private String name;
    private String managerFullName;
    private String groupLetter;

    public Team() {
    }

}
