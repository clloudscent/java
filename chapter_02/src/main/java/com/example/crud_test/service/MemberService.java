package com.example.crud_test.service;

import com.example.crud_test.entity.Members;
import com.example.crud_test.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService() {
        this.memberRepository = new MemberRepository();
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
        // response status : 201
        if(memberRepository.findByEmail(member.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else {
            memberRepository.save(member);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    public ResponseEntity<List<Members>> getList(HttpSession session) {
        Members me = (Members) session.getAttribute("principal");

        if(me==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.status(200).body(memberRepository.findAll());
    }
}
