package com.exception.exceptionstudy.domain;

import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.response.CreateBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadAllBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadBoardResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Entity
@Table
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Comment("보드 번호")
    private Long boardNo;

    @Column
    @Comment("제목")
    private String title;

    @Column
    @Comment("내용")
    private String body;

    public static Board from(CreateBoardRequest createBoardRequest) {
        return createBoardRequest.toEntity();
    }

    public CreateBoardResponse toCreateResponse() {
        return CreateBoardResponse.builder()
                .boardNo(this.boardNo)
                .build();
    }

    public ReadAllBoardResponse toReadAllResponse() {
        return ReadAllBoardResponse.builder()
                .boardNo(this.boardNo)
                .title(this.title)
                .build();
    }

    public ReadBoardResponse toReadResponse() {
        return ReadBoardResponse.builder()
                .boardNo(this.boardNo)
                .body(this.body)
                .title(this.title)
                .build();
    }
}
