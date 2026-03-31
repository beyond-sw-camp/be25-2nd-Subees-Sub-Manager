package com.subees.subscription.consumption.model.service;

import com.subees.subscription.consumption.model.dto.DateDetailListResponseDto;

public interface ConsumptionService {

    CalendarSummaryResultDto getCalendarSummary(Long userId, int year, int month);

    DateDetailListResponseDto getDateCount(Long userId, int year, int month, Integer date);
}
