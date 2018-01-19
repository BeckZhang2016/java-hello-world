package com.beck.libs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisClient {

    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    public void setEx(String key, String value, int expire) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            connection.setEx(key.getBytes(), expire, value.getBytes());
        } catch (Exception e) {
            if (connection != null) {
                connection.close();
            }
            connection = redisConnectionFactory.getConnection();
            connection.setEx(key.getBytes(), expire, value.getBytes());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

}
