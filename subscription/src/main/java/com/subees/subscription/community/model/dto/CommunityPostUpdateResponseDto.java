package com.subees.subscription.community.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor

//게시글 수정 시 수정된 내용 반환
public class CommunityPostUpdateResponseDto {

    @JsonProperty("post_id")
    private Long postId;

    private String title;

    private String content;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}