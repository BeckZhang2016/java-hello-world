package com.beck.controller;

import com.alibaba.fastjson.JSON;
import com.beck.bean.User;
import com.beck.dao.UserRepository;
import com.beck.libs.Encryption;
import com.beck.libs.ResponseData;
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

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  @ResponseBody
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
  @ResponseBody
  public List<User> findOne(@PathVariable(value = "username") String username) throws IOException {
    return userRepository.findOne(username);
  }

  @RequestMapping(value = "/user/register", method = RequestMethod.POST)
  @ResponseBody
  public ResponseData registerApp(@RequestBody String body) throws IOException {
    Map map = (Map) JSON.parse(body);
    List<User> userCount = userRepository.findOne(map.get("username").toString());
    if (userCount.size() == 0) {
      map.put("password", Encryption.getKeySha(map.get("password").toString()));
      responseData = new ResponseData(200, "注册成功", userRepository.registerApp(map));
    } else {
      responseData = new ResponseData(404, "用户名已被注册");
    }
    return responseData;
  }

  @RequestMapping(value = "/user/login", method = RequestMethod.POST)
  @ResponseBody
  public ResponseData loginApp(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
    Map map = (Map) JSON.parse(body);
    map.put("password", Encryption.getKeySha(map.get("password").toString()));
    int count = userRepository.loginApp(map);
    if (count == 0) {
      responseData = new ResponseData(400, "login fail");
    } else {
      String jwtToken = Encryption.jwtEncryption();
      response.setHeader("token", jwtToken);
      request.getSession().setAttribute(jwtToken, map.toString());
      logger.info(jwtToken);
      responseData = new ResponseData(200, "login success", jwtToken);
    }
    return responseData;
  }

}
