package org.example.board.dto.response;

import org.example.board.entity.Board;
import org.example.board.repository.BoardRepository;

import java.time.LocalDateTime;

public class BoardDetailResponse extends BoardResponse{
    private String content;
    private LocalDateTime updatedAt;
    private Long writerId;

    public String getContent() {
        return content;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getWriterId() {
        return writerId;
    }

    private BoardDetailResponse(Board board){
        super(board);
        this.content = board.getContent();
        this.updatedAt = board.getUpdatedAt();
        this.writerId = board.getWriter().getId();
    }

    public static BoardDetailResponse of(Board board){
        return new BoardDetailResponse(board);
    }
}
