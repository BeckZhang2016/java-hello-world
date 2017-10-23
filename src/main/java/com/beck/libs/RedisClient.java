package com.beck.libs;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class RedisClient {

  private JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

  public JedisConnectionFactory getJedisConnectionFactory() {
    return jedisConnectionFactory;
  }

  public void setEx(String key, String value, int expire){
    RedisConnection connection = jedisConnectionFactory.getConnection();
    connection.setEx(key.getBytes(), expire, value.getBytes());
    connection.close();
  }

}
