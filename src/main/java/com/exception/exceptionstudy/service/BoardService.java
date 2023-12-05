package com.exception.exceptionstudy.service;

import com.exception.exceptionstudy.domain.Board;
import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.response.CreateBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadAllBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadBoardResponse;
import com.exception.exceptionstudy.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public CreateBoardResponse createBoard(List<CreateBoardRequest> createBoardRequests) {
        List<Board> boards = new ArrayList<>();
        for (CreateBoardRequest createBoardRequest : createBoardRequests) {
            Board board = Board.from(createBoardRequest);
            boards.add(board);
        }
        boardRepository.saveAll(boards);

        return null;
    }

    @Transactional(readOnly = true)
    public List<ReadAllBoardResponse> readAllBoard() {
        List<Board> boards = boardRepository.findAll();

        return boards.stream()
                .map(Board::toReadAllResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReadBoardResponse readBoard(Long boardNo) {
        Board board = boardRepository.getReferenceById(boardNo);

        return board.toReadResponse();
    }
}
