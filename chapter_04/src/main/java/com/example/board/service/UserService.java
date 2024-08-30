package com.example.board.service;

import com.example.board.domain.user.entity.BoardUser;
import com.example.board.domain.user.entity.type.UserStatus;
import com.example.board.dto.request.LoginRequest;
import com.example.board.dto.request.SignupRequest;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final List<BoardUser> REPOSITORY = new ArrayList<>();

    @PostConstruct
    public void init(){
        BoardUser writer1 = new BoardUser();
        BoardUser writer2 = new BoardUser();
        writer1.setId(1L);
        writer1.setEmail("test@test.com");
        writer1.setPassword("1234");
        writer1.setNickname("john");
        writer1.setCreatedAt(LocalDateTime.now());
        writer1.setUpdatedAt(LocalDateTime.now());
        writer2.setId(2L);
        writer2.setEmail("test2@test.com");
        writer2.setPassword("1234");
        writer2.setNickname("wick");
        writer2.setCreatedAt(LocalDateTime.now());
        writer2.setUpdatedAt(LocalDateTime.now());
        REPOSITORY.add(writer1);
        REPOSITORY.add(writer2);
    }

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

    public ResponseEntity<Map<String,String>> signUp(SignupRequest body){
        BoardUser user = findByEmail(body.getEmail());

        if(user != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message",body.getEmail()+" is exists!!"));
        }

        user = new BoardUser();
        user.setId(REPOSITORY.stream().mapToLong(BoardUser::getId).max().orElse(0L)+1);
        user.setNewUser(body);

        REPOSITORY.add(user);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public BoardUser findByEmail(String email){
        return REPOSITORY.stream().filter(u->u.getEmail().equals(email)).findFirst().orElse(null);
    }
}
