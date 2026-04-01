package com.subees.backend.user.controller;

import com.subees.backend.global.response.ApiResponse;
import com.subees.backend.user.dto.*;
import com.subees.backend.user.service.UserService;
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
    public ResponseEntity<ApiResponse<Void>> signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(null, "회원가입이 완료되었습니다."));
    }

    @GetMapping("/check-email")
    public ResponseEntity<ApiResponse<CheckEmailResponse>> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(
                ApiResponse.success(userService.checkEmail(email))
        );
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<ApiResponse<CheckNicknameResponse>> checkNickname(@RequestParam String nickname) {
        return ResponseEntity.ok(
                ApiResponse.success(userService.checkNickname(nickname))
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<MyInfoResponse>> getMyInfo(@PathVariable Long userId,
                                                                 Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                ApiResponse.success(userService.getMyInfo(userId, tokenUserId))
        );
    }

    @PatchMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse<UpdateProfileResponse>> updateProfile(@PathVariable Long userId,
                                                                            @RequestBody @Valid UpdateProfileRequest request,
                                                                            Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                ApiResponse.success(userService.updateProfile(userId, tokenUserId, request))
        );
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<ApiResponse<ChangePasswordResponse>> changePassword(@PathVariable Long userId,
                                                                              @RequestBody @Valid ChangePasswordRequest request,
                                                                              Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                ApiResponse.success(userService.changePassword(userId, tokenUserId, request))
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<WithdrawResponse>> withdraw(@PathVariable Long userId,
                                                                  Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                ApiResponse.success(userService.withdraw(userId, tokenUserId))
        );
    }
    @GetMapping("/{userId}/profile-image")
    public ResponseEntity<ApiResponse<GetProfileImageResponse>> getProfileImage(@PathVariable Long userId,
                                                                                Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                ApiResponse.success(userService.getProfileImage(userId, tokenUserId))
        );
    }

    @PatchMapping("/{userId}/profile-image")
    public ResponseEntity<ApiResponse<UpdateProfileImageResponse>> updateProfileImage(@PathVariable Long userId,
                                                                                      @RequestBody @Valid UpdateProfileImageRequest request,
                                                                                      Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                ApiResponse.success(userService.updateProfileImage(userId, tokenUserId, request))
        );
    }
    @DeleteMapping("/{userId}/profile-image")
    public ResponseEntity<ApiResponse<DeleteProfileImageResponse>> deleteProfileImage(@PathVariable Long userId,
                                                                                      Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                ApiResponse.success(userService.deleteProfileImage(userId, tokenUserId))
        );
    }
    @PatchMapping("/{userId}/profile-image/file")
    public ResponseEntity<ApiResponse<UploadProfileImageResponse>> uploadProfileImageFile(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file,
            Authentication authentication
    ) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                ApiResponse.success(userService.uploadProfileImageFile(userId, tokenUserId, file))
        );
    }
}