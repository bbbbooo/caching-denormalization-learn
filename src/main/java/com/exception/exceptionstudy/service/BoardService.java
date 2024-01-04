package com.exception.exceptionstudy.service;

import com.exception.exceptionstudy.domain.Board;
import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.response.CreateBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadAllBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadBoardResponse;
import com.exception.exceptionstudy.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public CreateBoardResponse createBoard(CreateBoardRequest createBoardRequest) {
        Board board = boardRepository.save(Board.from(createBoardRequest));
        return board.toCreateResponse();
    }

    @Transactional(readOnly = true)
    public Page<ReadAllBoardResponse> readAllBoard(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(Board::toReadAllResponse);
    }

    @Cacheable(key = "#boardNo", value = "customerCache")
    @Transactional(readOnly = true)
    public ReadBoardResponse readBoard(Long boardNo) {
        Board board = boardRepository.getReferenceById(boardNo);

        return board.toReadResponse();
    }
}
