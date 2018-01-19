package com.beck.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;
    private String username;
    private String ecrptypwd;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdate;

    public User() {
    }

    public User(String username, String ecrptypwd) {
        this.username = username;
        this.ecrptypwd = ecrptypwd;
    }

    public User(String name, String ecrptypwd, Date createdate) {
        this.username = name;
        this.ecrptypwd = ecrptypwd;
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", ecrptypwd='" + ecrptypwd + '\'' +
                ", createdate=" + createdate +
                '}';
    }
}