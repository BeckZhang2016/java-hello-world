package com.beck.dao;

import com.beck.bean.Department;
import com.beck.bean.Role;
import com.beck.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  String resource = "com/beck/mapper-config/mybatis-config.xml";


  @Transactional(readOnly = true)
  public List<User> findAll(){
    List<User> users = jdbcTemplate.query("SELECT u1.userId, u1.name, u1.departmentId, u1.roleId, d1.name as department, r1.name as role, u1.createdate FROM t_user u1 LEFT JOIN t_role r1 ON r1.roleId=u1.roleId LEFT JOIN t_department d1 ON d1.departmentId=u1.departmentId", new UserRowMap());
    return users;
  }

  @Transactional(readOnly = true)
  public List<User> findOne(Integer id) throws IOException {
    List<User> users = jdbcTemplate.query("SELECT u1.userId, u1.name, u1.departmentId, u1.roleId, d1.name as department, r1.name as role, u1.createdate FROM t_user u1 LEFT JOIN t_role r1 ON r1.roleId=u1.roleId LEFT JOIN t_department d1 ON d1.departmentId=u1.departmentId WHERE userId=?",
            new Object[]{id}, new UserRowMap());
    return users;
  }

  public List<User> saveOne(){
    List<User> users = jdbcTemplate.query("SELECT u1.userId, u1.name, u1.departmentId, u1.roleId, d1.name as department, r1.name as role, u1.createdate FROM t_user u1 LEFT JOIN t_role r1 ON r1.roleId=u1.roleId LEFT JOIN t_department d1 ON d1.departmentId=u1.departmentId WHERE userId=?",
            new Object[]{}, new UserRowMap());
    return users;
  }

}

class UserRowMap implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {
    User user = new User();
    user.setId(resultSet.getInt("userId"));
    user.setName(resultSet.getString("name"));
    user.setCreatedate(resultSet.getTimestamp("createdate"));
    user.setDepartment(new Department(resultSet.getInt("departmentId"),resultSet.getString("department")));
    user.setRole(new Role(resultSet.getInt("roleId"), resultSet.getString("role")));
    return user;
  }
}