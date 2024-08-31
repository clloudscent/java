package com.example.board.controller;

import com.example.board.dto.request.LoginRequest;
import com.example.board.dto.request.SignupRequest;
import com.example.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Void> authenticate(HttpServletRequest request, @RequestBody LoginRequest body){
        return service.login(request,body);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Map<String,String>> signUp(@RequestBody SignupRequest body){
        return service.signUp(body);
    }
}
