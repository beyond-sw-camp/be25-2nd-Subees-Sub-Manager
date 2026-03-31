package com.subees.subscription.consumption.service;

import com.subees.subscription.consumption.dto.CalendarSummaryResultDto;
import com.subees.subscription.consumption.dto.DateDetailListResponseDto;

public interface ConsumptionService {

    CalendarSummaryResultDto getCalendarSummary(Long userId, int year, int month);

    DateDetailListResponseDto getDetail(Long userId, int year, int month, Integer date);
}
