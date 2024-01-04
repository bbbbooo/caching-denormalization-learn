package com.exception.exceptionstudy.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateBoardRequest {
    private final String body;
    private final String title;
}
