package com.md.manage.service.impl;

import com.md.manage.service.RedisService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public void set(String key, Object value) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value,2,TimeUnit.HOURS);
    }
    public Object get(String key) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

//    public void setHash(String key,Object value){
//        HashOperations<String,Object,Object> vo = redisTemplate.opsForHash();
//    }

}
