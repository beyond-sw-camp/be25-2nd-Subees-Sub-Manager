package com.subees.subscription.consumption.model.service;

import com.subees.subscription.consumption.model.dto.CalendarSummaryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarSummaryResultDto {
    private Integer monthTotalAmount;
    private List<CalendarSummaryResponseDto> items;
}