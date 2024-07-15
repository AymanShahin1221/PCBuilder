package com.app.service.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DelayUtilsTest {

    @BeforeEach
    void setUp() {
    }

    private LocalDateTime getResetTime() throws InterruptedException {

        LocalTime currentTime = LocalTime.now();
        LocalTime endTime = LocalTime.of(4, 0);

        LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), currentTime);
        LocalDateTime endDateTimeToday = LocalDateTime.of(LocalDate.now(), endTime);

        if (currentDateTime.isBefore(endDateTimeToday))
            return endDateTimeToday;

        else
        {
            LocalDateTime nextDayEndDateTime = LocalDateTime.of(LocalDate.now().plusDays(1), endTime);
            return nextDayEndDateTime;
        }
    }

    private LocalDateTime getResetTime(LocalDateTime mockCurrentTime) throws InterruptedException {

        LocalTime currentTime = LocalTime.from(mockCurrentTime);
        LocalTime endTime = LocalTime.of(4, 0);

        LocalDateTime endDateTimeToday = LocalDateTime.of(LocalDate.from(mockCurrentTime), endTime);

        if (currentTime.isBefore(LocalTime.from(endDateTimeToday)))
            return endDateTimeToday;

        else
        {
            LocalDateTime nextDayEndDateTime = LocalDateTime.of(LocalDate.from(mockCurrentTime.plusDays(1)), endTime);
            return nextDayEndDateTime;
        }
    }

    /**
     * The reset time must be synchronized to the nearest 4 AM
     */
    @Test
    void testResetTimeIfCalledNow() throws InterruptedException {

        LocalDateTime actualResetTime = getResetTime();
        LocalDateTime expectedResetTime = LocalDateTime.of(2024, Month.JULY, 13, 4, 0);

        assertEquals(expectedResetTime, actualResetTime);
    }

    private static List<Arguments> provideMockCurrentTimes() {
        return List.of(
                Arguments.of(LocalDateTime.of(2024, Month.JULY, 13, 2, 0), LocalDateTime.of(2024, Month.JULY, 13, 4, 0)),
                Arguments.of(LocalDateTime.of(2024, Month.JULY, 12, 14, 0), LocalDateTime.of(2024, Month.JULY, 13, 4, 0)),
                Arguments.of(LocalDateTime.of(2024, Month.JULY, 13, 14, 0), LocalDateTime.of(2024, Month.JULY, 14, 4, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("provideMockCurrentTimes")
    void testResetTimeWithMockCurrentTime(LocalDateTime mockCurrentTime, LocalDateTime expectedResetTime) throws InterruptedException {
        LocalDateTime actualResetTime = getResetTime(mockCurrentTime);
        assertEquals(expectedResetTime, actualResetTime);
    }
}