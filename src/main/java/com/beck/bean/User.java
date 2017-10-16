package com.beck.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User implements java.io.Serializable {

  private int id;
  private String name;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createdate;
  private Department department;
  private Role role;

  public User(int id, String name, Date createdate, Department deparment, Role role) {
    this.id = id;
    this.name = name;
    this.createdate = createdate;
    this.department = deparment;
    this.role = role;
  }

  public User() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreatedate() {
    return createdate;
  }

  public void setCreatedate(Date createdate) {
    this.createdate = createdate;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department deparment) {
    this.department = deparment;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", createdate=" + createdate +
            ", department=" + department +
            ", role=" + role +
            '}';
  }
}