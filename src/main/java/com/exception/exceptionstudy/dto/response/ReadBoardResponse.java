package com.exception.exceptionstudy.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadBoardResponse {
    private final Long boardNo;
    private final String title;
    private final String body;
}
