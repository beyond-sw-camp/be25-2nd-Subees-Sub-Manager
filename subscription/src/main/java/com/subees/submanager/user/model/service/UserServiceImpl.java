package com.subees.submanager.user.model.service;

import com.subees.submanager.common.exception.UniversityException;
import com.subees.submanager.common.exception.message.ExceptionMessage;
import com.subees.submanager.user.model.dto.*;
import com.subees.submanager.user.model.dto.ChangePasswordRequest;
import com.subees.submanager.user.model.dto.ChangePasswordResponse;
import com.subees.submanager.user.model.dto.CheckEmailResponse;
import com.subees.submanager.user.model.dto.CheckNicknameResponse;
import com.subees.submanager.user.model.dto.DeleteProfileImageResponse;
import com.subees.submanager.user.model.dto.GetProfileImageResponse;
import com.subees.submanager.user.model.dto.MyInfoResponse;
import com.subees.submanager.user.model.dto.SignUpRequest;
import com.subees.submanager.user.model.dto.UpdateProfileImageRequest;
import com.subees.submanager.user.model.dto.UpdateProfileImageResponse;
import com.subees.submanager.user.model.dto.UpdateProfileRequest;
import com.subees.submanager.user.model.dto.UpdateProfileResponse;
import com.subees.submanager.user.model.dto.UploadProfileImageResponse;
import com.subees.submanager.user.model.dto.WithdrawResponse;
import com.subees.submanager.user.model.vo.User;
import com.subees.submanager.user.model.vo.UserState;
import com.subees.submanager.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
public class UserServiceImpl implements UserService {

