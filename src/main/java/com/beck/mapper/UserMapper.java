package com.beck.mapper;

import com.beck.bean.Role;
import com.beck.bean.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface UserMapper {
  @Select({"SELECT u1.userId, u1.name, u1.departmentId, u1.roleId, d1.name as department, r1.name as role, u1.createdate FROM t_user u1 LEFT JOIN t_role r1 ON r1.roleId=u1.roleId LEFT JOIN t_department d1 ON d1.departmentId=u1.departmentId"})
  @Results({
          @Result(id = true, property = "id", column = "userId"),
          @Result(property = "name", column = "name"),
          @Result(property = "department.id", column = "departmentId"),
          @Result(property = "department.name", column = "department"),
          @Result(property = "role.id", column = "roleId"),

          @Result(property = "role.name", column = "role"),
          @Result(property = "createdate", column = "createdate")
  })
  List<User> findAll();
}
