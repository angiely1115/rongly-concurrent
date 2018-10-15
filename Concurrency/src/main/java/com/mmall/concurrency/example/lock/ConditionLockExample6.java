package com.mmall.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionLockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            reentrantLock.lock();
            try {

                log.info("wait signal"); // 1
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
            log.info("reentrantLock.unlock()"+Thread.currentThread().getName());
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock"); // 2
            try {
                condition.await();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal ~ "); // 3
            reentrantLock.unlock();
            log.info("reentrantLock.unlock2()"+Thread.currentThread().getName());
        }).start();


        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock3"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send3 signal ~ "); // 3
            reentrantLock.unlock();
            log.info("reentrantLock.unlock3()"+Thread.currentThread().getName());
        }).start();
    }
}
