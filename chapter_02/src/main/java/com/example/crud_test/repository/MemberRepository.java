package com.example.crud_test.repository;

import com.example.crud_test.entity.Members;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepository {

    private static List<Members> MEMBERS = new ArrayList<>();

    public List<Members> findAll(){
        return MEMBERS;
    }

    public Optional<Members> findById(Long id){
        return MEMBERS.stream().filter(b->b.getId().equals(id)).findFirst();
    }

    public Optional<Members> findByEmail(String email){
        return MEMBERS.stream().filter(b->b.getEmail().equals(email)).findFirst();
    }

    public Members save(Members members){
        if(members.getId()==null){
            if(findByEmail(members.getEmail()).isPresent()){
                throw new RuntimeException("이미 가입되어있는 메일입니다.");
            }
            members.setId(MEMBERS.stream().mapToLong(Members::getId).max().orElse(0L)+1);
            MEMBERS.add(members);
        }else{
            Members db = MEMBERS.stream().filter(b->b.getId().equals(members.getId())).findFirst().orElseThrow();
            db.setEmail(members.getEmail());
            db.setName(members.getName());
            db.setPassword(members.getPassword());
        }
        return members;
    }
}
