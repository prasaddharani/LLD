package org.example.logging;

import org.example.logging.enums.LogLevel;
import org.example.logging.handler.LogHandler;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Logger {
    private static final Logger INSTANCE = new Logger();
    private volatile LogHandler head;
    private volatile LogLevel globalLevel = LogLevel.DEBUG;

    public void setGlobalLevel(LogLevel level) {
        this.globalLevel = level;
    }

    private final ExecutorService worker;
    private final BlockingQueue<LogMessage> queue =
            new ArrayBlockingQueue<>(5000);

    private final AtomicLong dropped = new AtomicLong(0);

    private Logger() {
        worker = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r, "Logger-Worker");
            t.setDaemon(true);
            return t;
        });
        worker.submit(this::drainLoop);
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void setHandlerChain(LogHandler head) {
        this.head = head;
    }

    private boolean enabled(LogLevel level) {
        return level.ordinal() >= globalLevel.ordinal();
    }

    public void log(LogLevel level, String msg) {
        log(level, msg, null);
    }

    public void log(LogLevel level, String msg, Map<String, String> meta) {
        if (!enabled(level)) return;

        LogMessage lm = new LogMessage(level, msg, meta);
        if (!queue.offer(lm)) dropped.incrementAndGet();
    }

    public void info(String msg) { log(LogLevel.INFO, msg); }
    public void debug(String msg) { log(LogLevel.DEBUG, msg); }
    public void warn(String msg) { log(LogLevel.WARN, msg); }
    public void error(String msg) { log(LogLevel.ERROR, msg); }

    private void drainLoop() {
        try {
            while (true) {
                LogMessage message = queue.take();
                if (head != null) {
                    head.handle(message);
                }
            }
        } catch (InterruptedException e) {

        }
    }

    public long droppedMessages() { return dropped.get(); }

}
