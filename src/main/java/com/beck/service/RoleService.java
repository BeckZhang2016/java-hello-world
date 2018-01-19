package com.beck.service;

import com.beck.entities.Role;
import com.beck.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    @Autowired
    private RoleMapper roleMapper;

    public Integer saveOne(String name) {
        return roleMapper.saveOne(name);
    }

    public List<Role> findAll() {
        return roleMapper.findAll();
    }
}
