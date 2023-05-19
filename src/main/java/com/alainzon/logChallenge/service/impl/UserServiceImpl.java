package com.alainzon.logChallenge.service.impl;

import com.alainzon.logChallenge.dto.UserDto;
import com.alainzon.logChallenge.entity.Role;
import com.alainzon.logChallenge.entity.User;
import com.alainzon.logChallenge.entity.UserName;
import com.alainzon.logChallenge.repository.RoleRepository;
import com.alainzon.logChallenge.repository.UserRepository;
import com.alainzon.logChallenge.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(new UserName(userDto.getFirstName(), userDto.getLastName()));
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findRoleByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        //user.setRoles(Arrays.asse(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getName().getFirst());
        userDto.setLastName(user.getName().getLast());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
