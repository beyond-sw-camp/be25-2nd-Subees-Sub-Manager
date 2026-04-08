package com.subees.submanager.auth.service;

import org.springframework.http.ResponseCookie;

public interface JwtCookieService {
    String ACCESS_TOKEN_COOKIE_NAME = "access_token";

    ResponseCookie createAccessTokenCookie(String accessToken);
    ResponseCookie clearAccessTokenCookie();
}
