package com.exception.exceptionstudy.repository;

import com.exception.exceptionstudy.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart findByBoardNoAndUserNo(Long boardNo, Long userNo);
}
