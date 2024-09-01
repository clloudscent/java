package org.example.board.repository;

import org.example.board.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {
    Optional<BoardUser> findByEmail(String email);
}
