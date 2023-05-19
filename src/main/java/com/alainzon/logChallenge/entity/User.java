package com.alainzon.logChallenge.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Data
@Document(collection = "Users")
public class User {

    private Set<Role> roles = new HashSet<>();


    @Id
    private String _id;

    private String guid;

    private Boolean isActive;

    private String balance;

    private String picture;

    private Integer age;

    private String eyeColor;

    private UserName name;

    private String company;


    private String email;


    private String password;

    private String phone;

    private String address;

    public User get() {
        return null;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

