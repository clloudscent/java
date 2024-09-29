package org.example.board.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.board.dto.response.CommonResponse;
import org.example.board.entity.BoardUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class AuthenticationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final ObjectMapper mapper;

    public AuthenticationFilter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        log.info("====== start filter =======");
        log.info("====== auth filter progress ======");

        BoardUser auth = (BoardUser) ((HttpServletRequest)request).getSession().getAttribute("auth");
        if(auth == null) {
            // 필터에서 발생한 예외는 RestControllerAdvice 에서 처리 하지 못하기 때문에 직접 응답객체를 설정해야 함.
            HttpServletResponse response = (HttpServletResponse)res;        // 형변환
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());   // 캐릭터셋 설정
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);      // 바디 컨턴트 타입 정의
            response.setStatus(HttpStatus.UNAUTHORIZED.value());            // HTTP 상태 코드 정의

            try(PrintWriter writer = response.getWriter()){                 // 응답 바디 작성
                CommonResponse responseObject = CommonResponse.of("not authenticated");
                String body = mapper.writeValueAsString(responseObject);

                writer.write(body);
                writer.flush();                                             // 응답 전송 ( 통신 종료 / http 1.1 )
            }catch(Exception e){
                log.info(e.getMessage());
            }
            return; // 필터 체인 종료
        }
        log.info("===== USER : {} / authenticate success !! ====",auth.getEmail());

        filterChain.doFilter(request, res);

        log.info("end filter");
    }
}
