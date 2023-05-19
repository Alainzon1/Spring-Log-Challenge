package com.alainzon.logChallenge.repository;

import com.alainzon.logChallenge.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RoleRepository extends MongoRepository<Role, String> {

    @Query("{username:'?0'}")
    Role findRoleByName(String role);
}