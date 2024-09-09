package org.example.board.dto.response;

import org.example.board.entity.Board;
import org.example.board.entity.BoardUser;

import java.time.LocalDateTime;

public class BoardResponse {
    private Long id;
    private String title;
    private String writer; // nickname
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    protected BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.writer = board.getWriter().getNickname();
        this.createdAt = board.getCreatedAt();
    }

    public static BoardResponse of(Board board){
        return new BoardResponse(board);
    }
}
