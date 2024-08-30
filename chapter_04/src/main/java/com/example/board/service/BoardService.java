package com.example.board.service;

import com.example.board.domain.board.entity.Board;
import com.example.board.domain.user.entity.BoardUser;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private static final List<Board> REPOSITORY = new ArrayList<>();

    @PostConstruct
    public void init(){
        BoardUser writer1 = new BoardUser();
        BoardUser writer2 = new BoardUser();
        writer1.setId(1L);
        writer1.setEmail("test@test.com");
        writer1.setPassword("1234");
        writer1.setNickname("john");
        writer1.setCreatedAt(LocalDateTime.now());
        writer1.setUpdatedAt(LocalDateTime.now());
        writer2.setId(2L);
        writer2.setEmail("test2@test.com");
        writer2.setPassword("1234");
        writer2.setNickname("wick");
        writer2.setCreatedAt(LocalDateTime.now());
        writer2.setUpdatedAt(LocalDateTime.now());

        for(int i=1;i<=10;i++){
            REPOSITORY.add(new Board((long) i,"title_"+i, "content_"+i, (i % 2 == 0?writer1:writer2)));
        }
    }

    public List<Board> getList() {
        return REPOSITORY;
    }

    public Board getOne(Long boardId){
        Board post = REPOSITORY.stream().filter(b-> Objects.equals(b.getId(), boardId)).findFirst().orElse(null);

        if( post == null){
            throw new RuntimeException("not exist post!!!!!");
        }
        return post;
    }

    public Board postBoard(HttpSession session, Board board){
        BoardUser authUser = getPrincipal(session);

        long max = REPOSITORY.stream().mapToLong(Board::getId).max().orElse(1);

        board.setId(max+1);
        board.setWriter(authUser);
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        REPOSITORY.add(board);
        return board;
    }

    public Board modifyBoard(HttpSession session, Long boardId, Board post) {
        Board board = checkMine(session,boardId);

        if(post.getTitle() != null)
            board.setTitle(post.getTitle());

        if(post.getContent() != null)
            board.setContent(post.getContent());

        return board;
    }

    public void deleteOne(HttpSession session, Long boardId){
        Board post = checkMine(session,boardId);
        REPOSITORY.remove(post);
    }

    // 로그인한 유저가 작성한 포스트 검증
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

    public void increaseViewCount(Long boardId){
        Board board = getOne(boardId);
        board.setViewCount(board.getViewCount()+1);
    }
}