    private static final Set<String> ALLOWED_IMAGE_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".gif");

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.profile.default-image-url}")
    private String defaultProfileImageUrl;

    @Override
    @Transactional
    public void signUp(SignUpRequest request) {
        if (userMapper.countByEmail(request.getEmail()) > 0) {
            throw new UniversityException(ExceptionMessage.DUPLICATE_EMAIL);
        }
        if (userMapper.countByNickname(request.getNickname()) > 0) {
            throw new UniversityException(ExceptionMessage.DUPLICATE_NICKNAME);
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

    @Override
    public CheckEmailResponse checkEmail(String email) {
        boolean exists = userMapper.countByEmail(email) > 0;
        return new CheckEmailResponse(!exists, exists ? "이미 사용 중인 이메일입니다." : "사용 가능한 이메일입니다.");
    }

    @Override
    public CheckNicknameResponse checkNickname(String nickname) {
        boolean exists = userMapper.countByNickname(nickname) > 0;
        return new CheckNicknameResponse(!exists, exists ? "이미 사용 중인 닉네임입니다." : "사용 가능한 닉네임입니다.");
    }

    @Override
    public MyInfoResponse getMyInfo(Long pathUserId, Long tokenUserId) {
        validateSelf(pathUserId, tokenUserId, "본인 정보만 조회할 수 있습니다.");
        return MyInfoResponse.from(findUserOrThrow(pathUserId));
    }

    @Override
    @Transactional
    public UpdateProfileResponse updateProfile(Long pathUserId, Long tokenUserId, UpdateProfileRequest request) {
        validateSelf(pathUserId, tokenUserId, "본인 정보만 수정할 수 있습니다.");
        User user = findUserOrThrow(pathUserId);

        if (user.getNickname().equals(request.getNickname())) {
            throw new UniversityException(ExceptionMessage.NO_PROFILE_CHANGES);
        }

        if (userMapper.countByNickname(request.getNickname()) > 0) {
            throw new UniversityException(ExceptionMessage.DUPLICATE_NICKNAME);
        }

        userMapper.updateNickname(pathUserId, request.getNickname());
        return new UpdateProfileResponse(user.getUserId(), user.getEmail(), request.getNickname());
    }
    @Override
    @Transactional
    public ChangePasswordResponse changePassword(Long pathUserId, Long tokenUserId, ChangePasswordRequest request) {
        validateSelf(pathUserId, tokenUserId, "본인 비밀번호만 변경할 수 있습니다.");
        User user = findUserOrThrow(pathUserId);

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new UniversityException(ExceptionMessage.INVALID_CURRENT_PASSWORD);
        }
        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new UniversityException(ExceptionMessage.SAME_AS_OLD_PASSWORD);
        }

        userMapper.updatePassword(pathUserId, passwordEncoder.encode(request.getNewPassword()));
        return new ChangePasswordResponse(user.getUserId(), "비밀번호가 변경되었습니다.");
    }

    @Override
    @Transactional
    public WithdrawResponse withdraw(Long pathUserId, Long tokenUserId) {
        validateSelf(pathUserId, tokenUserId, "본인 계정만 탈퇴할 수 있습니다.");
        User user = findUserOrThrow(pathUserId);

        if (user.getUserState() == UserState.INACTIVE) {
            throw new UniversityException(ExceptionMessage.ALREADY_WITHDRAWN_USER);
        }

        userMapper.updateUserState(pathUserId, UserState.INACTIVE);
        return new WithdrawResponse(user.getUserId(), UserState.INACTIVE.name(), "회원탈퇴가 완료되었습니다.");
    }

    @Override
    public GetProfileImageResponse getProfileImage(Long pathUserId, Long tokenUserId) {
        validateSelf(pathUserId, tokenUserId, "본인 프로필 이미지만 조회할 수 있습니다.");
        User user = findUserOrThrow(pathUserId);
        String profileImageUrl = user.getProfileImageUrl();
        if (profileImageUrl == null || profileImageUrl.isBlank()) {
            profileImageUrl = defaultProfileImageUrl;
        }
        return new GetProfileImageResponse(user.getUserId(), profileImageUrl);
    }

    @Override
    @Transactional
    public UpdateProfileImageResponse updateProfileImage(Long pathUserId, Long tokenUserId, UpdateProfileImageRequest request) {
        validateSelf(pathUserId, tokenUserId, "본인 프로필 이미지만 수정할 수 있습니다.");
        findUserOrThrow(pathUserId);
        userMapper.updateProfileImageUrl(pathUserId, request.getProfileImageUrl());
        return new UpdateProfileImageResponse(pathUserId, request.getProfileImageUrl());
    }

    @Override
    @Transactional
    public DeleteProfileImageResponse deleteProfileImage(Long pathUserId, Long tokenUserId) {
        validateSelf(pathUserId, tokenUserId, "본인 프로필 이미지만 삭제할 수 있습니다.");
        findUserOrThrow(pathUserId);
        userMapper.updateProfileImageUrl(pathUserId, defaultProfileImageUrl);
        return new DeleteProfileImageResponse(pathUserId, defaultProfileImageUrl, "프로필 이미지가 기본 이미지로 변경되었습니다.");
    }

    @Override
    @Transactional
    public UploadProfileImageResponse uploadProfileImageFile(Long pathUserId, Long tokenUserId, MultipartFile file) {
        validateSelf(pathUserId, tokenUserId, "본인 프로필 이미지만 수정할 수 있습니다.");
        if (file == null || file.isEmpty()) {
            throw new UniversityException(ExceptionMessage.EMPTY_UPLOAD_FILE);
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new UniversityException(ExceptionMessage.INVALID_IMAGE_FILE);
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new UniversityException(ExceptionMessage.INVALID_FILE_EXTENSION);
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf('.')).toLowerCase();
        if (!ALLOWED_IMAGE_EXTENSIONS.contains(extension)) {
            throw new UniversityException(ExceptionMessage.INVALID_FILE_EXTENSION);
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
            return new UploadProfileImageResponse(pathUserId, profileImageUrl, originalFilename);
        } catch (IOException e) {
            throw new UniversityException(ExceptionMessage.FILE_UPLOAD_FAILED);
        }
    }

    private void validateSelf(Long pathUserId, Long tokenUserId, String message) {
        if (!pathUserId.equals(tokenUserId)) {
            throw new UniversityException("FORBIDDEN", message, HttpStatus.FORBIDDEN);
        }
    }

    private User findUserOrThrow(Long userId) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new UniversityException(ExceptionMessage.USER_NOT_FOUND);
        }
        return user;
    }
}
