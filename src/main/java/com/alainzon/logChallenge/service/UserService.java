package com.alainzon.logChallenge.service;

import com.alainzon.logChallenge.dto.UserDto;
import com.alainzon.logChallenge.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
