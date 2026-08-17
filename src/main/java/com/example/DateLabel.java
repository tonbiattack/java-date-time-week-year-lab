package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateLabel {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd", Locale.US);

    public String label(LocalDate date) {
        return formatter.format(date);
    }
}
