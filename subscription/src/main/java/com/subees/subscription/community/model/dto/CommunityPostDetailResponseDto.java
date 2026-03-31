package com.subees.subscription.community.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommunityPostDetailResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String nickname;
    private int viewCount;
    private int scrapCount;
    private LocalDateTime createdAt;
}
