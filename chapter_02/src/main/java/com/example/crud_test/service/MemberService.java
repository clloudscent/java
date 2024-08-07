package com.example.crud_test.service;

import com.example.crud_test.entity.Members;
import com.example.crud_test.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public ResponseEntity<Void> login(HttpSession session, Members members){
        Members result = memberRepository.findByEmail(members.getEmail()).orElseGet(()->null);
        if(result==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if(result.getPassword().equals(members.getPassword())){
            session.setAttribute("principal",result);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Void> signUp(Members member) {
        //숙제
        return null;
    }
}
