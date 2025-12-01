package org.example.logging.handler;

import org.example.logging.Logger;
import org.example.logging.enums.LogLevel;
import org.example.logging.formatter.Formatter;
import org.example.logging.formatter.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.setGlobalLevel(LogLevel.INFO);

        Formatter simpleFormatter = new SimpleFormatter();

        LogHandler head = new ConsoleHandler(LogLevel.INFO, simpleFormatter);
        logger.setHandlerChain(head);

        logger.debug("Debug message (may be filtered)");
        logger.info("Service started successfully");

        logger.error("Something went wrong!");

        System.out.println("Dropped logs = " + logger.droppedMessages());
    }
}
