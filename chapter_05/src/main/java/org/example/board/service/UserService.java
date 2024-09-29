package org.example.board.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.board.dto.request.LoginRequest;
import org.example.board.dto.request.SignupRequest;
import org.example.board.dto.response.CommonResponse;
import org.example.board.entity.BoardUser;
import org.example.board.exception.ConflictException;
import org.example.board.repository.BoardUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final BoardUserRepository repository;

    public UserService(BoardUserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CommonResponse signUp(SignupRequest body){
        BoardUser user = getUserInfo(body.getEmail());

        if(user != null){
            // {"message":"this email exists"}
            throw new ConflictException("이미 존재하는 email 입니다.");
        }
        user = new BoardUser();

        //set User
        user.setNewUser(body);
        repository.save(user);

        return CommonResponse.of("sign up complete!! plz try log in");
    }

    public BoardUser getUserInfo(String email){
        return repository.findByEmail(email).orElse(null);
    }

    @Transactional
    public void save(BoardUser user) {
        repository.save(user);
    }
}
