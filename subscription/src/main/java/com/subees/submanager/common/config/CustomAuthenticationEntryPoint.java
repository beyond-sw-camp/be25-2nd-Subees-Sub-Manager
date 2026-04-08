package com.subees.submanager.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subees.submanager.common.exception.dto.ApiErrorResponseDto;
import com.subees.submanager.common.exception.message.ExceptionMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                new ApiErrorResponseDto(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        ExceptionMessage.UNAUTHORIZED.name(),
                        ExceptionMessage.UNAUTHORIZED.getMessage()
                )
        ));
    }
}
