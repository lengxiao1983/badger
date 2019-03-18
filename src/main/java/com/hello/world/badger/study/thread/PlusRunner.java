package com.hello.world.badger.study.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Data
public class PlusRunner extends IRunner {
    private LinkedBlockingQueue<Long> toQueue;
    private LinkedBlockingQueue<Long> resultQueue;
    private Long sum = 0L;

    private PlusRunner(LinkedBlockingQueue<Long> toQueue,
                      LinkedBlockingQueue<Long> resultQueue) {
        super();
        this.toQueue = toQueue;
        this.resultQueue = resultQueue;
    }

    public static PlusRunner newInstance(LinkedBlockingQueue<Long> toQueue,
                                         LinkedBlockingQueue<Long> resultQueue) {
        return new PlusRunner(toQueue, resultQueue);
    }

    @Override
    public void process() throws Exception {
        while (true) {
            Long val = toQueue.take();
            if (val != null) {
                sum += val;
                resultQueue.put(sum);
            }
        }
    }
}
