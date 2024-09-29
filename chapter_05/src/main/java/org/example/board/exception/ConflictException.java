package org.example.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ConflictException extends RuntimeException {
    private final HttpStatusCode status;

    public ConflictException(String message) {
        super(message);
        status = HttpStatus.CONFLICT;
    }

    public HttpStatusCode getStatus() {
        return status;
    }
}
