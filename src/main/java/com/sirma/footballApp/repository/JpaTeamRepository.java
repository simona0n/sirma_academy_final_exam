package com.sirma.footballApp.repository;
import com.sirma.footballApp.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTeamRepository extends JpaRepository<Team, Long> {
}