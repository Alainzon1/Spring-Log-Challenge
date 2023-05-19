package com.alainzon.logChallenge.entity;

import lombok.Data;

@Data
public class UserName {

    public UserName(String first, String last){
        this.setFirst(first);
        this.setLast(last);
    }
    private String first;
    private String last;
}
