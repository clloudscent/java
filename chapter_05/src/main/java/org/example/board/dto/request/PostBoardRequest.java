package org.example.board.dto.request;

import org.example.board.entity.Board;
import org.example.board.entity.BoardUser;

import java.time.LocalDateTime;

public class PostBoardRequest {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Board toEntity(BoardUser writer){
        Board board = new Board();
        board.setTitle(this.title);
        board.setContent(this.content);
        board.setWriter(writer);
        board.setCreatedAt(LocalDateTime.now());
        return board;
    }
}
