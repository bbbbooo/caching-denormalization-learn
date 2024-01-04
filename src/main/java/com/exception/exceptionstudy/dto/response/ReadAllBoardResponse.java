package com.exception.exceptionstudy.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class ReadAllBoardResponse implements Serializable {
    private final Long boardNo;
    private final String title;
}
