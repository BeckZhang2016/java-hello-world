package com.beck.repository;

import com.beck.entities.Role;
import com.beck.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {
  private static final Logger logger = LoggerFactory.getLogger(RoleRepository.class);
  @Autowired
  private RoleMapper roleMapper;

  public Integer saveOne(String name){
    return roleMapper.saveOne(name);
  }

  public List<Role> findAll(){
    return roleMapper.findAll();
  }
}
