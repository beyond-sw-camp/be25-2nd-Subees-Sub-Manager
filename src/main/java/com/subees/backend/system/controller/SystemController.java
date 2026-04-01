package com.subees.backend.system.controller;

import com.subees.backend.global.response.ApiResponse;
import com.subees.backend.system.dto.HealthCheckResponse;
import com.subees.backend.system.dto.SystemInfoResponse;
import com.subees.backend.system.dto.SystemTimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1/system")
public class SystemController {

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<HealthCheckResponse>> health() {
        HealthCheckResponse response = new HealthCheckResponse(
                "UP",
                "SUBEES_BACK",
                ZonedDateTime.now().toString()
        );

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<SystemInfoResponse>> info() {
        SystemInfoResponse response = new SystemInfoResponse(
                "SUBEES_BACK",
                "v1",
                "local",
                System.getProperty("java.version")
        );

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/time")
    public ResponseEntity<ApiResponse<SystemTimeResponse>> time() {
        ZonedDateTime now = ZonedDateTime.now();

        SystemTimeResponse response = new SystemTimeResponse(
                now.toString(),
                now.getZone().getId(),
                now.toInstant().toEpochMilli()
        );

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}