package com.exception.exceptionstudy.domain;

import com.exception.exceptionstudy.dto.request.CreateBoardRequest;
import com.exception.exceptionstudy.dto.request.UpdateBoardRequest;
import com.exception.exceptionstudy.dto.response.CreateBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadAllBoardResponse;
import com.exception.exceptionstudy.dto.response.ReadBoardResponse;
import com.exception.exceptionstudy.dto.response.UpdateBoardResponse;
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

    @Column
    @Comment("좋아요 수")
    private Long likeCount;

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
                .heartCount(this.likeCount)
                .build();
    }

    public UpdateBoardResponse updateBoard(UpdateBoardRequest updateBoardRequest) {
        this.body = updateBoardRequest.getBody();
        this.title = updateBoardRequest.getTitle();

        return UpdateBoardResponse.builder()
                .boardId(this.boardNo)
                .body(this.body)
                .title(this.title)
                .build();
    }

    public ReadAllBoardResponse of() {
        return ReadAllBoardResponse.builder()
                .boardNo(this.boardNo)
                .title(this.title)
                .build();
    }

    public void decreaseHeart() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }

    public void increaseHeart() {
        this.likeCount++;
    }
}
