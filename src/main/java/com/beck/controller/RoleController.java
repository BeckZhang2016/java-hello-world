package com.beck.controller;

import com.alibaba.fastjson.JSON;
import com.beck.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RoleController {
  @Autowired
  private RoleRepository roleRepository;

  @RequestMapping(value = "/role", method = RequestMethod.POST)
  @ResponseBody
  public Integer saveOne(@RequestBody String body) {
    Map maps = (Map) JSON.parse(body);
    String name = (String) maps.get("name");
    return roleRepository.saveOne(name);
  }
}