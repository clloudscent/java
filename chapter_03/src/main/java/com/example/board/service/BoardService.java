package com.example.board.service;

import com.example.board.domain.board.entity.Board;
import com.example.board.domain.user.entity.BoardUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return REPOSITORY.stream().filter(b-> Objects.equals(b.getId(), boardId)).findFirst().orElse(null);
    }

    public Board postBoard(Board board){
        long max = REPOSITORY.stream().mapToLong(Board::getId).max().orElse(1);
        board.setId(max+1);
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        REPOSITORY.add(board);
        return board;
    }

    public Board modifyBoard(Long boardId, Board post) {
        Board board = REPOSITORY.stream().filter(b->b.getId().equals(boardId)).findFirst().orElseThrow(RuntimeException::new);
        if(post.getTitle() != null)
            board.setTitle(post.getTitle());

        if(post.getContent() != null)
            board.setContent(post.getContent());

        return board;
    }

    public void deleteOne(Long boardId){
        Board board = REPOSITORY.stream().filter(b->b.getId().equals(boardId)).findFirst().orElseThrow(RuntimeException::new);
        REPOSITORY.remove(board);
    }
}
