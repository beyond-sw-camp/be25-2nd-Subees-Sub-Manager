package com.subees.subscription.community.service;

import com.subees.subscription.community.model.dto.CommunityPostDetailResponseDto;
import com.subees.subscription.community.model.dto.CommunityPostListResponseDto;
import com.subees.subscription.community.model.dto.PageRequestDto;
import java.util.List;

public interface CommunityService {

    // 게시글 목록 조회 (페이징)
    List<CommunityPostListResponseDto> getCommunityPostList(PageRequestDto pageRequestDto);

    // 전체 게시글 수
    int getCommunityPostCount();

    // 게시글 상세 조회 (조회수 +1 포함)
    CommunityPostDetailResponseDto getCommunityPostDetail(long postId);
}