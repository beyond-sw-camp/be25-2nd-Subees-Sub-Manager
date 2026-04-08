package com.subees.submanager.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subees.submanager.common.exception.dto.ApiErrorResponseDto;
import com.subees.submanager.common.exception.message.ExceptionMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                new ApiErrorResponseDto(
                        HttpServletResponse.SC_FORBIDDEN,
                        ExceptionMessage.FORBIDDEN.name(),
                        ExceptionMessage.FORBIDDEN.getMessage()
                )
        ));
    }
}
