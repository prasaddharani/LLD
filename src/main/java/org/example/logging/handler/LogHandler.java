package org.example.logging.handler;

import org.example.logging.LogMessage;
import org.example.logging.enums.LogLevel;
import org.example.logging.formatter.Formatter;

public abstract class LogHandler {
    protected final LogLevel threshold;
    protected final Formatter formatter;
    protected LogHandler next;


    protected LogHandler(LogLevel threshold, Formatter formatter) {
        this.threshold = threshold;
        this.formatter = formatter;
    }

    public void setNext(LogHandler next) {
        this.next = next;
    }

    public void handle(LogMessage msg) {
        try {
            if (msg.getLogLevel().ordinal() >= threshold.ordinal()) {
                write(formatter.format(msg));
            }
        } catch (Exception e) {
            System.out.println("Handler failed: " + e.getMessage());
        }
        if (next != null) {
            next.handle(msg);
        }
    }

    public abstract void write(String formatted) throws Exception;
}
