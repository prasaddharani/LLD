package org.example.logging.handler;

import org.example.logging.enums.LogLevel;
import org.example.logging.formatter.Formatter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler extends LogHandler implements AutoCloseable{
    private final BufferedWriter writer;
    public FileHandler(LogLevel threshold, Formatter formatter, Path file) throws IOException {
        super(threshold, formatter);
        Files.createDirectories(file.getParent());
        this.writer = Files.newBufferedWriter(
                file,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    @Override
    public void write(String formatted) throws Exception {
        writer.write(formatted);
        writer.newLine();
        writer.flush();
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
