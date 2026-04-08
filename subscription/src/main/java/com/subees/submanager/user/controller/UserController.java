package com.subees.submanager.user.controller;

import com.subees.submanager.common.model.dto.BaseResponseDto;
import com.subees.submanager.user.dto.*;
import com.subees.submanager.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<BaseResponseDto<String>> signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseDto<>(HttpStatus.CREATED, "회원가입이 완료되었습니다."));
    }

    @GetMapping("/check-email")
    public ResponseEntity<BaseResponseDto<CheckEmailResponse>> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.checkEmail(email)));
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<BaseResponseDto<CheckNicknameResponse>> checkNickname(@RequestParam String nickname) {
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.checkNickname(nickname)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponseDto<MyInfoResponse>> getMyInfo(@PathVariable Long userId,
                                                                     Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.getMyInfo(userId, tokenUserId)));
    }

    @PatchMapping("/{userId}/profile")
    public ResponseEntity<BaseResponseDto<UpdateProfileResponse>> updateProfile(@PathVariable Long userId,
                                                                                @RequestBody @Valid UpdateProfileRequest request,
                                                                                Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.updateProfile(userId, tokenUserId, request)));
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<BaseResponseDto<ChangePasswordResponse>> changePassword(@PathVariable Long userId,
                                                                                  @RequestBody @Valid ChangePasswordRequest request,
                                                                                  Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.changePassword(userId, tokenUserId, request)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<BaseResponseDto<WithdrawResponse>> withdraw(@PathVariable Long userId,
                                                                      Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.withdraw(userId, tokenUserId)));
    }

    @GetMapping("/{userId}/profile-image")
    public ResponseEntity<BaseResponseDto<GetProfileImageResponse>> getProfileImage(@PathVariable Long userId,
                                                                                    Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.getProfileImage(userId, tokenUserId)));
    }

    @PatchMapping("/{userId}/profile-image")
    public ResponseEntity<BaseResponseDto<UpdateProfileImageResponse>> updateProfileImage(@PathVariable Long userId,
                                                                                          @RequestBody @Valid UpdateProfileImageRequest request,
                                                                                          Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.updateProfileImage(userId, tokenUserId, request)));
    }

    @DeleteMapping("/{userId}/profile-image")
    public ResponseEntity<BaseResponseDto<DeleteProfileImageResponse>> deleteProfileImage(@PathVariable Long userId,
                                                                                          Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.deleteProfileImage(userId, tokenUserId)));
    }

    @PatchMapping("/{userId}/profile-image/file")
    public ResponseEntity<BaseResponseDto<UploadProfileImageResponse>> uploadProfileImageFile(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file,
            Authentication authentication
    ) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, userService.uploadProfileImageFile(userId, tokenUserId, file)));
    }
}
