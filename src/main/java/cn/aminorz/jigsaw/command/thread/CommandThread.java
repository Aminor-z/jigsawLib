package cn.aminorz.jigsaw.command.thread;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class CommandThread {
    private static final ExecutorService CommandThread = newSingleThreadExecutor();

    public static void submit(Runnable runnable) {
        CommandThread.submit(runnable);
    }
}
