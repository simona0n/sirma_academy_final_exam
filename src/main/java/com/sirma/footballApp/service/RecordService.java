package com.sirma.footballApp.service;
import com.sirma.footballApp.model.Record;
import com.sirma.footballApp.repository.JpaRecordRepository;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {

    private final JpaRecordRepository jpaRecordRepository;
    public RecordService(JpaRecordRepository _jpaRecordRepository) {
        this.jpaRecordRepository = _jpaRecordRepository;
    }

    public void readRecords() throws Exception {
        File filePath = new File("src/main/resources/records.csv");
        List<Record> recordsList = new ArrayList<>();

        try (BufferedReader myReader = new BufferedReader(new FileReader(filePath))) {
            myReader.readLine();
            String line;
            while ((line = myReader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                Record record = convertToRecord(data);
                recordsList.add(record);
            }
        }

        jpaRecordRepository.saveAll(recordsList);
    }

    private static Record convertToRecord(String[] data) {
        Record record = new Record();
        record.setId(Long.parseLong(data[0].trim()));
        record.setPlayerId(Long.parseLong(data[1].trim()));
        record.setMatchId(Long.parseLong(data[2].trim()));
        record.setFromMinutes(Integer.parseInt(data[3].trim()));
        String toMinutesString = data[4].trim();

        if (toMinutesString.equalsIgnoreCase("NULL")) {
            record.setToMinutes(null);
        } else {
            record.setToMinutes(Integer.parseInt(toMinutesString));
        }
        return record;
    }
}