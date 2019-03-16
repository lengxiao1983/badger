package com.hello.world.badger.study.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestMain {

    public static void main(String[] args) throws Exception {
        LinkedBlockingQueue<Long> fromQueue = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Long> toQueue = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Long> resultQueue = new LinkedBlockingQueue<>();

        SquareRunner.newInstance(fromQueue, toQueue).start();
        SquareRunner.newInstance(fromQueue, toQueue).start();
        PlusRunner.newInstance(toQueue, resultQueue).start();
        ShowRunner.newInstance(resultQueue).start();

        for (Long i=0L; i<100; i++) {
            TimeUnit.SECONDS.sleep(1);
            fromQueue.put(i);
        }
    }
}
