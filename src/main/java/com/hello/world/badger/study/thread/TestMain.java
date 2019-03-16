package com.hello.world.badger.study.thread;

import java.util.concurrent.TimeUnit;

public class TestMain {

    public static void main(String[] args) throws Exception {
        for (Long i=0L; i<100; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("put " + i + " in fromQueue");
            Badger.compute(i);
        }
    }
}
