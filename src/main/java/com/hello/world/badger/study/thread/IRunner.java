package com.hello.world.badger.study.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

@Slf4j
public abstract class IRunner implements Runnable {
    private ExecutorService executor;
    private AtomicBoolean lock = new AtomicBoolean(false);

    public IRunner() {

    }

    public void start() {
        if (lock.compareAndSet(false, true)) {
            executor = newSingleThreadExecutor();
            executor.submit(this);
        }
    }

    public abstract void process() throws Exception;

    @Override
    public void run() {
        try {
            process();
        } catch (Throwable t) {
            log.error(t.getMessage(), t);
        } finally {
            executor.submit(this);
        }

    }
}
