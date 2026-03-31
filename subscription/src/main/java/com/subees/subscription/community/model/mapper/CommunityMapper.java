package com.subees.subscription.community.model.mapper;

import com.subees.subscription.community.model.dto.CommunityPostDetailResponseDto;
import com.subees.subscription.community.model.dto.CommunityPostListResponseDto;
import com.subees.subscription.community.model.dto.PageRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// java code와 sql문을 연결하는 역할
@Mapper
public interface CommunityMapper {

    List<CommunityPostListResponseDto> selectCommunityPostList(PageRequestDto pageRequestDto); // 게시글 목록 조회 (페이징)

    int selectCommunityPostCount(); // 전체 게시글 수 조회

    CommunityPostDetailResponseDto selectCommunityPostDetail(@Param("postId") long postId); // 게시글 상세 조회

    void updateViewCount(@Param("postId") long postId); // 조회수 +1
}