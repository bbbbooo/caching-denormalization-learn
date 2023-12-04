package com.exception.exceptionstudy.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateBoardResponse {
    private final Long boardNo;
}
