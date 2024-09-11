package org.example.board.service;

import org.example.board.dto.request.CommentResponse;
import org.example.board.dto.request.PostBoardRequest;
import org.example.board.dto.response.BoardDetailResponse;
import org.example.board.dto.response.BoardResponse;
import org.example.board.entity.Board;
import org.example.board.entity.BoardUser;
import org.example.board.entity.Comment;
import org.example.board.repository.BoardRepository;
import org.example.board.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public BoardService(BoardRepository boardRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public BoardDetailResponse getBoard(Long id){
        return boardRepository.findById(id).map(BoardDetailResponse::of).orElse(null);
    }

    public Page<BoardResponse> getPage(Pageable pageable) {
        return boardRepository.findAll(pageable).map(BoardResponse::of);
    }

    //게시판 생성
    public BoardDetailResponse createPost(PostBoardRequest body, BoardUser writer){
        Board board = new Board();
        board.setTitle(body.getTitle());
        board.setContent(body.getContent());
        board.setWriter(writer);
        board.setCreatedAt(LocalDateTime.now());

        boardRepository.save(board);
        return BoardDetailResponse.of(board);
    }




    // 댓글 조회 기능
    public Page<CommentResponse> getComments(Long boardId, Pageable pageable) {
        return commentRepository.findByBoardId(boardId, pageable)
                .map(comment -> CommentResponse.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .build());
    }

    // 댓글 생성
    public CommentResponse createComment(Long id, String content, BoardUser writer) {
        Board board = boardRepository.getReferenceById(id);

        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setContent(content);
        comment.setWriter(writer);
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    // 댓글 삭제
    public ResponseEntity<Void> deleteComment(BoardUser user, Long boardId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null || !comment.getBoard().getId().equals(boardId) || !comment.getWriter().equals(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        commentRepository.delete(comment);
        return ResponseEntity.noContent().build();
    }
}
