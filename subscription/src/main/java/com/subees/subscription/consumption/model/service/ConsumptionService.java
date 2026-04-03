package com.subees.subscription.consumption.model.service;

import com.subees.subscription.consumption.model.dto.CalendarSummaryResultDto;
import com.subees.subscription.consumption.model.dto.DateDetailListResponseDto;

public interface ConsumptionService {

    CalendarSummaryResultDto getCalendarSummary(Long userId, int year, int month);

    DateDetailListResponseDto getDetail(Long userId, int year, int month, Integer date);
}
