package com.subees.submanager.auth.service;

import com.subees.submanager.auth.dto.LoginRequest;
import com.subees.submanager.auth.dto.LoginResponse;
import com.subees.submanager.auth.dto.LogoutResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LogoutResponse logout(Long tokenUserId);
}
