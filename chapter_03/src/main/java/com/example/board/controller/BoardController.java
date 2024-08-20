package com.example.board.controller;

import com.example.board.domain.board.entity.Board;
import com.example.board.service.BoardService;
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

    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping
    public List<Board> getPages(){
//        return service.getList();
        return List.of();
    }

    @GetMapping("/{board-id}")
    public Board getDetail(@PathVariable("board-id") Long boardId){
//        return service.getOne(boardId);
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Board postBoard(@RequestBody Board post){
//        return service.postBoard(post);
        return post;
    }

    @PutMapping("/{board-id}")
    public Board modifyBoard(@PathVariable("board-id") Long boardId,@RequestBody Board post){
//        return service.modifyBoard(boardId, post);
        return post;
    }

    @DeleteMapping("/{board-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDetail(@PathVariable("board-id") Long boardId){
//        service.deleteOne(boardId);

    }
}
