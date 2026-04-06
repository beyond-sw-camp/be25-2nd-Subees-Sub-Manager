package com.subees.subscription.consumption.model.mapper;

import com.subees.subscription.consumption.model.dto.CalendarResponseDto;
import com.subees.subscription.consumption.model.dto.CategoryAnalysisResponseDto;
import com.subees.subscription.consumption.model.dto.DateDetailResponseDto;
import com.subees.subscription.consumption.model.vo.CategorySummary;
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

    List<CalendarResponseDto> selectCalendar(
            @Param("userId") Long userId,
            @Param("monthStart") String monthStart,
            @Param("nextMonthStart") String nextMonthStart
    );

    List<DateDetailResponseDto> selectDateDetailList(
            @Param("userId") Long userId,
            @Param("monthStart") String monthStart,
            @Param("nextMonthStart") String nextMonthStart,
            @Param("startDate") Integer startDate
    );

    List<CategorySummary> selectCategory(
            @Param("userId") Long userId,
            @Param("monthStart") String monthStart,
            @Param("nextMonthStart") String nextMonthStart
    );

    List<CategoryAnalysisResponseDto> selectMonthlyCategoryAnalysis(
            @Param("userId") Long userId,
            @Param("monthStart") String monthStart,
            @Param("nextMonthStart") String nextMonthStart
    );

    List<CategoryAnalysisResponseDto> selectYearlyCategoryAnalysis(
            @Param("userId") Long userId,
            @Param("yearStart") String yearStart,
            @Param("nextYearStart") String nextYearStart
    );
}
