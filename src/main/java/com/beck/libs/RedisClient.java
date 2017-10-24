package com.beck.libs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisClient {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  public void setEx(String key, String value, int expire) {
    stringRedisTemplate.opsForValue().set(key, value);
  }

}
