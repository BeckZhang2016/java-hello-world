package com.beck.controller;

import com.alibaba.fastjson.JSON;
import com.beck.entities.Role;
import com.beck.libs.RedisClient;
import com.beck.mapper.RoleMapper;
import com.beck.repository.RoleRepository;
import com.beck.vo.ResultVO;
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
  public ResultVO saveOne(@RequestBody String body) {
    Map maps = (Map) JSON.parse(body);
    String name = (String) maps.get("name");
    int status = roleMapper.saveOne(name);
    ResultVO responseData;
    if (status != 0) {
      responseData = new ResultVO<>(200, "success", null);
      redisClient.setEx(name, Integer.toString(status), 60 * 60);
    } else {
      responseData = new ResultVO<>(500, "fail", null);
    }
    return responseData;
  }

  @RequestMapping(value = "/role", method = RequestMethod.GET)
  @ResponseBody
  public ResultVO getAll() {
    List<Role> users = roleMapper.findAll();

    return new ResultVO<>(200, "success", users);
  }
}
