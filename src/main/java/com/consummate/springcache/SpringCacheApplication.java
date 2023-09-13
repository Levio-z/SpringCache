package com.consummate.springcache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringCacheApplication implements ApplicationListener<ApplicationReadyEvent> {

    private final RedisTemplate redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("应用程序已准备就绪");
        redisTemplate.opsForValue().set("key","1");
        stringRedisTemplate.opsForValue().set("key","value");
        System.out.println("redis正常访问：key：key,value:"+redisTemplate.opsForValue().get("key"));
        System.out.println("redis正常访问：key：key,value:"+stringRedisTemplate.opsForValue().get("key"));
    }
}
