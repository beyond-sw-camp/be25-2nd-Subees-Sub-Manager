package com.subees.subscription.recommend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendSubmitResponse {

    private Long reportId;
    private String reportTitle;
    private String reportStatus;
    private Integer itemCount;
    private Integer totalMonthlyPrice;
}
