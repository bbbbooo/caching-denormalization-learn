package com.exception.exceptionstudy.controller;

import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.response.CreateBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadAllBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadBoardResponse;
import com.exception.exceptionstudy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<CreateBoardResponse> create(@RequestBody List<CreateBoardRequest> createBoardRequest) {
        return ResponseEntity.ok(boardService.createBoard(createBoardRequest));
    }

    @GetMapping
    public ResponseEntity<List<ReadAllBoardResponse>> readAll() {
        return ResponseEntity.ok(boardService.readAllBoard());
    }

    @GetMapping("{no}")
    public ResponseEntity<ReadBoardResponse> read(@PathVariable("no") Long boardNo) {
        return ResponseEntity.ok(boardService.readBoard(boardNo));
    }
}
