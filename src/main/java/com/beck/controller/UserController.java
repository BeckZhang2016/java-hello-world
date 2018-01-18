package com.beck.controller;

import com.alibaba.fastjson.JSON;
import com.beck.entities.User;
import com.beck.libs.EncryptUtil;
import com.beck.repository.UserRepository;
import com.beck.libs.JwtUtil;
import com.beck.mapper.UserMapper;
import com.beck.vo.ResultVO;
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

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO findAll() {
        return new ResultVO<>(200, "success", userRepository.findAll());
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO findOne(@PathVariable(value = "username") String username) throws IOException {
        return new ResultVO<>(200, "success", userRepository.findOneForName(username));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO registerApp(@RequestBody String body) throws IOException {
        Map map = (Map) JSON.parse(body);
        ResultVO responseData;
        List<User> userCount = userRepository.findOneForName(map.get("username").toString());
        if (userCount.size() == 0) {
            map.put("ecrptypwd", EncryptUtil.SHA256Encoder(map.get("password").toString()));
            responseData = new ResultVO<>(200, "注册成功", userRepository.registerApp(map));
        } else {
            responseData = new ResultVO<>(404, "用户名已被注册");
        }
        return responseData;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO loginApp(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
        Map map = (Map) JSON.parse(body);
        map.put("ecrptypwd", EncryptUtil.SHA256Encoder(map.get("password").toString()));
        String count = userRepository.loginApp(map);
        ResultVO responseData;
        if (Integer.parseInt(count) == 0) {
            responseData = new ResultVO(400, "login fail");
        } else {
            String jwtToken = JwtUtil.createToken(request.getRemoteAddr());
            response.setHeader("token", jwtToken);
            request.getSession().setAttribute(jwtToken, map.toString());
            logger.debug(jwtToken);
            responseData = new ResultVO<>(200, "login success", jwtToken);
        }
        return responseData;
    }

}
