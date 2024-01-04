package com.exception.exceptionstudy.service;

import com.exception.exceptionstudy.domain.Board;
import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.request.UpdateBoardRequest;
import com.exception.exceptionstudy.dto.response.CreateBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadAllBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadBoardResponse;
import com.exception.exceptionstudy.dto.response.UpdateBoardResponse;
import com.exception.exceptionstudy.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private static final int PAGE_NUMBER = 0;
    private static final String SORT_BY = "boardNo";

    @Transactional
    public CreateBoardResponse createBoard(CreateBoardRequest createBoardRequest) {
        Board board = boardRepository.save(Board.from(createBoardRequest));
        return board.toCreateResponse();
    }

    @Cacheable(key = "#pageable.pageNumber + '-' + #pageable.pageSize", value = "readAllCache")
    @Transactional(readOnly = true)
    public Page<ReadAllBoardResponse> readAllBoard(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(Board::toReadAllResponse);
    }

    @Cacheable(key = "#boardNo", value = "readOneCache")
    @Transactional(readOnly = true)
    public ReadBoardResponse readBoard(Long boardNo) {
        Board board = boardRepository.getReferenceById(boardNo);

        return board.toReadResponse();
    }

    @CacheEvict(key = "#boardNo", value = "readOneCache")
    @Transactional
    public UpdateBoardResponse updateBoard(Long boardNo, UpdateBoardRequest updateBoardRequest) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(EntityNotFoundException::new);

        return board.updateBoard(updateBoardRequest);
    }

    @Cacheable(key = "#lastBoardNo", value = "readAllCache")
    @Transactional(readOnly = true)
    public Slice<ReadAllBoardResponse> readAllBoardByNoOffset(Long lastBoardNo, int size) {
        Slice<Board> boards = findBoards(lastBoardNo, size);
        boolean hasNext = boards.getSize() == size + 1;

        List<Board> content = getContentByHasNext(boards, hasNext);
        List<ReadAllBoardResponse> responses = convertToResponse(content);

        return new SliceImpl<>(responses, boards.getPageable(), hasNext);
    }

    private List<ReadAllBoardResponse> convertToResponse(List<Board> content) {
        return content.stream()
                .map(Board::of)
                .collect(Collectors.toList());
    }

    private List<Board> getContentByHasNext(Slice<Board> boards, boolean hasNext) {
        List<Board> content = new ArrayList<>(boards.getContent());
        if (hasNext) {
            content.remove(content.size() - 1);
        }

        return content;
    }

    private Slice<Board> findBoards(Long lastBoardNo, int size) {
        Long boardNo = Optional.ofNullable(lastBoardNo).orElse(Long.MAX_VALUE);

        PageRequest pageRequest = PageRequest.of(PAGE_NUMBER, size + 1, Sort.by(Sort.Direction.DESC, SORT_BY));
        return boardRepository.findByBoardNoLessThan(boardNo, pageRequest);
    }
}
