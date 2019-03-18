package com.hello.world.badger.study.thread;

import lombok.Data;

import java.util.concurrent.LinkedBlockingQueue;

@Data
public class Badger {
    private static final LinkedBlockingQueue<Long> fromQueue = new LinkedBlockingQueue<>();
    private static final LinkedBlockingQueue<Long> toQueue = new LinkedBlockingQueue<>();
    private static final LinkedBlockingQueue<Long> resultQueue = new LinkedBlockingQueue<>();

    private static final Badger instance = new Badger();

    private Badger() {
        SquareRunner.newInstance(fromQueue, toQueue).start();
        SquareRunner.newInstance(fromQueue, toQueue).start();
        PlusRunner.newInstance(toQueue, resultQueue).start();
        ShowRunner.newInstance(resultQueue).start();
    }

    public static void compute(Long val) throws Exception {
        fromQueue.put(val);
    }
}
