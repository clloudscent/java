package org.example.board.controller;

import org.example.board.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BoardController {
    BoardService service;

    public BoardController(BoardService service){
        this.service = service;
    }

}
