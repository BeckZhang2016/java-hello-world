package com.beck.entities;

import lombok.Data;

@Data
public class Role {
    private int id;
    private String name;

    public Role() {
    }


    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
