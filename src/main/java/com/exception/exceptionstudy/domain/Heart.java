package com.exception.exceptionstudy.domain;

import com.exception.exceptionstudy.dto.response.PostLikeResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Heart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    @Comment("좋아요 번호")
    private Long heartNo;

    @Column
    @Comment("유저 번호")
    private Long userNo;

    @Column
    @Comment("게시글 번호")
    private Long boardNo;

    public static Heart createHeart(Long boardNo, Long userNo) {
        return Heart.builder()
                .userNo(userNo)
                .boardNo(boardNo)
                .build();
    }

    public PostLikeResponse toLikeResponse() {
        return PostLikeResponse.builder()
                .heartNo(heartNo)
                .build();
    }
}
