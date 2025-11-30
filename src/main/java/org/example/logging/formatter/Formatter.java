package org.example.logging.formatter;

import org.example.logging.LogMessage;

public interface Formatter {
    String format(LogMessage logMessage);
}
