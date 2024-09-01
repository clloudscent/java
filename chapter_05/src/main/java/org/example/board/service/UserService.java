package org.example.board.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.board.dto.request.LoginRequest;
import org.example.board.dto.request.SignupRequest;
import org.example.board.dto.response.CommonResponse;
import org.example.board.entity.BoardUser;
import org.example.board.repository.BoardUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BoardUserRepository repository;

    public UserService(BoardUserRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<CommonResponse> login(HttpServletRequest request, LoginRequest body){
        //로그인하려는 계정이 있는 지 확인해야함
        BoardUser user = getUserInfo(body.getEmail());

        // not exists user info
        if( user == null || !user.getPassword().equals(body.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.of("fail authenticate"));
        }

        // save authenticate info in session
        request.getSession().setAttribute("auth",user);

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.of("OK"));
    }

    public ResponseEntity<CommonResponse> signUp(SignupRequest body){
        BoardUser user = getUserInfo(body.getEmail());

        if(user != null){
            // {"message":"this email exists"}
            return ResponseEntity.status(HttpStatus.CONFLICT).body(CommonResponse.of("존재하는 Email"));
        }
        user = new BoardUser();

        //set User
        user.setNewUser(body);
        repository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.of("sign up complete!! plz try log in"));
    }

    public BoardUser getUserInfo(String email){
        return repository.findByEmail(email).orElse(null);
    }
}
