package com.subees.subscription.consumption.model.service;

import com.subees.subscription.consumption.model.dto.DateDetailListResponseDto;
import com.subees.subscription.consumption.model.dto.CalendarSummaryResponseDto;
import com.subees.subscription.consumption.model.mapper.ConsumptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionServiceImpl implements ConsumptionService {
    private final ConsumptionMapper consumptionMapper;

    @Override
    public  CalendarSummaryResultDto getCalendarSummary(Long userId, int year, int month) {
        LocalDate monthStart = LocalDate.of(year, month, 1);
        LocalDate nextMonthStart = monthStart.plusMonths(1);

        Integer monthTotalAmount = consumptionMapper.selectMonthlyTotalAmount(
                userId,
                monthStart.toString(),
                nextMonthStart.toString()
        );

        List<CalendarSummaryResponseDto> items = consumptionMapper.selectCalendarSummary(
                userId,
                monthStart.toString(),
                nextMonthStart.toString()
        );

        return new CalendarSummaryResultDto(monthTotalAmount, items);
    }

    @Override
    public DateDetailListResponseDto getDateCount(Long userId, int year, int month, Integer date) {
        LocalDate monthStart = LocalDate.of(year, month, 1);
        LocalDate nextMonthStart = monthStart.plusMonths(1);

        return consumptionMapper.selectDateDetailList(
                userId,
                monthStart.toString(),
                nextMonthStart.toString(),
                date
        );
    }

}