package com.example.crud_test.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

import java.util.Objects;

//@Entity
public class Board {
//    @Id
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

    //toString
    @Override
    public String toString() {
        return "Board [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + "]";
    }


}
