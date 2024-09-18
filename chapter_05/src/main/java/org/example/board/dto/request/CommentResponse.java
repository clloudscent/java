package org.example.board.dto.request;

import org.example.board.entity.Comment;

import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private CommentResponse(){}

    public static CommentResponse of(Comment comment){
        CommentResponse response = new CommentResponse();
        response.id = comment.getId();
        response.content = comment.getContent();
        response.createdAt = comment.getCreatedAt();
        return response;
    }
    public static CommentResponseBuilder builder(){
        return new CommentResponseBuilder();
    }

    // builder pattern
    public static class CommentResponseBuilder{
        private final CommentResponse response;

        private CommentResponseBuilder(){
            this.response = new CommentResponse();
        }

        public CommentResponseBuilder id(Long id){
            response.id = id;
            return this;
        }

        public CommentResponseBuilder content(String content){
            response.content = content;
            return this;
        }

        public CommentResponseBuilder createdAt(LocalDateTime createdAt){
            response.createdAt = createdAt;
            return this;
        }

        public CommentResponse build(){
            return response;
        }
    }
}
