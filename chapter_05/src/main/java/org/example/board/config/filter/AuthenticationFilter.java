package org.example.board.config.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.example.board.entity.BoardUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("======start filter =======");
        log.info("======auth filter progress======");

        BoardUser auth = (BoardUser) ((HttpServletRequest)request).getSession().getAttribute("auth");
        if(auth == null){
            throw  new RuntimeException("not authenticated");
        }
        log.info("===== USER : {} / authenticate success !! ====",auth.getEmail());

        filterChain.doFilter(request, response);

        log.info("end filter");
    }
}
