package com.example.board.config.filter;

import com.example.board.domain.user.entity.BoardUser;
import com.example.board.dto.request.LoginRequest;
import com.example.board.service.UserService;
import com.example.board.wrapper.RequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoginFailCountFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(LoginFailCountFilter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserService userService;

    public LoginFailCountFilter(UserService userService){
        // 생성자
        this.userService=userService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        // reusable InputStream setting
        RequestWrapper r = new RequestWrapper((HttpServletRequest) req);

        log.info("================ before login process ================");

        filterChain.doFilter(r,res);

        log.info("================ after login process ================");

        InputStream is = r.getInputStream();

        // 로그인 아이디를 바디에서 꺼내 오기
        LoginRequest body = objectMapper.readValue(is,LoginRequest.class);

        // 로그인 하려는 계정이 있는지 확인
        BoardUser userInfo = userService.getUserInfo(body.getEmail()); // userService에 user 정보가 담긺

        if(((HttpServletResponse)res).getStatus() != 200 ){                 // login fail
            if( userInfo != null ){  // exists user info
                // 비밀번호 확인 전 실패 횟수 체크
                //   - 실패횟수 임계치 이상 혹은 상태 이상시 : 실패 처리 (5번이상 실패시 실패처리)
                userInfo.setLoginTrial(userInfo.getLoginTrial()+1);
                log.info("======= login fail - email : {} / count : {}", userInfo.getEmail(), userInfo.getLoginTrial());
            }
        }else{
            log.info("======= login success =======");
            userInfo.setLoginTrial(0);
        }
    }
}
