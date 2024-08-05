package com.example.crud_test.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class BoardController {
    //게시글 목록 조회
    @GetMapping ("/")
    public String getBoardList() {
        return "boardList";
    }

    //게시글 상세 조회
    @GetMapping ("/{boardId}")
    public String getBoardDetail() {
        return "boardDetail";
    }

    //게시글 등록
    @GetMapping ("/new")
    public String createBoard() {
        return "createBoard";
    }

    //게시글 수정
    @GetMapping ("/{boardId}/edit")
    public String editBoard() {
        return "editBoard";
    }

    //게시글 삭제
    @GetMapping ("/{boardId}/delete")
    public String deleteBoard() {
        return "deleteBoard";
    }
}
