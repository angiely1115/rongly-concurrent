package com.rongly.high.concurrency.test;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: lvrongzhuan
 * @Description: 计数测试
 * @Date: 2018/10/12 17:34
 * @Version: 1.0
 * modified by:
 */
public class CountExample1 {


    private static final int treadTotal = 100;//每次同时允许100个线程

    private static final int clientTotal =  1000;//总共1000个请求
    static ExecutorService pool = Executors.newFixedThreadPool(30);
    static Semaphore semaphore = new Semaphore(treadTotal);
    static CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
    static int count;
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    static LongAdder longAdder = new LongAdder();
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<clientTotal;i++){
            int finalI = i;
            pool.submit(()->{
//                System.out.println("1:"+Thread.currentThread().getName());
                try {

//                    System.out.println("****count i:"+ finalI);
                    semaphore.acquire();
                    count++;
                    atomicInteger.incrementAndGet();
                    longAdder.increment();
//                    System.out.println("count**********:"+count);
                    TimeUnit.MILLISECONDS.sleep(300);
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        System.out.println("2***************:"+Thread.currentThread().getName());
        System.out.println("count :"+count);
        System.out.println("atomicInteger count :"+atomicInteger.get());
        System.out.println("longAdder count :"+longAdder.longValue());
        System.out.println("longAdder sum :"+longAdder.sum());
    }
    @Test
    public void test1() throws InterruptedException {
        for(int i=0;i<clientTotal;i++){
            pool.submit(()->{
                try {
                    semaphore.acquire();
                    count++;
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        System.out.println("count :"+count);
    }
}
