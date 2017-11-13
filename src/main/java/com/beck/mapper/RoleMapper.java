package com.beck.mapper;

import com.beck.bean.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {
  @Select("SELECT * FROM t_role")
  @Results({
          @Result(property = "name", column = "name"),
          @Result(property = "id", column = "roleId")
  })
  List<Role> getAll();

  @Insert("INSERT INTO t_role (`name`) VALUES (#{name})")
  int saveOne(String name);
}
