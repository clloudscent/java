package com.example.crud_test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Board {
    @Id
    private Long id;
    private String title;
    private String content;
    private String author;

    //Getter, Setter
    public Long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //toString
    @Override
    public String toString() {
        return "Board [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + "]";
    }


}
