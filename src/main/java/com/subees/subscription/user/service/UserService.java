package com.subees.subscription.user.service;

import com.subees.subscription.user.dto.*;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void signUp(SignUpRequest request);
    CheckEmailResponse checkEmail(String email);
    CheckNicknameResponse checkNickname(String nickname);
    MyInfoResponse getMyInfo(Long pathUserId, Long tokenUserId);
    UpdateProfileResponse updateProfile(Long pathUserId, Long tokenUserId, UpdateProfileRequest request);
    ChangePasswordResponse changePassword(Long pathUserId, Long tokenUserId, ChangePasswordRequest request);
    WithdrawResponse withdraw(Long pathUserId, Long tokenUserId);
    GetProfileImageResponse getProfileImage(Long pathUserId, Long tokenUserId);
    UpdateProfileImageResponse updateProfileImage(Long pathUserId, Long tokenUserId, UpdateProfileImageRequest request);
    DeleteProfileImageResponse deleteProfileImage(Long pathUserId, Long tokenUserId);
    UploadProfileImageResponse uploadProfileImageFile(Long pathUserId, Long tokenUserId, MultipartFile file);
}
