package com.subees.subscription.auth.service;

import com.subees.subscription.auth.dto.LoginRequest;
import com.subees.subscription.auth.dto.LoginResponse;
import com.subees.subscription.auth.dto.LogoutResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LogoutResponse logout(Long tokenUserId);
}
