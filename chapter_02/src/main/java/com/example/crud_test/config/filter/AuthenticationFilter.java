package com.example.crud_test.config.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 인증 필터
//jakarta.servlet.Filter 인터페이스를 구현하여 필터를 생성
public class AuthenticationFilter implements Filter {

  // 로그 객체 생성
  private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

  // doFilter 메서드를 오버라이드하여 필터 로직을 구현
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    log.info("===== start authentication filter =====");

    HttpServletRequest servletRequest = (HttpServletRequest) request;
    log.info("Request: {}", servletRequest.getRequestURI());

    HttpSession session = servletRequest.getSession();

    if(session.getAttribute("principal") == null){
      log.info("=== 로그인 인증 실패 ===");
      HttpServletResponse servletResponse = (HttpServletResponse) response;

      // 401 Unauthorized
      servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      log.info("===== end authentication filter =====");

      return;
    }
    log.info("=== 로그인 인증 성공 ===");

    log.info("===== end authentication filter =====");

    filterChain.doFilter(request, response);
  }
}
