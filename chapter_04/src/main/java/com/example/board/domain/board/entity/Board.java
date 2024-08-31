package com.example.board.domain.board.entity;

import com.example.board.domain.user.entity.BoardUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private BoardUser writer;
    @Column(columnDefinition = "INT UNSIGNED")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Board board = (Board) o;
        return Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
