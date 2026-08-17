package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DateLabelTest {
    @Test
    void 年末日を暦年で表示する() {
        String actual = new DateLabel().label(LocalDate.of(2021, 12, 31));
        System.out.println("[evidence] label=" + actual);
        assertEquals("2021-12-31", actual);
    }
}
