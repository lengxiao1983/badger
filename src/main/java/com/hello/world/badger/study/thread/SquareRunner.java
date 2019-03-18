package com.hello.world.badger.study.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;


@Data
@Slf4j
public class SquareRunner extends IRunner {
    private LinkedBlockingQueue<Long> fromQueue;
    private LinkedBlockingQueue<Long> toQueue;

    private SquareRunner(LinkedBlockingQueue<Long> fromQueue,
                        LinkedBlockingQueue<Long> toQueue) {
        super();
        this.fromQueue = fromQueue;
        this.toQueue = toQueue;
    }

    public static SquareRunner newInstance(LinkedBlockingQueue<Long> fromQueue,
                                           LinkedBlockingQueue<Long> toQueue) {
        return new SquareRunner(fromQueue, toQueue);
    }

    @Override
    public void process() throws Exception {
        while (true) {
            Long val = fromQueue.take();
            if (val != null) {
                toQueue.put(val * val);
            }
        }
    }
}
