package org.example.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class NotFoundException extends RuntimeException{
    private final HttpStatusCode status;

    public NotFoundException() {
        super("not found data");
        this.status = HttpStatus.NOT_FOUND;
    }

    public HttpStatusCode getStatus() {
        return status;
    }
}
