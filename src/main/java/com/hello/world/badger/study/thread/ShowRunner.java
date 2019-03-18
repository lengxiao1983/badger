package com.hello.world.badger.study.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Data
public class ShowRunner extends IRunner {

    private LinkedBlockingQueue<Long> resultQueue;

    private ShowRunner(LinkedBlockingQueue<Long> resultQueue) {
        super();
        this.resultQueue = resultQueue;
    }

    public static ShowRunner newInstance(LinkedBlockingQueue<Long> resultQueue) {
        return new ShowRunner(resultQueue);
    }

    @Override
    public void process() throws Exception {
        while (true) {
            Long val = resultQueue.take();
            if (val != null) {
                System.out.println("current result is: " + val);
            }
        }
    }
}
