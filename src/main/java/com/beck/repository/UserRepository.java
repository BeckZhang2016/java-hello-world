package com.beck.repository;

import com.beck.entities.User;
import com.beck.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    public UserMapper userMapper;


    public List<User> findAll(){
        return userMapper.findAll();
    }

    public List<User> findOneForName(String username){
        return userMapper.findOneForName(username);
    }

    public String loginApp(Map map){
        return userMapper.loginApp(map);
    }

    public int registerApp(Map map){
        return userMapper.registerApp(map);
    }

}
