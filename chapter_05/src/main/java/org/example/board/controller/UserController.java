package org.example.board.controller;

import org.example.board.dto.request.SignupRequest;
import org.example.board.dto.response.CommonResponse;
import org.example.board.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // init receive request
    @PostMapping("/sign-up")
    public CommonResponse signUp(@RequestBody SignupRequest body){
        return service.signUp(body);
    }

}
