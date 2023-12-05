package com.exception.exceptionstudy.service;

import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.response.ReadAllBoardResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    void readAllBoard() {
        // given
        List<CreateBoardRequest> createBoardRequests = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            createBoardRequests.add(CreateBoardRequest.builder()
                            .title("title" + i)
                            .body("body" + i)
                    .build());
        }
        boardService.createBoard(createBoardRequests);

        // when
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<ReadAllBoardResponse> readAllBoardResponses = boardService.readAllBoard();
        stopWatch.stop();

        // then
        System.out.println("총 조회 시간 : " + stopWatch.getTotalTimeSeconds());

        assertThat(readAllBoardResponses.size()).isEqualTo(100000);
    }
}