package org.example.board.controller;

import jakarta.servlet.http.HttpSession;
import org.example.board.dto.request.CommentResponse;
import org.example.board.dto.request.PostBoardRequest;
import org.example.board.dto.request.PostCommentRequest;
import org.example.board.dto.response.BoardDetailResponse;
import org.example.board.dto.response.BoardResponse;
import org.example.board.entity.BoardUser;
import org.example.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/board")
public class BoardController {
    BoardService service;

    public BoardController(BoardService service){
        this.service = service;
    }

    @GetMapping
    public Page<BoardResponse> getPage(Pageable pageable){
        return service.getPage(pageable);
    }
    @GetMapping("/{id}")
    public BoardDetailResponse getOne(@PathVariable Long id){
        return service.getBoard(id);
    }

    @PostMapping
    public BoardDetailResponse postBoard(HttpSession session, @RequestBody PostBoardRequest body){
        return service.createPost(body,(BoardUser) session.getAttribute("auth"));
    }

    @PutMapping("/{id}")
    public BoardDetailResponse editBoard(HttpSession session, @PathVariable Long id, @RequestBody PostBoardRequest body){
        return service.updatePost(id, body, (BoardUser) session.getAttribute("auth"));
    }

    // method
    @PostMapping("/{id}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse createComment(HttpSession session, @PathVariable Long id, @RequestBody PostCommentRequest body){
        return service.createComment(id, body.getContent(), (BoardUser)session.getAttribute("auth"));
    }

    @DeleteMapping ("/{board-id}/comment/{comment-id}")
    public ResponseEntity<Void> deleteComment(HttpSession session, @PathVariable("board-id") Long boardId, @PathVariable("comment-id") Long commentId){
        return service.deleteComment((BoardUser)session.getAttribute("auth"), boardId, commentId);
    }

    @GetMapping("/{board-id}/comment")
    public Page<CommentResponse> getComment(@PathVariable("board-id") Long boardId, Pageable pageable){
        return service.getComments(boardId, pageable);
    }
}
