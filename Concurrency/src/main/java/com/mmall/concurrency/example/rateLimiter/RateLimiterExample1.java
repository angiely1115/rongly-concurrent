package com.mmall.concurrency.example.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RateLimiterExample1 {

    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) throws Exception {

        for (int index = 0; index < 100; index++) {
            //判断能否在199毫秒内得到令牌，如果不能则立即返回false，不会阻塞程序
            if (rateLimiter.tryAcquire(199, TimeUnit.MILLISECONDS)) {
                handle(index);
            }
        }
    }

    private static void handle(int i) {
       log.info("{}", i);
    }
}
