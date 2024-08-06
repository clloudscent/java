package com.example.crud_test.service;

import com.example.crud_test.entity.Members;
import com.example.crud_test.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void login(Members members){
        Members result = memberRepository.findByEmail(members.getEmail()).orElseGet(()->null);
        if(result==null){
            throw new RuntimeException("없는 이메일입니다.");
        }
        if(result.getPassword().equals(members.getPassword())){

        }else{
            throw  new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }
}
