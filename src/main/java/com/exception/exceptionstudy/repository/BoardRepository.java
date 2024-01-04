package com.exception.exceptionstudy.repository;

import com.exception.exceptionstudy.domain.Board;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Slice<Board> findByBoardNoLessThan(Long boardNo, PageRequest pageRequest);
}
