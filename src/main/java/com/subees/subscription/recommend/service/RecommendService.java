package com.subees.subscription.recommend.service;

import com.subees.subscription.recommend.dto.RecommendDeleteResponse;
import com.subees.subscription.recommend.dto.RecommendDetailResponse;
import com.subees.subscription.recommend.dto.RecommendGenerateRequest;
import com.subees.subscription.recommend.dto.RecommendGenerateResponse;
import com.subees.subscription.recommend.dto.RecommendListResponse;
import com.subees.subscription.recommend.dto.RecommendSaveRequest;
import com.subees.subscription.recommend.dto.RecommendSaveResponse;
import com.subees.subscription.recommend.dto.RecommendSubmitRequest;
import com.subees.subscription.recommend.dto.RecommendSubmitResponse;

public interface RecommendService {

    RecommendSubmitResponse submit(Long userId, RecommendSubmitRequest request);

    RecommendGenerateResponse generate(Long userId, RecommendGenerateRequest request);

    RecommendSaveResponse save(Long userId, RecommendSaveRequest request);

    RecommendListResponse getSavedReports(Long userId);

    RecommendDetailResponse getReportDetail(Long userId, Long reportId);

    RecommendDeleteResponse delete(Long userId, Long reportId);
}
