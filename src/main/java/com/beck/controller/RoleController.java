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

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO saveOne(@RequestBody String body) {
        Map maps = (Map) JSON.parse(body);
        String name = (String) maps.get("name");
        int status = roleRepository.saveOne(name);
        ResultVO result;
        if (status != 0) {
            result = new ResultVO<>(200, "success");
            redisClient.setEx(name, Integer.toString(status), 60 * 60);
        } else {
            result = new ResultVO<>(500, "fail");
        }
        return result;
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getAll() {
        List<Role> users = roleRepository.findAll();

        return new ResultVO<>(200, "success", users);
    }
}
