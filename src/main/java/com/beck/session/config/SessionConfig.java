package com.beck.session.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60)
public class SessionConfig {
  @Value("${redis.hostname:localhost}")
  private String redisHostName;
  @Value("${redis.port:6379}")
  private int port;

  @Bean
  public RedisConnectionFactory jedisConnectionFactory(){
    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setHostName(redisHostName);
    connectionFactory.setPort(port);
    return connectionFactory;
  }
}
