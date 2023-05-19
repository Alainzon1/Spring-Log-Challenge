package com.alainzon.logChallenge.controller;

import com.alainzon.logChallenge.dto.UserDto;
import com.alainzon.logChallenge.entity.User;
import com.alainzon.logChallenge.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class AuthController {

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
}
