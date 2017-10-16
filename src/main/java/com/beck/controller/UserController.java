package com.beck.controller;

import com.beck.bean.User;
import com.beck.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

//  private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepository;

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  @ResponseBody
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
  @ResponseBody
  public List<User> findOne(@PathVariable(value = "id") Integer id) throws IOException {
    return userRepository.findOne(id);
  }

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  @ResponseBody
  public List<User> saveOne(@RequestParam User user){
    return userRepository.saveOne();
  }
}
