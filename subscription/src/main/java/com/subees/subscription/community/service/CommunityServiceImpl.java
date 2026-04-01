package com.subees.subscription.community.service;

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

    @Override
    public CommunityPostDetailResponseDto getCommunityPostDetail(long postId) {
        communityMapper.updateViewCount(postId); // 조회수 +1 먼저 실행
        return communityMapper.selectCommunityPostDetail(postId);
    }

    //글 작성
    @Transactional
    @Override
    public int save(CommunityPostCreateDto communityPostCreateDto) {
        return communityMapper.insertCommunityPost(communityPostCreateDto);
    }
}