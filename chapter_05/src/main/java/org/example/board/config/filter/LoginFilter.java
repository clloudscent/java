package org.example.board.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.board.dto.request.LoginRequest;
import org.example.board.dto.response.CommonResponse;
import org.example.board.entity.BoardUser;
import org.example.board.service.UserService;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.Writer;

public class LoginFilter implements Filter {
    private final UserService userService;
    private final ObjectMapper mapper;

    public LoginFilter(UserService userService,ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // HttpServletRequest로 캐스팅하여 HTTP 요청으로부터 추가 정보를 추출
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if(!httpRequest.getMethod().equals("POST")){
            // Request Method check!! ONLY POST
            httpResponse.sendError(HttpStatus.METHOD_NOT_ALLOWED.value());
            return;
        }

        // reqeust body parsing
        LoginRequest loginRequest = mapper.readValue(httpRequest.getInputStream(), LoginRequest.class);

        // find user info
        BoardUser user = userService.getUserInfo(loginRequest.getEmail());

        httpRequest.getSession().removeAttribute("auth");

        String responseBody = mapper.writeValueAsString(CommonResponse.of("fail authenticate"));;
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        if( user != null && user.isActivate()) {
            if (!user.getPassword().equals(loginRequest.getPassword())) {
                byte trial = (byte) (user.getLoginTrial() + 1);
                user.setLoginTrial(trial);
                if (trial >= 5) {
                    user.setActivate(false);
                }
            } else {
                // success
                responseBody = mapper.writeValueAsString(CommonResponse.of("OK"));
                user.setLoginTrial((byte) 0);
                status = HttpStatus.OK;
                httpRequest.getSession().setAttribute("auth", user);
            }
            userService.save(user);
        }
        sendResponse(httpResponse, status, responseBody);
    }

    private void sendResponse(HttpServletResponse response, HttpStatus status, String body){
        try(Writer writer = response.getWriter()){
            response.setStatus(status.value());
            writer.write(body);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//로그인된 회원만 CRUD
//로그인 failcountfilter logincheckfilter authentication
