package com.sirma.footballApp.repository;
import com.sirma.footballApp.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaMatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m.id FROM Match m")
    List<Long> findAllMatchIds();
}