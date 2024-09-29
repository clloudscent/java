package org.example.board.config.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.example.board.dto.response.CommonResponse;
import org.example.board.exception.ConflictException;
import org.example.board.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@RestControllerAdvice
public class ServiceExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    // 숙제 : Exception Handler 추상화 ( 난이도 : 중 )
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String,String>> conflict(HttpServletRequest req, ConflictException ce){
        log.info(req.getRequestURI());
        return ResponseEntity.status(ce.getStatus()).body(Map.of("message",ce.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse notFound(HttpServletRequest req, NotFoundException ne){
        CommonResponse responseObject = CommonResponse.of(ne.getMessage());
        log.info(req.getRequestURI());
        return responseObject;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse internalServerError(HttpServletRequest req, Throwable t){
        CommonResponse responseObject = CommonResponse.of(t.getMessage());

        /*
        1. Throwable 을 상속받은 모든 예외를 internalServerError 메서드에서 처리함
        2. /api/board/throw 주소의 컨트롤러에서 RuntimeException 을 발생시킴
        3. 해당 핸들러( internalServerError ) 를 테스트하려면 로그인 상태여야 함.
            - WebConfig class > setAuthenticationFilterBean 필터에서 로그인을 필수로 설정했기 때문

        4. 테스트 순서
          1) 로그인
          2) /api/board/throw API 호출
         */
        log.info(req.getRequestURI());
        return responseObject;
    }
}
