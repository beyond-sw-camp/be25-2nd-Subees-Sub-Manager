package com.subees.subscription.community.controller;

import com.subees.subscription.community.model.dto.CommunityPostDetailResponseDto;
import com.subees.subscription.community.model.dto.CommunityPostListResponseDto;
import com.subees.subscription.community.model.dto.PageRequestDto;
import com.subees.subscription.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //json 반환
//@Controller // html 반환
@RequestMapping("/api/v1")
@RequiredArgsConstructor // final 필드가 붙은 필수 파라미터 생성자 생성
public class CommunityController {

    private final CommunityService communityService;

    // 게시글 목록 조회 - JSON 반환
    @GetMapping("/community/posts")
    public ResponseEntity<Map<String, Object>> getCommunityPostList(@RequestParam(defaultValue = "1") int page) {
        int size = 10; //한 페이지당 글 수 10개
        PageRequestDto pageRequestDto = new PageRequestDto(page, size);

        List<CommunityPostListResponseDto> posts = communityService.getCommunityPostList(pageRequestDto);
        int totalCount = communityService.getCommunityPostCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        Map<String, Object> response = new HashMap<>();
        response.put("posts", posts);
        response.put("page", page);
        response.put("size", size);
        response.put("totalCount", totalCount);
        response.put("totalPages", totalPages);

        return ResponseEntity.ok(response);
    }

    // 게시글 상세 조회 - JSON 반환
    @GetMapping("/community/posts/{postId}")
    public ResponseEntity<CommunityPostDetailResponseDto> getCommunityPostDetail(@PathVariable long postId) {
        CommunityPostDetailResponseDto post = communityService.getCommunityPostDetail(postId);
        return ResponseEntity.ok(post);
    }
    // 화면용
//    @GetMapping("/community/posts")
//    public ModelAndView getCommunityPostList(@RequestParam(defaultValue = "1") int page) {
//        int size = 10;
//        PageRequestDto pageRequestDto = new PageRequestDto(page, size);
//
//        List<CommunityPostListResponseDto> posts = communityService.getCommunityPostList(pageRequestDto);
//        int totalCount = communityService.getCommunityPostCount();
//        int totalPages = (int) Math.ceil((double) totalCount / size);
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("posts", posts);
//        mav.addObject("page", page);
//        mav.addObject("size", size);
//        mav.addObject("totalCount", totalCount);
//        mav.addObject("totalPages", totalPages);
//        mav.setViewName("community/communityList");
//        return mav;
//    }

// 화면용
//    //글 상세 조회
//    @GetMapping("/community/posts/{postId}")
//    public ModelAndView getCommunityPostDetail(@PathVariable long postId) {
//        CommunityPostDetailResponseDto post = communityService.getCommunityPostDetail(postId);
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("post", post);
//        mav.setViewName("community/communityDetail");
//        return mav;
//    }



}