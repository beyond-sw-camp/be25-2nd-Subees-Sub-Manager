package com.subees.backend.user.service;

import com.subees.backend.user.dto.*;
import com.subees.backend.user.entity.User;
import com.subees.backend.user.entity.UserState;
import com.subees.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.upload.dir}")
    private String uploadDir;
    @Value("${app.profile.default-image-url}")
    private String defaultProfileImageUrl;

    private static final Set<String> ALLOWED_IMAGE_EXTENSIONS =
            Set.of(".jpg", ".jpeg", ".png", ".gif");

    @Transactional
    public void signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        if (userRepository.existsByNickname(request.getNickname())) {
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

        userRepository.save(user);
    }

    public CheckEmailResponse checkEmail(String email) {
        boolean exists = userRepository.existsByEmail(email);
        return new CheckEmailResponse(!exists,
                exists ? "이미 사용 중인 이메일입니다." : "사용 가능한 이메일입니다.");
    }

    public CheckNicknameResponse checkNickname(String nickname) {
        boolean exists = userRepository.existsByNickname(nickname);
        return new CheckNicknameResponse(!exists,
                exists ? "이미 사용 중인 닉네임입니다." : "사용 가능한 닉네임입니다.");
    }

    public MyInfoResponse getMyInfo(Long pathUserId, Long tokenUserId) {
        if (!pathUserId.equals(tokenUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 정보만 조회할 수 있습니다.");
        }

        User user = userRepository.findById(pathUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        return MyInfoResponse.from(user);
    }

    @Transactional
    public UpdateProfileResponse updateProfile(Long pathUserId, Long tokenUserId, UpdateProfileRequest request) {
        if (!pathUserId.equals(tokenUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 정보만 수정할 수 있습니다.");
        }

        User user = userRepository.findById(pathUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        if (!user.getNickname().equals(request.getNickname())
                && userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        user.updateNickname(request.getNickname());

        return new UpdateProfileResponse(
                user.getUserId(),
                user.getEmail(),
                user.getNickname(),
                "프로필이 수정되었습니다."
        );
    }
    @Transactional
    public ChangePasswordResponse changePassword(Long pathUserId, Long tokenUserId, ChangePasswordRequest request) {
        if (!pathUserId.equals(tokenUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 비밀번호만 변경할 수 있습니다.");
        }

        User user = userRepository.findById(pathUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new IllegalArgumentException("새 비밀번호는 현재 비밀번호와 달라야 합니다.");
        }

        user.updatePassword(passwordEncoder.encode(request.getNewPassword()));

        return new ChangePasswordResponse(user.getUserId(), "비밀번호가 변경되었습니다.");
    }
    @Transactional
    public WithdrawResponse withdraw(Long pathUserId, Long tokenUserId) {
        if (!pathUserId.equals(tokenUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 계정만 탈퇴할 수 있습니다.");
        }

        User user = userRepository.findById(pathUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        if (user.getUserState() == UserState.INACTIVE) {
            throw new IllegalArgumentException("이미 탈퇴 처리된 계정입니다.");
        }

        user.updateUserState(UserState.INACTIVE);

        return new WithdrawResponse(
                user.getUserId(),
                user.getUserState().name(),
                "회원탈퇴가 완료되었습니다."
        );
    }
    public GetProfileImageResponse getProfileImage(Long pathUserId, Long tokenUserId) {
        if (!pathUserId.equals(tokenUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 프로필 이미지만 조회할 수 있습니다.");
        }

        User user = userRepository.findById(pathUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

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
        if (!pathUserId.equals(tokenUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 프로필 이미지만 수정할 수 있습니다.");
        }

        User user = userRepository.findById(pathUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        user.updateProfileImageUrl(request.getProfileImageUrl());

        return new UpdateProfileImageResponse(
                user.getUserId(),
                user.getProfileImageUrl()
        );
    }

    @Transactional
    public DeleteProfileImageResponse deleteProfileImage(Long pathUserId, Long tokenUserId) {
        if (!pathUserId.equals(tokenUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 프로필 이미지만 삭제할 수 있습니다.");
        }

        User user = userRepository.findById(pathUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        user.updateProfileImageUrl(defaultProfileImageUrl);

        return new DeleteProfileImageResponse(
                user.getUserId(),
                user.getProfileImageUrl(),
                "프로필 이미지가 기본 이미지로 변경되었습니다."
        );
    }
    @Transactional
    public UploadProfileImageResponse uploadProfileImageFile(Long pathUserId,
                                                             Long tokenUserId,
                                                             MultipartFile file) {
        if (!pathUserId.equals(tokenUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 프로필 이미지만 수정할 수 있습니다.");
        }

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

        User user = userRepository.findById(pathUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            String savedFilename = UUID.randomUUID() + extension;
            Path targetPath = uploadPath.resolve(savedFilename);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            String profileImageUrl = "/uploads/" + savedFilename;
            user.updateProfileImageUrl(profileImageUrl);

            return new UploadProfileImageResponse(
                    user.getUserId(),
                    user.getProfileImageUrl(),
                    originalFilename
            );
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.");
        }
    }
}