package com.subees.subscription.community.model.mapper;

import com.subees.subscription.community.model.dto.CommunityPostCreateDto;
import com.subees.subscription.community.model.dto.CommunityPostDetailResponseDto;
import com.subees.subscription.community.model.dto.CommunityPostListResponseDto;
import com.subees.subscription.community.model.dto.CommunityPostPageRequestDto;
import com.subees.subscription.community.model.dto.CommunityPostUpdateDto;
import com.subees.subscription.community.model.dto.CommunityPostUpdateResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// java code와 sql문을 연결하는 역할
@Mapper
public interface CommunityMapper {

    List<CommunityPostListResponseDto> selectCommunityPostList(CommunityPostPageRequestDto communityPostPageRequestDto); // 게시글 목록 조회 (페이징)

    int selectCommunityPostCount(); // 전체 게시글 수 조회

    CommunityPostDetailResponseDto selectCommunityPostDetail(@Param("postId") Long postId); // 게시글 상세 조회

    void updateViewCount(@Param("postId") Long postId); // 조회수 +1

    int insertCommunityPost(CommunityPostCreateDto communityPostCreateDto); //글 작성

    int updateCommunityPost(CommunityPostUpdateDto communityPostUpdateDto); //글 수정

    CommunityPostUpdateResponseDto selectUpdatedPost(@Param("postId") Long postId); //수정된 글 조회

    //수정 요청이 들어왔을 때, 해당 게시글의 작성자 user_id를 DB에서 가져오기 위해 추가
    Long selectPostOwnerUserId(@Param("postId") Long postId); //게시글 작성자 userId 조회

    int deleteCommunityPost(@Param("postId") Long postId); //게시글 삭제
}