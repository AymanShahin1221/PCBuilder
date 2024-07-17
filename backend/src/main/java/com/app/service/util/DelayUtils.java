package com.app.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DelayUtils {

    private static final Logger logger = LoggerFactory.getLogger(DelayUtils.class);

    private DelayUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Delays retrieval of images until time reset of API
     * If delay occurs before time of reset, delay will occur until that time
     * If not, delay will occur until the next day, same time
     */
    public static void delay(LocalTime resetTime) throws InterruptedException {

        logger.info("Delaying...");

        LocalTime currentTime = LocalTime.now();

        LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), currentTime);
        LocalDateTime endDateTimeToday = LocalDateTime.of(LocalDate.now(), resetTime);

        if (currentDateTime.isBefore(endDateTimeToday))
        {
            Duration duration = Duration.between(currentDateTime, endDateTimeToday);
            long delayDuration = duration.toMillis();

            Thread.sleep(delayDuration);
        }
        else
        {
            LocalDateTime nextDayEndDateTime = LocalDateTime.of(LocalDate.now().plusDays(1), resetTime);

            Duration duration = Duration.between(currentDateTime, nextDayEndDateTime);
            long delayDuration = duration.toMillis();

            System.out.println(delayDuration);

            Thread.sleep(delayDuration);
        }
        logger.info("Resuming...");
    }
}
