package com.example.crud_test.controller;

import com.example.crud_test.entity.Board;
import com.example.crud_test.entity.Members;
import com.example.crud_test.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    //게시글 목록 조회
    @GetMapping
    public List<Board> getBoardList() {
        return service.getBoardList();
    }

    //게시글 상세 조회
    @GetMapping ("/{boardId}")
    public Board getBoardDetail(@PathVariable Long boardId) {
        return service.getPostById(boardId);
    }

    //게시글 등록
    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return service.createBoard(board);
    }

    //게시글 수정
    @PutMapping ("/{boardId}/edit")
    public void editBoard(@PathVariable Long boardId, @RequestBody Board board) {
        service.editBoard(boardId, board);
    }

    //게시글 삭제
    @DeleteMapping ("/{boardId}/delete")
    public void deleteBoard(@PathVariable Long boardId) {
        service.deletePost(boardId);
    }
}
