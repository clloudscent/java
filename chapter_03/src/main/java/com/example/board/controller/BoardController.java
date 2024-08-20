package com.example.board.controller;

import com.example.board.domain.board.entity.Board;
import com.example.board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/board")
public class BoardController {

    // 개발순서
    // 1. 기능 목록 정의 : 컨트롤러 정의
    // 2. 비즈니스 로직 구현 : 서비스 클래스 정의

    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping
    public List<Board> getPages(){
        return service.getList();
    }

    @GetMapping("/{board-id}")
    public Board getDetail(@PathVariable("board-id") Long boardId){
        return service.getOne(boardId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Board postBoard(HttpSession session, @RequestBody Board post){
        return service.postBoard(session, post);
    }

    @PutMapping("/{board-id}")
    public Board modifyBoard(HttpSession session, @PathVariable("board-id") Long boardId,@RequestBody Board post){
        return service.modifyBoard(session, boardId, post);
    }

    @DeleteMapping("/{board-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDetail(HttpSession session, @PathVariable("board-id") Long boardId){
        service.deleteOne(session, boardId);
    }
}
