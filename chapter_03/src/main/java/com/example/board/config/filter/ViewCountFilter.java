package com.example.board.config.filter;

import com.example.board.service.BoardService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ViewCountFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(ViewCountFilter.class);

    private final BoardService service;

    public ViewCountFilter(BoardService service) {
        this.service = service;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        log.info("======start filter ========");
        log.info("=== view count progress ===");

        // 1. /api/board/{board-id} 경로에만 반응해야함
        // 2. 조회 기능이 정상적으로 완수된 후에 작동되야 함.

        HttpServletRequest r = (HttpServletRequest) req;

        if(!r.getRequestURI().equals("/api/board")){
            if(r.getMethod().equalsIgnoreCase("GET") && ((HttpServletResponse)res).getStatus() == 200){
                log.info("==== view count +1 ====");
                String path = r.getRequestURI();
                Long boardId = Long.parseLong(path.substring(path.lastIndexOf("/")+1));

                service.increaseViewCount(boardId);
            }
        }
        filterChain.doFilter(req,res);
    }
}
