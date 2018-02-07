package com.beck.controller;

import com.beck.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/setget")
    @ResponseBody
    public ResultVO connect(){
        redisTemplate.opsForValue().set("testenv","222222ccc", 100);

        return new ResultVO(200, redisTemplate.opsForValue().get("testenv"));
    }

    @PostMapping("/connnectionset")
    @ResponseBody
    public ResultVO connectionSet(String key, String value){
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            // one aways
            connection.set(key.getBytes(), value.getBytes());
            // another aways
            ((StringRedisConnection)connection).set(key, value);

            return "set success";
        });
        return new ResultVO(200, "success");
    }

    @PostMapping("/pub")
    @ResponseBody
    public ResultVO pub(){
        redisTemplate.convertAndSend("news","a news");
        return new ResultVO(200, "success");
    }
}
