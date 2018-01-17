package com.beck.mapper;

import com.beck.entities.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
  List<User> findAll();

  List<User> findOneForName(String username);

  String loginApp(Map map);

  int registerApp(Map map);



}
