package com.subees.submanager.recommend.service;

import com.subees.submanager.recommend.dto.RecommendDeleteResponse;
import com.subees.submanager.recommend.dto.RecommendDetailResponse;
import com.subees.submanager.recommend.dto.RecommendGenerateRequest;
import com.subees.submanager.recommend.dto.RecommendGenerateResponse;
import com.subees.submanager.recommend.dto.RecommendListResponse;
import com.subees.submanager.recommend.dto.RecommendSaveRequest;
import com.subees.submanager.recommend.dto.RecommendSaveResponse;
import com.subees.submanager.recommend.dto.RecommendSubmitRequest;
import com.subees.submanager.recommend.dto.RecommendSubmitResponse;

public interface RecommendService {

    RecommendSubmitResponse submit(Long userId, RecommendSubmitRequest request);

    RecommendGenerateResponse generate(Long userId, RecommendGenerateRequest request);

    RecommendSaveResponse save(Long userId, RecommendSaveRequest request);

    RecommendListResponse getSavedReports(Long userId);

    RecommendDetailResponse getReportDetail(Long userId, Long reportId);

    RecommendDeleteResponse delete(Long userId, Long reportId);
}
