package com.subees.backend.auth.controller;

import com.subees.backend.auth.dto.LoginRequest;
import com.subees.backend.auth.dto.LoginResponse;
import com.subees.backend.auth.dto.LogoutResponse;
import com.subees.backend.auth.service.AuthService;
import com.subees.backend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
        System.out.println("=== login controller entered ===");
        return ResponseEntity.ok(ApiResponse.success(authService.login(request), "로그인 성공"));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<LogoutResponse>> logout(Authentication authentication) {
        Long tokenUserId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(ApiResponse.success(authService.logout(tokenUserId), "로그아웃 성공"));
    }
}