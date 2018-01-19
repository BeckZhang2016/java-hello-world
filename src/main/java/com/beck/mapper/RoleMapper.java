package com.beck.mapper;

import com.beck.entities.Role;

import java.util.List;


public interface RoleMapper {

    List<Role> findAll();

    int saveOne(String name);
}
