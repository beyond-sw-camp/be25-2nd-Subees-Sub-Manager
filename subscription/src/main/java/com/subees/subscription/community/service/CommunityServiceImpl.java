package com.subees.subscription.community.service;

import com.subees.subscription.common.exception.UniversityException;
import com.subees.subscription.common.exception.message.ExceptionMessage;
import com.subees.subscription.community.model.dto.CommunityPostCreateDto;
import com.subees.subscription.community.model.dto.CommunityPostDetailResponseDto;
import com.subees.subscription.community.model.dto.CommunityPostListResponseDto;
import com.subees.subscription.community.model.dto.CommunityPostPageRequestDto;
import com.subees.subscription.community.model.mapper.CommunityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityMapper communityMapper;

    //글 목록 조회
    @Override
    public List<CommunityPostListResponseDto> getCommunityPostList(CommunityPostPageRequestDto communityPostPageRequestDto) {
        return communityMapper.selectCommunityPostList(communityPostPageRequestDto);
    }

    @Override
    public int getCommunityPostCount() {
        return communityMapper.selectCommunityPostCount();
    }

    //글 상세 조회 + 예외처리
    @Override
    public CommunityPostDetailResponseDto getCommunityPostDetail(long postId) {
        communityMapper.updateViewCount(postId); // 조회수 +1 먼저 실행
        CommunityPostDetailResponseDto post = communityMapper.selectCommunityPostDetail(postId);
        if (post == null) {
            throw new UniversityException(ExceptionMessage.POST_NOT_FOUND);
        }
        return post;
    }

    //글 작성
    @Transactional
    @Override
    public int save(CommunityPostCreateDto communityPostCreateDto) {
        // 미로그인 체크
        if (communityPostCreateDto.getUserId() == null) {
            throw new UniversityException(ExceptionMessage.UNAUTHORIZED);
        }
        // title/content 유효성 체크
        if (communityPostCreateDto.getTitle() == null || communityPostCreateDto.getTitle().isBlank() ||
            communityPostCreateDto.getContent() == null || communityPostCreateDto.getContent().isBlank()) {
            throw new UniversityException(ExceptionMessage.INVALID_REQUEST);
        }
        return communityMapper.insertCommunityPost(communityPostCreateDto);
    }
}