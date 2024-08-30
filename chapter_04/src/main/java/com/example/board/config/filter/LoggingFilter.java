package com.example.board.config.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LoggingSystem;

public class LoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LoggingSystem.class);
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) req;
        log.info("=== Method : {} ===",r.getMethod());
        log.info("=== Path : {} ===",r.getRequestURI());
        log.info("=== Client IP : {} ===", r.getRemoteAddr());

        filterChain.doFilter(req,res);
    }
}
