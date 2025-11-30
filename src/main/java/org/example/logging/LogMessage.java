package org.example.logging;

import org.example.logging.enums.LogLevel;

import java.util.Collections;
import java.util.Map;

public class LogMessage {
    private final LogLevel logLevel;
    private final String message;
    private final long timestamp;
    private final Map<String, String> metaData;


    public LogMessage(LogLevel logLevel, String message, long timestamp, Map<String, String> metaData) {
        this.logLevel = logLevel;
        this.message = message;
        this.timestamp = timestamp;
        this.metaData = metaData == null? Collections.emptyMap(): Map.copyOf(metaData);
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }
}
