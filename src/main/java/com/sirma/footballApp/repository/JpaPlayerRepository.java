package com.sirma.footballApp.repository;
import com.sirma.footballApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPlayerRepository extends JpaRepository<Player, Long> {
}