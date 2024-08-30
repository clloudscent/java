package com.example.board.config.filter;

import com.example.board.domain.user.entity.BoardUser;
import com.example.board.domain.user.entity.type.UserStatus;
import com.example.board.dto.request.LoginRequest;
import com.example.board.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class LoginFailCountFilter implements Filter {

  private static final Logger log = LoggerFactory.getLogger(LoginFailCountFilter.class);
  private final UserService userService;

  public LoginFailCountFilter(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
    ContentCachingRequestWrapper request = new ContentCachingRequestWrapper((HttpServletRequest) req);
    ContentCachingResponseWrapper response = new ContentCachingResponseWrapper((HttpServletResponse)res);

    filterChain.doFilter(request,response);

    String requestBody = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
    LoginRequest loginRequest = new ObjectMapper().readValue(requestBody,LoginRequest.class);
    log.info("Request Body: {}", requestBody);

    if(response.getStatus() != 200){
      BoardUser user = userService.findByEmail(loginRequest.getEmail());
      if( user.getStatus() == UserStatus.ACTIVE ) {
        user.loginFail();
      }
      log.info("Login Fail Count: {}, Status: {}", user.getFailCount(), user.getStatus());
    }

    response.copyBodyToResponse();
  }
}
