package com.example.board.service;

import com.example.board.domain.board.entity.Board;
import com.example.board.domain.user.entity.BoardUser;
import com.example.board.repository.BoardRepository;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    private final BoardRepository repository;

    public BoardService(BoardRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly=true)
    public List<Board> getList() {
        return repository.findAll();
    }

    public Board getOne(Long boardId){
        Board post = repository.findById(boardId).orElse(null);

        if( post == null ){
            throw new RuntimeException("not exist post!!!!!");
        }
        return post;
    }

    @Transactional
    public Board postBoard(HttpSession session, Board board){
        BoardUser authUser = getPrincipal(session);

        board.setWriter(authUser);
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        repository.save(board);
        return board;
    }

    @Transactional
    public Board modifyBoard(HttpSession session, Long boardId, Board post) {
        Board board = checkMine(session,boardId);

        if(post.getTitle() != null)
            board.setTitle(post.getTitle());

        if(post.getContent() != null)
            board.setContent(post.getContent());

        return board;
    }

    @Transactional
    public void deleteOne(HttpSession session, Long boardId){
        Board post = checkMine(session,boardId);
        repository.delete(post);
    }

    // 로그인 한 유저가 작성한 포스트 검증
    private Board checkMine(HttpSession session, Long boardId){
        BoardUser authUser = getPrincipal(session);
        Board post = getOne(boardId);

        if (!post.getWriter().getId().equals(authUser.getId())) {
            throw new RuntimeException("not your post!!!!!");
        }
        return post;
    }

    // 로그인 상태 검증
    private BoardUser getPrincipal(HttpSession session){
        return (BoardUser) session.getAttribute("auth");
    }

    @Transactional
    public void increaseViewCount(Long boardId){
        Board board = getOne(boardId);
        board.setViewCount(board.getViewCount()+1);
    }
}
