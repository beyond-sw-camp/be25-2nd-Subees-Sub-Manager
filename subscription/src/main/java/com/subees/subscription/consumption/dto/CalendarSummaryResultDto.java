package com.subees.subscription.consumption.dto;

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