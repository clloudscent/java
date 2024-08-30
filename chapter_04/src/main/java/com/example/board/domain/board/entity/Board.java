package com.example.board.domain.board.entity;

import com.example.board.domain.user.entity.BoardUser;
import java.time.LocalDateTime;

public class Board {
    private Long id;
    private String title;
    private String content;
    private BoardUser writer;
    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BoardUser getWriter() {
        return writer;
    }

    public void setWriter(BoardUser writer) {
        this.writer = writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Board(){}

    public Board(Long id, String title, String content, BoardUser writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
