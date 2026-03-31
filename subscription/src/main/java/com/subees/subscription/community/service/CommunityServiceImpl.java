package com.subees.subscription.community.service;

import com.subees.subscription.community.model.dto.CommunityPostDetailResponseDto;
import com.subees.subscription.community.model.dto.CommunityPostListResponseDto;
import com.subees.subscription.community.model.dto.PageRequestDto;
import com.subees.subscription.community.model.mapper.CommunityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityMapper communityMapper;

    @Override
    public List<CommunityPostListResponseDto> getCommunityPostList(PageRequestDto pageRequestDto) {
        return communityMapper.selectCommunityPostList(pageRequestDto);
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
}