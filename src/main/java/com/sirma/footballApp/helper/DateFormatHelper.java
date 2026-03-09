package com.sirma.footballApp.helper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateFormatHelper {
    private static final List<String> DATE_FORMATS = Arrays.asList(
            "M/d/yyyy",
            "yyyy-MM-dd",
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "yyyy/MM/dd",
            "MMM dd, yyyy",
            "MMM.dd.yyyy",
            "dd.MMM.yyyy",
            "MMM.dd.yyyy",
            "yyyy.MMM.dd"
    );

    public static LocalDate dateHelper(String _dateString) {
        if (_dateString == null || _dateString.trim().isEmpty()) {
            return null;
        }

        String newDate = _dateString.trim();

        for (String format : DATE_FORMATS) {
            try {
                return LocalDate.parse(newDate, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {

            }
        }

        throw new IllegalArgumentException("Invalid date format: " + _dateString);
    }
}