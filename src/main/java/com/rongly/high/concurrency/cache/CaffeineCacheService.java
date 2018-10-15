package com.rongly.high.concurrency.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/14 12:50
 * @Version: 1.0
 * modified by:
 */
@Service
public class CaffeineCacheService {
    @Autowired
    private CaffeineCacheManager caffeineCacheManager;
    @Autowired
    private CacheManager caffeineCacheManager2;

    public String caffeineCache01() throws InterruptedException {
        //根据名称获取缓存
        CaffeineCache caffeineCache = (CaffeineCache) caffeineCacheManager.getCache("notOutLimit");
        caffeineCache.put("key","value");
        String val = caffeineCache.get("key",String.class);
        System.out.println("第一次获取："+val);
        TimeUnit.MINUTES.sleep(2);
        //缓存过期后获取为null
        val = caffeineCache.get("key",String.class);
        System.out.println("第二次获取："+val);
        return val;
    }

    public DataObject caffeineCache02(){
        CaffeineCache caffeineCache = (CaffeineCache) caffeineCacheManager.getCache("notOutLimit");
        DataObject dataObject = caffeineCache.get("key1",DataObject.class);
        if(dataObject==null){
             dataObject = new DataObject();
            dataObject.setName("吕荣砖");
            dataObject.setZonedDateTime(ZonedDateTime.now());
            caffeineCache.put("key1",dataObject);
            System.out.println("从数据库获取,并加入缓存");
        }else {
            System.out.println("从缓存中获取");
        }
        return dataObject;
    }

    public void caffeineCache03(){
       CaffeineCache caffeineCache = (CaffeineCache) caffeineCacheManager2.getCache("get_test_config");
        caffeineCache.put("key","val");
        caffeineCache.put("key","val2");
        System.out.println("name:"+caffeineCache.getName());
        System.out.println("获取Key值："+caffeineCache.get("key",String.class));
    }
}
