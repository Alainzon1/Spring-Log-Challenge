package com.alainzon.logChallenge.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document(collection = "Users")
public class Role
{
    @Id
    private String _id;

    private String guid;


    private String name;


    private List<User> users;
}
