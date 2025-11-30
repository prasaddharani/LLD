package org.example.logging.formatter;

import org.example.logging.LogMessage;

import java.util.Map;
import java.util.StringJoiner;

public class JsonFormatter implements Formatter{
    @Override
    public String format(LogMessage msg) {

        StringJoiner meta = new StringJoiner(", ", "\"metadata\":{", "}");
        for (Map.Entry<String, String> e : msg.getMetaData().entrySet()) {
            meta.add("\"" + esc(e.getKey()) + "\":\"" + esc(e.getValue()) + "\"");
        }

        return "{"
                + "\"timestamp\":" + msg.getTimestamp() + ","
                + "\"level\":\"" + msg.getLogLevel() + "\","
                + "\"thread\":\"" + esc(Thread.currentThread().getName()) + "\","
                + "\"message\":\"" + esc(msg.getMessage()) + "\","
                + meta
                + "}";
    }

    private String esc(String s) { return s.replace("\"", "\\\""); }
}
