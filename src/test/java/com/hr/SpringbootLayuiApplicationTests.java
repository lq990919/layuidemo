package com.hr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootLayuiApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        String a ="asd";
        StringBuffer b = new StringBuffer("asd");
        System.out.println(a.equals(b));
    }

    @Test
    void redisDemo() {

        Object o = redisTemplate.opsForValue().get("account:a");
        Object o1 = redisTemplate.opsForValue().get("account:b");

        System.out.println(o);
        System.out.println(o1);
    }

}
