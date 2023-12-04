package com.exception.exceptionstudy.domain;

import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.response.CreateBoardResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

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
}
