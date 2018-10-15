package com.rongly.high.concurrency;

import com.rongly.high.concurrency.cache.CaffeineCacheService;
import com.rongly.high.concurrency.cache.DataObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RonglyHighConcurrencyApplicationTests {
	@Autowired
	private CaffeineCacheService caffeineCacheService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCache01() throws InterruptedException {
		caffeineCacheService.caffeineCache01();
	}

	@Test
	public void testCache02(){
		caffeineCacheService.caffeineCache02();
		DataObject dataObject = caffeineCacheService.caffeineCache02();
		System.out.println(dataObject);
	}

	@Test
	public void testCache03() throws InterruptedException {
		caffeineCacheService.caffeineCache03();
		TimeUnit.MINUTES.sleep(2);
		caffeineCacheService.caffeineCache03();
	}
}
