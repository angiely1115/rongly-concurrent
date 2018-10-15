package com.rongly.high.concurrency.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/14 12:08
 * @Version: 1.0
 * modified by:
 */
@Configuration
@EnableCaching
public class LocalCacheConfig {
    public static final int DEFAULT_MAXSIZE = 50000;
    public static final int DEFAULT_TTL = 10;
    public enum Cacehs{
        getSomething, //缺省10秒
        getOtherthing(300, 1000), //5分钟，最大容量1000
        get_test_config(10, 10), //5分钟，最大容量1000
        ;
        private int ttl = DEFAULT_TTL;//过期时间
        private int maxSize = DEFAULT_MAXSIZE;//最大容量

        Cacehs() {
        }

        Cacehs(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }
    }

    @Bean
    public CaffeineCacheManager caffeineCacheManager(){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        Caffeine caffeine = Caffeine.newBuilder();
       Cache cache = caffeine
                .expireAfterWrite(1, TimeUnit.MINUTES)
               //初始化容量
               .initialCapacity(10)
               //最大容量
                .maximumSize(100)
                .build();
        caffeineCacheManager.setCaffeine(caffeine);
        caffeineCacheManager.setCacheNames(getNames());
        return caffeineCacheManager;
    }
    private static List<String> getNames(){
        List<String> names = new ArrayList<>(2);
        names.add("outLimit");
        names.add("notOutLimit");
        return names;
    }
    public static void main(String[] args) {
        for(Cacehs c : Cacehs.values()) {
            System.out.println(c.name());
            System.out.println(c.maxSize);
        }
        }
    @Bean
    @Primary
    public CacheManager caffeineCacheManager2() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches = new ArrayList<CaffeineCache>();
        for(Cacehs c : Cacehs.values()){
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterWrite(c.ttl, TimeUnit.SECONDS)
                            .maximumSize(c.maxSize)
                            .build())
            );
        }
        cacheManager.setCaches(caches);

        return cacheManager;
    }
}