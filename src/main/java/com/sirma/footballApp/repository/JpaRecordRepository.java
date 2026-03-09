package com.sirma.footballApp.repository;
import com.sirma.footballApp.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaRecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByMatchId(Long matchId);
}