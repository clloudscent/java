package com.example.crud_test.repository;

import com.example.crud_test.entity.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardRepository  {

  private static List<Board> BOARDS = new ArrayList<>();

  public List<Board> findAll(){
    return BOARDS;
  }

  public Optional<Board> findById(Long id){
    return BOARDS.stream().filter(b->b.getId().equals(id)).findFirst();
  }

  public void deleteById(Long id){
    BOARDS = BOARDS.stream().filter(b->!b.getId().equals(id)).toList();
  }

  public Board save(Board board){
    if(board.getId()==null){
      board.setId(BOARDS.stream().mapToLong(Board::getId).max().orElseGet(()->0L)+1);
      BOARDS.add(board);
    }else{
      Board db = BOARDS.stream().filter(b->b.getId().equals(board.getId())).findFirst().orElseThrow(RuntimeException::new);
      db.setContent(board.getContent());
      db.setAuthor(board.getAuthor());
      db.setTitle(board.getTitle());
    }

    return board;
  }
}