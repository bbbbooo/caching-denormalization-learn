package com.exception.exceptionstudy.controller;

import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.request.UpdateBoardRequest;
import com.exception.exceptionstudy.dto.response.CreateBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadAllBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadBoardResponse;
import com.exception.exceptionstudy.dto.response.UpdateBoardResponse;
import com.exception.exceptionstudy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<CreateBoardResponse> create(@RequestBody CreateBoardRequest createBoardRequest) {
        return ResponseEntity.ok(boardService.createBoard(createBoardRequest));
    }

    @GetMapping
    public ResponseEntity<Page<ReadAllBoardResponse>> readAll(@PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(boardService.readAllBoard(pageable));
    }

    @GetMapping("{no}")
    public ResponseEntity<ReadBoardResponse> read(@PathVariable("no") Long boardNo) {
        return ResponseEntity.ok(boardService.readBoard(boardNo));
    }

    @PutMapping("{no}")
    public ResponseEntity<UpdateBoardResponse> update(@PathVariable("no") Long boardNo, @RequestBody UpdateBoardRequest updateBoardRequest) {
        return ResponseEntity.ok(boardService.updateBoard(boardNo, updateBoardRequest));
    }
}
