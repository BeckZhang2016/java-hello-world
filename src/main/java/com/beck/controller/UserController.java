package com.beck.controller;

import com.alibaba.fastjson.JSON;
import com.beck.entities.User;
import com.beck.dao.UserRepository;
import com.beck.libs.Encryption;
import com.beck.libs.ResponseData;
import com.beck.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

  private final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
  private ResponseData responseData;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  @ResponseBody
  public ResponseData findAll() {
    return new ResponseData(200, "success", userMapper.findAll());
  }

  @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseData findOne(@PathVariable(value = "username") String username) throws IOException {
    return new ResponseData(200, "success", userMapper.findOne(username));
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @ResponseBody
  public ResponseData registerApp(@RequestBody String body) throws IOException {
    Map map = (Map) JSON.parse(body);
    List<User> userCount = userMapper.findOne(map.get("username").toString());
    if (userCount.size() == 0) {
      map.put("password", Encryption.getKeySha(map.get("password").toString()));
      responseData = new ResponseData(200, "注册成功", userMapper.registerApp(map));
    } else {
      responseData = new ResponseData(404, "用户名已被注册");
    }
    return responseData;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public ResponseData loginApp(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
    Map map = (Map) JSON.parse(body);
    map.put("password", Encryption.getKeySha(map.get("password").toString()));
    String count = userMapper.loginApp(map);
    if (Integer.parseInt(count) == 0) {
      responseData = new ResponseData(400, "login fail");
    } else {
      String jwtToken = Encryption.jwtEncryption();
      response.setHeader("token", jwtToken);
      request.getSession().setAttribute(jwtToken, map.toString());
      logger.debug(jwtToken);
      responseData = new ResponseData(200, "login success", jwtToken);
    }
    return responseData;
  }

}
