package com.beck.dao;

import com.beck.entities.Department;
import com.beck.entities.Role;
import com.beck.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;


//  @Transactional(readOnly = true)
  public List<User> findAll() {
    List<User> users = jdbcTemplate.query("SELECT u1.userId, u1.name, u1.departmentId, u1.roleId, d1.name as department, r1.name as role, u1.createdate FROM t_user u1 LEFT JOIN t_role r1 ON r1.roleId=u1.roleId LEFT JOIN t_department d1 ON d1.departmentId=u1.departmentId", new UserRowMap());
    return users;
  }

//  @Transactional(readOnly = true)
  public List<User> findOne(String username) throws IOException {
    List<User> users = jdbcTemplate.query("SELECT u1.userId, u1.name, u1.departmentId, u1.roleId, d1.name as department, r1.name as role, u1.createdate FROM t_user u1 LEFT JOIN t_role r1 ON r1.roleId=u1.roleId LEFT JOIN t_department d1 ON d1.departmentId=u1.departmentId WHERE u1.`name` = ?",
            new Object[]{username}, new UserRowMap());
    return users;
  }

  public int registerApp(Map userMap) {
    int rows = jdbcTemplate.update("INSERT INTO t_user (`name`, `password`, departmentId, roleId) VALUES (?,?,?,?)",
            new Object[]{userMap.get("username"),userMap.get("password"), userMap.get("departmentId"), userMap.get("roleId")});
    return rows;
  }

  public int loginApp(Map userMap) {
    ArrayList arrayList = (ArrayList) jdbcTemplate.query("SELECT COUNT(0) as total FROM t_user WHERE `name` = ? AND `password` = ?",
            new Object[]{userMap.get("username"), userMap.get("password")}, (rs, rowNum) -> rs.getInt("total"));
    return (int) arrayList.get(0);
  }

}

class UserRowMap implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {
    User user = new User();
    user.setId(resultSet.getInt("userId"));
    user.setName(resultSet.getString("name"));
    user.setCreatedate(resultSet.getTimestamp("createdate"));
    user.setDepartment(new Department(resultSet.getInt("departmentId"), resultSet.getString("department")));
    user.setRole(new Role(resultSet.getInt("roleId"), resultSet.getString("role")));
    return user;
  }
}
