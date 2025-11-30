package org.example.logging.handler;


import org.example.logging.enums.LogLevel;
import org.example.logging.formatter.Formatter;

public class ConsoleHandler extends LogHandler {
    public ConsoleHandler(LogLevel threshold, Formatter formatter) {
        super(threshold, formatter);
    }

    @Override
    public void write(String formatted) {
        System.out.println(formatted);
    }
}
