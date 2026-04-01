package com.subees.backend.user.service;

import com.subees.backend.user.dto.*;
import com.subees.backend.user.entity.User;
import com.subees.backend.user.entity.UserState;
import com.subees.backend.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.profile.default-image-url}")
    private String defaultProfileImageUrl;

    private static final Set<String> ALLOWED_IMAGE_EXTENSIONS =
            Set.of(".jpg", ".jpeg", ".png", ".gif");

    @Transactional
    public void signUp(SignUpRequest request) {
        if (userMapper.countByEmail(request.getEmail()) > 0) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        if (userMapper.countByNickname(request.getNickname()) > 0) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .userState(UserState.ACTIVE)
                .emailVerified("N")
                .emailVerifiedAt(null)
                .createdAt(LocalDateTime.now())
                .build();

        user.updateProfileImageUrl(defaultProfileImageUrl);

        userMapper.insertUser(user);
    }

    public CheckEmailResponse checkEmail(String email) {
        boolean exists = userMapper.countByEmail(email) > 0;
        return new CheckEmailResponse(
                !exists,
                exists ? "이미 사용 중인 이메일입니다." : "사용 가능한 이메일입니다."
        );
    }

    public CheckNicknameResponse checkNickname(String nickname) {
        boolean exists = userMapper.countByNickname(nickname) > 0;
        return new CheckNicknameResponse(
                !exists,
                exists ? "이미 사용 중인 닉네임입니다." : "사용 가능한 닉네임입니다."
        );
    }

    public MyInfoResponse getMyInfo(Long pathUserId, Long tokenUserId) {
        validateSelf(pathUserId, tokenUserId, "본인 정보만 조회할 수 있습니다.");

        User user = findUserOrThrow(pathUserId);

        return MyInfoResponse.from(user);
    }

    @Transactional
    public UpdateProfileResponse updateProfile(Long pathUserId, Long tokenUserId, UpdateProfileRequest request) {
        validateSelf(pathUserId, tokenUserId, "본인 정보만 수정할 수 있습니다.");

        User user = findUserOrThrow(pathUserId);

        if (!user.getNickname().equals(request.getNickname())
                && userMapper.countByNickname(request.getNickname()) > 0) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        userMapper.updateNickname(pathUserId, request.getNickname());

        return new UpdateProfileResponse(
                user.getUserId(),
                user.getEmail(),
                request.getNickname(),
                "프로필이 수정되었습니다."
        );
    }

    @Transactional
    public ChangePasswordResponse changePassword(Long pathUserId, Long tokenUserId, ChangePasswordRequest request) {
        validateSelf(pathUserId, tokenUserId, "본인 비밀번호만 변경할 수 있습니다.");

        User user = findUserOrThrow(pathUserId);

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new IllegalArgumentException("새 비밀번호는 현재 비밀번호와 달라야 합니다.");
        }

        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
        userMapper.updatePassword(pathUserId, encodedNewPassword);

        return new ChangePasswordResponse(user.getUserId(), "비밀번호가 변경되었습니다.");
    }

    @Transactional
    public WithdrawResponse withdraw(Long pathUserId, Long tokenUserId) {
        validateSelf(pathUserId, tokenUserId, "본인 계정만 탈퇴할 수 있습니다.");

        User user = findUserOrThrow(pathUserId);

        if (user.getUserState() == UserState.INACTIVE) {
            throw new IllegalArgumentException("이미 탈퇴 처리된 계정입니다.");
        }

        userMapper.updateUserState(pathUserId, UserState.INACTIVE);

        return new WithdrawResponse(
                user.getUserId(),
                UserState.INACTIVE.name(),
                "회원탈퇴가 완료되었습니다."
        );
    }

    public GetProfileImageResponse getProfileImage(Long pathUserId, Long tokenUserId) {
        validateSelf(pathUserId, tokenUserId, "본인 프로필 이미지만 조회할 수 있습니다.");

        User user = findUserOrThrow(pathUserId);

        String profileImageUrl = user.getProfileImageUrl();

        if (profileImageUrl == null || profileImageUrl.isBlank()) {
            profileImageUrl = defaultProfileImageUrl;
        }

        return new GetProfileImageResponse(
                user.getUserId(),
                profileImageUrl
        );
    }

    @Transactional
    public UpdateProfileImageResponse updateProfileImage(Long pathUserId,
                                                         Long tokenUserId,
                                                         UpdateProfileImageRequest request) {
        validateSelf(pathUserId, tokenUserId, "본인 프로필 이미지만 수정할 수 있습니다.");

        findUserOrThrow(pathUserId);
        userMapper.updateProfileImageUrl(pathUserId, request.getProfileImageUrl());

        return new UpdateProfileImageResponse(
                pathUserId,
                request.getProfileImageUrl()
        );
    }

    @Transactional
    public DeleteProfileImageResponse deleteProfileImage(Long pathUserId, Long tokenUserId) {
        validateSelf(pathUserId, tokenUserId, "본인 프로필 이미지만 삭제할 수 있습니다.");

        findUserOrThrow(pathUserId);
        userMapper.updateProfileImageUrl(pathUserId, defaultProfileImageUrl);

        return new DeleteProfileImageResponse(
                pathUserId,
                defaultProfileImageUrl,
                "프로필 이미지가 기본 이미지로 변경되었습니다."
        );
    }

    @Transactional
    public UploadProfileImageResponse uploadProfileImageFile(Long pathUserId,
                                                             Long tokenUserId,
                                                             MultipartFile file) {
        validateSelf(pathUserId, tokenUserId, "본인 프로필 이미지만 수정할 수 있습니다.");

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 없습니다.");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("이미지 파일만 업로드할 수 있습니다.");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new IllegalArgumentException("허용되지 않는 파일 확장자입니다.");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();

        if (!ALLOWED_IMAGE_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException("허용되지 않는 파일 확장자입니다.");
        }

        findUserOrThrow(pathUserId);

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            String savedFilename = UUID.randomUUID() + extension;
            Path targetPath = uploadPath.resolve(savedFilename);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            String profileImageUrl = "/uploads/" + savedFilename;
            userMapper.updateProfileImageUrl(pathUserId, profileImageUrl);

            return new UploadProfileImageResponse(
                    pathUserId,
                    profileImageUrl,
                    originalFilename
            );
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.");
        }
    }

    private void validateSelf(Long pathUserId, Long tokenUserId, String message) {
        if (!pathUserId.equals(tokenUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, message);
        }
    }

    private User findUserOrThrow(Long userId) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");
        }
        return user;
    }
}