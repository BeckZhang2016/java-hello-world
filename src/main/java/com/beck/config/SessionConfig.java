package com.beck.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
public class SessionConfig {
  /*  @Value("${spring.redis.host}")
    private String redisHostName;
    @Value("${spring.redis.port}")
    private int port;*/
  private String host;
  private int port;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  @Bean
  public RedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setHostName(host);
    connectionFactory.setPort(port);
    return connectionFactory;
  }

}
