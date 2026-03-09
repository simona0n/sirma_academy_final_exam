package com.sirma.footballApp.controller;
import com.sirma.footballApp.model.Record;
import com.sirma.footballApp.repository.JpaRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final JpaRecordRepository recordRepository;
    public RecordController(JpaRecordRepository _recordRepository) {
        recordRepository = _recordRepository;
    }

    @GetMapping
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @GetMapping("/{id}")
    public Record getRecordById(@PathVariable Long id) {
        return recordRepository.findById(id).orElse(null);
    }
}