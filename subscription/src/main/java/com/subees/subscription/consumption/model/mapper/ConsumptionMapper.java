package com.subees.subscription.consumption.model.mapper;

import com.subees.subscription.consumption.model.dto.DateDetailListResponseDto;
import com.subees.subscription.consumption.model.dto.CalendarSummaryResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsumptionMapper {

    Integer selectMonthlyTotalAmount(
            @Param("userId") Long userId,
            @Param("monthStart") String monthStart,
            @Param("nextMonthStart") String nextMonthStart
    );

    List<CalendarSummaryResponseDto> selectCalendarSummary(
            @Param("userId") Long userId,
            @Param("monthStart") String monthStart,
            @Param("nextMonthStart") String nextMonthStart
    );

    DateDetailListResponseDto selectDateDetailList(
            @Param("userId") Long userId,
            @Param("monthStart") String monthStart,
            @Param("nextMonthStart") String nextMonthStart,
            @Param("startDate") Integer startDate
    );
}
