package com.example.crud_test.service;

import com.example.crud_test.entity.Board;
import com.example.crud_test.entity.Members;
import com.example.crud_test.repository.BoardRepository;
import com.example.crud_test.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService() {
        this.boardRepository = new BoardRepository();
    }

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public Board getPostById(Long id){
        return boardRepository.findById(id).orElseThrow();
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public void editBoard(Long id, Board board) {
        board.setId(id);
        boardRepository.save(board);
    }

    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
