package com.exception.exceptionstudy.service;

import com.exception.exceptionstudy.domain.Board;
import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.response.CreateBoardResponse;
import com.exception.exceptionstudy.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public CreateBoardResponse createBoard(CreateBoardRequest createBoardRequest) {
        Board board = boardRepository.save(Board.from(createBoardRequest));
        return board.toCreateResponse();
    }
}
