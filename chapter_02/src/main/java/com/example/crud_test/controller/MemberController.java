package com.example.crud_test.controller;

import com.example.crud_test.entity.Members;
import com.example.crud_test.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {

  private final MemberService service;

  public MemberController(MemberService service) {
    this.service = service;
  }

  @PostMapping("/login")
  public ResponseEntity<Void> login(HttpSession session, @RequestBody Members member){
    return service.login(session,member);
  }

  @PostMapping("/sign-up")
  public ResponseEntity<Void> signUp(@RequestBody Members member){
    return service.signUp(member);
  }

  @GetMapping("/list")
  public ResponseEntity<List<Members>> getMembers(HttpSession session){
    return service.getList(session);
  }

}
