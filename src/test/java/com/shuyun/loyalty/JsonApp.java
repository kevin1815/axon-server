package com.shuyun.loyalty;

import com.shuyun.loyalty.util.BlockCallerThreadHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JsonApp {

    static ExecutorService executorService = new ThreadPoolExecutor(4, 4, 0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(10), new BlockCallerThreadHandler());

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10000; i++) {
            System.out.println("send value " + i);
            executorService.submit(new Task(i));
        }
    }



    static class Task implements Runnable {

        private final int value;

        Task(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println(value);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
