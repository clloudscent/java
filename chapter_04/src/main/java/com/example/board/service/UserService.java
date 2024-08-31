package com.example.board.service;

import com.example.board.domain.user.entity.BoardUser;
import com.example.board.domain.user.entity.type.UserStatus;
import com.example.board.dto.request.LoginRequest;
import com.example.board.dto.request.SignupRequest;
import com.example.board.repository.BoardUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BoardUserRepository repository;

    public UserService(BoardUserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity<Void> login(HttpServletRequest request, LoginRequest body) {
        BoardUser user = findByEmail(body.getEmail());

        if(user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if( !user.getPassword().equals(body.getPassword()) ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(user.getStatus() != UserStatus.ACTIVE){
            return ResponseEntity.status(HttpStatus.LOCKED).build();
        }
        user.loginSuccess();
        request.getSession().setAttribute("auth", user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<Map<String,String>> signUp(SignupRequest body){
        BoardUser user = findByEmail(body.getEmail());

        if(user != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message",body.getEmail()+" is exists!!"));
        }

        user = new BoardUser();
        user.setNewUser(body);

        repository.save(user);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public BoardUser findByEmail(String email){
        return repository.findByEmail(email).orElse(null);
    }
}
