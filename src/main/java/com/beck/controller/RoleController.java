package com.beck.controller;

import com.alibaba.fastjson.JSON;
import com.beck.bean.Role;
import com.beck.dao.RoleRepository;
import com.beck.libs.RedisClient;
import com.beck.libs.ResponseData;
import com.beck.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

  @Autowired
  private RedisClient redisClient;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private RoleMapper roleMapper;

  @RequestMapping(value = "/role", method = RequestMethod.POST)
  @ResponseBody
  public ResponseData saveOne(@RequestBody String body) {
    Map maps = (Map) JSON.parse(body);
    String name = (String) maps.get("name");
    int status = roleRepository.saveOne(name);
    ResponseData responseData;
    if (status != 0) {
      responseData = new ResponseData(200, "success", null);
      redisClient.setEx(name, Integer.toString(status), 60 * 60);
    } else {
      responseData = new ResponseData(500, "fail", null);
    }
    return responseData;
  }

  @RequestMapping(value = "/role", method = RequestMethod.GET)
  @ResponseBody
  public ResponseData getAll() {
    ResponseData responseData;
    List<Role> users = roleMapper.getAll();

    return responseData = new ResponseData(200, "success", users);
  }
}
