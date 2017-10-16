package com.beck.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RoleRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public Integer saveOne(String name) {
    int id = jdbcTemplate.update("INSERT INTO t_role (`name`) VALUES (?)", new Object[]{name});
    return id;
  }
}
