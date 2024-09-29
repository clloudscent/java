package org.example.board.service;

import org.example.board.dto.request.CommentResponse;
import org.example.board.dto.request.PostBoardRequest;
import org.example.board.dto.response.BoardDetailResponse;
import org.example.board.dto.response.BoardResponse;
import org.example.board.entity.Board;
import org.example.board.entity.BoardUser;
import org.example.board.entity.Comment;
import org.example.board.exception.NotFoundException;
import org.example.board.repository.BoardRepository;
import org.example.board.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardService {
    //멤버필드 추가
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    //생성자 ->멤버필드에 객체를 넣어주기위해(초기화하기위해서)
    public BoardService(BoardRepository boardRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    //게시판 조회하는 기능
    /*
    게시판 조회

    게시판 조회를 위해 BoardDetailResponse 를 반환하는 서비스 기능
    게시판 조회시 필요한 기능
    1. 조회시 게시판 조회수 증가
    2. 수정된 게시판 정보 저장 ( update )
    3. 게시판 Reaponse 객체로 변환 후 반환 ( return )

    (1) board 객체 생성
        초기화를 위해 board 객체를 생성하고 초기화할 데이터를 BoardDetailResponse에 담아주기위해
        a. board 객체를 조회할 게시판 id로 초기화 작업 진행
            -> 보드 객체를 조회 후 저장할 변수 생성
    (2) 조회수 기능을 추가한다. -> 조회수를 증가시킨다.
        a. 조회할 게시판에 해당하는 조회수를 가져오고 조회수를 늘려주는 기능을 추가한다.
             -> 조회할 게시판에 해당하는 조회수를 가져온다
             -> 조회수를 늘려준다.
             -> 증가된 조회수를 보드 객체에 적용한다.
        b. 바뀐 조회수를 다시 초기화하고 저장한다.
             -> 수정된 보드 객체 ( 엔티티 ) 를 저장한다. ( update )
    (3) board 객체를 게시판 조회 형태의 boardDetailResponse 객체안에 담고 반환한다.
        -> board 객체로 boardDetailResponse 객체를 생성후 boardDetailResponse 객체를 반환한다.

     */
    public BoardDetailResponse getBoard(Long id){
        Board board = getOne(id);
        board.addViewCount();
        boardRepository.save(board);
        return BoardDetailResponse.of(board);
    }

    private Board getOne(Long id){
        return boardRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<BoardResponse> getPage(Pageable pageable) {
        return boardRepository.findAll(pageable).map(BoardResponse::of);
    }

    //게시판 생성
    public BoardDetailResponse createPost(PostBoardRequest body, BoardUser writer){
        Board board = body.toEntity(writer);
        boardRepository.save(board);
        return BoardDetailResponse.of(board);
    }

    //게시판 수정
    public BoardDetailResponse updatePost(Long id, PostBoardRequest body, BoardUser writer){
        Board board = getOne(id);

        if(board.getWriter().equals(writer)){
            board.setContent(body.getContent());
            board.setTitle(body.getTitle());
            board.setUpdatedAt(LocalDateTime.now());
        }

        boardRepository.save(board);
        return BoardDetailResponse.of(board);
    }

    // 댓글 조회 기능
    public Page<CommentResponse> getComments(Long boardId, Pageable pageable) {
        return commentRepository.findByBoardId(boardId, pageable)
                .map(CommentResponse::of);
    }

    // 댓글 생성
    public CommentResponse createComment(Long boardId, String content, BoardUser writer) {
        Comment comment = setComment(boardId, content, writer);
        commentRepository.save(comment);
        return CommentResponse.of(comment);
    }

    public Comment setComment(Long boardId,String content, BoardUser writer){
        Board board = getOne(boardId);
        // 디비에서 필요할때 조회함( 데이터가 실재로 없어도 당장은 예외가 발생되지 않음 )
        // - 댓글을 저장할때는 보드 객체가 필수 임.
        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setContent(content);
        comment.setWriter(writer);
        comment.setCreatedAt(LocalDateTime.now());

        return comment;
    }

    // 댓글 삭제
    public void deleteComment(BoardUser user, Long boardId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundException::new);
        commentRepository.delete(comment);
    }
}
