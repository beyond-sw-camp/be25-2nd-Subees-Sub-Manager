package com.subees.submanager.community.controller;

import com.subees.submanager.common.exception.UniversityException;
import com.subees.submanager.common.exception.message.ExceptionMessage;
import com.subees.submanager.common.model.dto.BaseResponseDto;
import com.subees.submanager.community.model.dto.post.CommunityPostCreateDto;
import com.subees.submanager.community.model.dto.post.CommunityPostDetailResponseDto;
import com.subees.submanager.community.model.dto.post.CommunityPostListResponseDto;
import com.subees.submanager.community.model.dto.post.CommunityPostPageRequestDto;
import com.subees.submanager.community.model.dto.post.CommunityPostPageResponseDto;
import com.subees.submanager.community.model.dto.post.CommunityPostUpdateDto;
import com.subees.submanager.community.model.dto.post.CommunityPostUpdateResponseDto;
import com.subees.submanager.community.model.dto.scrap.CommunityScrapCreateDto;
import com.subees.submanager.community.model.dto.scrap.CommunityScrapPageResponseDto;
import com.subees.submanager.community.model.dto.scrap.CommunityScrapResponseDto;
import com.subees.submanager.community.model.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //json 반환
//@Controller // html 반환
@RequestMapping("/api/v1") //공통 prefix 적용
@RequiredArgsConstructor // final 필드가 붙은 필수 파라미터 생성자 생성
public class CommunityController {

    private final CommunityService communityService;

    // 게시글 목록 조회 - JSON 반환
    @GetMapping("/community/posts")
    public ResponseEntity<BaseResponseDto<CommunityPostPageResponseDto>> getCommunityPostList(@RequestParam(defaultValue = "1") int page) {

        int size = 10; //한 페이지당 글 수 10개

        //math.ceil 페이징 올림 처리 (25개 / 10 = 2.5 ->3 페이지)
        int totalCount = communityService.getCommunityPostCount();
        //totalPages = 몇 페이지로 나눌지
        int totalPages = (int) Math.ceil((double) totalCount / size);

        //page 예외처리 (1 미만 또는 존재하지 않는 페이지)
        if (page < 1 || (totalPages > 0 && page > totalPages)) {
            throw new UniversityException(ExceptionMessage.INVALID_PAGE);
        }

        CommunityPostPageRequestDto communityPostPageRequestDto = new CommunityPostPageRequestDto(page, size);
        List<CommunityPostListResponseDto> posts = communityService.getCommunityPostList(communityPostPageRequestDto);

        CommunityPostPageResponseDto responseDto = new CommunityPostPageResponseDto(
                posts,
                communityPostPageRequestDto.getPage(),
                communityPostPageRequestDto.getSize(),
                totalCount,
                totalPages
        );
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, responseDto));
    }

    // 게시글 상세 조회 - JSON 반환
    @GetMapping("/community/posts/{postId}")
    public ResponseEntity<BaseResponseDto<CommunityPostDetailResponseDto>> getCommunityPostDetail(@PathVariable long postId) {
        CommunityPostDetailResponseDto post = communityService.getCommunityPostDetail(postId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, post));
    }

    //게시글 작성
    @PostMapping("/community/posts")
    public ResponseEntity<BaseResponseDto<CommunityPostCreateDto>> postCommunityCreate(@RequestBody CommunityPostCreateDto communityPostCreateDto) {
        communityService.save(communityPostCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseDto<>(HttpStatus.CREATED, communityPostCreateDto));
    }

    //게시글 수정
    //@Valid가 있어야 dto에 붙인 @NotBlank가 동작함
    @PutMapping("/community/posts/{postId}")
    public ResponseEntity<BaseResponseDto<CommunityPostUpdateResponseDto>> postCommunityUpdate(@PathVariable long postId, @Valid @RequestBody CommunityPostUpdateDto communityPostUpdateDto) {
        communityPostUpdateDto.setPostId(postId); // URL의 postId를 DTO에 세팅
        CommunityPostUpdateResponseDto updatedPost = communityService.update(communityPostUpdateDto);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, updatedPost));
    }


    //게시글 삭제
    //primitive 타입은 null이 불가능해서
    //userId 없이 요청하면 오류남. Long으로 변경
    //RequestParam(required = true) spring 500 error
    //@RequestParam(required = false)  userId = null → 서비스에서 401 UNAUTHORIZED 반환
    @DeleteMapping("/community/posts/{postId}")
    public ResponseEntity<BaseResponseDto<Long>> postCommunityDelete(@PathVariable long postId, @RequestParam(required = false) Long userId) {
        communityService.delete(postId, userId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, postId));
    }

    // 게시글 스크랩
    @PostMapping("/community/posts/{postId}/scraps")
    public ResponseEntity<BaseResponseDto<CommunityScrapResponseDto>> postCommunityScrap(@PathVariable long postId, @RequestBody CommunityScrapCreateDto communityScrapCreateDto) {
        CommunityScrapResponseDto scrap = communityService.scrap(postId, communityScrapCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseDto<>(HttpStatus.CREATED, scrap));
    }

    // 스크랩 목록 조회
    @GetMapping("/community/scraps")
    public ResponseEntity<BaseResponseDto<CommunityScrapPageResponseDto>> getScrapList(@RequestParam(required = false) Long userId, @RequestParam(defaultValue = "1") int page) {
        CommunityScrapPageResponseDto response = communityService.getScrapList(userId, page);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, response));
    }

    // 스크랩 취소
    @DeleteMapping("/community/posts/{postId}/scraps")
    public ResponseEntity<BaseResponseDto<Long>> cancelScrap(@PathVariable long postId, @RequestParam(required = false) Long userId) {
        communityService.cancelScrap(postId, userId);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, postId));
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
