package com.exception.exceptionstudy.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateBoardResponse {
    private final Long boardId;
    private final String title;
    private final String body;
}
