package com.example.crud_test.service;

import com.example.crud_test.entity.Board;
import com.example.crud_test.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }
    public Board getPostById(Long id) {
        return (Board) boardRepository.findBy(id).orElseThrow(
            () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );
    }
    public Board createPost(Board board) {
        return boardRepository.save(board);
    }
    public void editPost(Long id, Board board) {
        board = boardRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );
    }
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

}
