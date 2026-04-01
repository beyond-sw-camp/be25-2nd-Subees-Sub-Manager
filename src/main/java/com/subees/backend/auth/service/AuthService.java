package com.subees.backend.auth.service;

import com.subees.backend.auth.dto.LoginRequest;
import com.subees.backend.auth.dto.LoginResponse;
import com.subees.backend.auth.dto.LogoutResponse;
import com.subees.backend.auth.jwt.JwtTokenProvider;
import com.subees.backend.user.entity.User;
import com.subees.backend.user.entity.UserState;
import com.subees.backend.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest request) {
        User user = userMapper.findByEmail(request.getEmail());

        if (user == null) {
            throw new IllegalArgumentException("가입되지 않은 이메일입니다.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (user.getUserState() == UserState.INACTIVE) {
            throw new IllegalStateException("탈퇴한 계정입니다.");
        }

        if (user.getUserState() != UserState.ACTIVE) {
            throw new IllegalStateException("비활성화된 계정입니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getNickname());

        return new LoginResponse(
                "Bearer",
                accessToken,
                user.getUserId(),
                user.getNickname()
        );
    }

    public LogoutResponse logout(Long tokenUserId) {
        return new LogoutResponse(tokenUserId, "로그아웃이 완료되었습니다.");
    }
}