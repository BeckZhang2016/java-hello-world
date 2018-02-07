package com.beck.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.io.UnsupportedEncodingException;

@Configuration
public class MyRedisChannelListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] channel = message.getChannel();
        byte[] bs = message.getBody();

        try {
            String content = new String(bs, "UTF-8"); // message content
            String p = new String(channel, "UTF-8"); //频道
            System.out.println(String.format("get %s from %s", content, p));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对消息进行序列化操作，默认采用StringRedisSerializer
     *
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(new MyRedisChannelListener());
    }

    /**
     * 在redis客户端接受到消息后，通过PatternTopic派发消息到对应的消息监听器，并构造一个线程池任务来调用MessageListener
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(@Qualifier("redisConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter(), new PatternTopic("news"));
        return container;
    }

    @Bean
    StringRedisTemplate template(@Qualifier("redisConnectionFactory") RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}
