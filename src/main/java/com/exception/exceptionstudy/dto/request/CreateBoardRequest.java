package com.exception.exceptionstudy.dto.request;

import com.exception.exceptionstudy.domain.Board;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateBoardRequest {
    @NotNull
    private String title;
    private String body;

    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .body(this.body)
                .build();
    }
}
