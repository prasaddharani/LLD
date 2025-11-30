package org.example.logging.formatter;

import org.example.logging.LogMessage;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class SimpleFormatter implements Formatter{
    private static final DateTimeFormatter dtf =
            DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.systemDefault());

    @Override
    public String format(LogMessage logMessage) {
        return String.format("%s [%s] %s - %s",
                dtf.format(Instant.ofEpochMilli(logMessage.getTimestamp())),
                logMessage.getLogLevel(),
                Thread.currentThread().getName(),
                logMessage.getMessage()
                );
    }
}
