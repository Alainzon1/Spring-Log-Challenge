package com.alainzon.logChallenge.controller;

import com.alainzon.logChallenge.dto.UserDto;
import com.alainzon.logChallenge.entity.User;
import com.alainzon.logChallenge.entity.UserName;
import com.alainzon.logChallenge.repository.UserRepository;
import com.alainzon.logChallenge.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/detail")
    public void detail(@AuthenticationPrincipal UserDetails authentication,
    HttpServletResponse response) throws IOException {
        User users = userService.findByEmail("email");
    }

    @PutMapping("/{_id}")
    public User setUser(@PathVariable("_id")String _id,
                        @RequestBody Map<String, String> body){
        User updateUser = userRepository.findById(_id).get();
        UserName completeName = userRepository.findById(_id).get().getName();
        completeName.setFirst(body.get("first"));
        completeName.setLast(body.get("last"));
        updateUser.setName(completeName);
        updateUser.setAddress(body.get("address"));
        updateUser.setCompany(body.get("company"));
        updateUser.setEmail(body.get("email"));
        updateUser.setPassword(body.get("password"));
        updateUser.setPhone(body.get("phone"));
        userRepository.save(updateUser);
        return updateUser;
    }
}
