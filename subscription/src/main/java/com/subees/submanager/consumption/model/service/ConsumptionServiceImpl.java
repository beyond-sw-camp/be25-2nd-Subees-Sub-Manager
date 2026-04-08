package com.subees.submanager.consumption.model.service;

import com.subees.submanager.consumption.model.dto.CalendarResponseDto;
import com.subees.submanager.consumption.model.dto.CalendarResultDto;
import com.subees.submanager.consumption.model.dto.CategoryAnalysisResponseDto;
import com.subees.submanager.consumption.model.dto.CategoryAnalysisResultDto;
import com.subees.submanager.consumption.model.dto.CategoryResponseDto;
import com.subees.submanager.consumption.model.dto.DateDetailResponseDto;
import com.subees.submanager.consumption.model.mapper.ConsumptionMapper;
import com.subees.submanager.consumption.model.vo.CategorySummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionServiceImpl implements ConsumptionService {

    private final ConsumptionMapper consumptionMapper;

    @Override
    public CalendarResultDto getCalendarSummary(Long userId, int year, int month) {

        if (year <= 0) {
            throw new IllegalArgumentException("년도는 1 이상이어야 합니다.");
        }

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("month는 1부터 12 사이여야 합니다.");
        }

        LocalDate monthStart = LocalDate.of(year, month, 1);
        LocalDate nextMonthStart = monthStart.plusMonths(1);

        Integer monthTotalAmount = consumptionMapper.selectMonthlyTotalAmount(
                userId,
                monthStart.toString(),
                nextMonthStart.toString()
        );

        List<CalendarResponseDto> items = consumptionMapper.selectCalendar(
                userId,
                monthStart.toString(),
                nextMonthStart.toString()
        );

        return new CalendarResultDto(year, month, monthTotalAmount, items);
    }

    @Override
    public List<DateDetailResponseDto> getDetail(Long userId, int year, int month, Integer date) {

        if (year <= 0) {
            throw new IllegalArgumentException("년도는 1 이상이어야 합니다.");
        }

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("month는 1부터 12 사이여야 합니다.");
        }

        if (date == null) {
            throw new IllegalArgumentException("date는 필수입니다.");
        }

        YearMonth yearMonth = YearMonth.of(year, month);
        int lastDay = yearMonth.lengthOfMonth();

        if (date < 1 || date > lastDay) {
            throw new IllegalArgumentException("해당 월의 유효한 날짜를 입력해야 합니다.");
        }

        LocalDate monthStart = LocalDate.of(year, month, 1);
        LocalDate nextMonthStart = monthStart.plusMonths(1);

        return consumptionMapper.selectDateDetailList(
                userId,
                monthStart.toString(),
                nextMonthStart.toString(),
                date
        );
    }

    @Override
    public List<CategoryResponseDto> getCategory(Long userId, int year, int month) {
        LocalDate monthStart = LocalDate.of(year, month, 1);
        LocalDate nextMonthStart = monthStart.plusMonths(1);

        List<CategorySummary> category = consumptionMapper.selectCategory(
                userId,
                monthStart.toString(),
                nextMonthStart.toString()
        );

        return category.stream()
                .map(vo -> new CategoryResponseDto(
                        year,
                        month,
                        vo.getCategoryName(),
                        vo.getTotalAmount(),
                        vo.getItemNames()
                ))
                .toList();
    }

    @Override
    public CategoryAnalysisResultDto getCategoryAnalysis(Long userId, int year, Integer month, String rangeType) {

        if (year <= 0) {
            throw new IllegalArgumentException("년도는 1 이상이어야 합니다.");
        }

        if (rangeType == null || rangeType.isBlank()) {
            throw new IllegalArgumentException("조회 범위는 필수입니다.");
        }

        if (!"MONTH".equals(rangeType) && !"YEAR".equals(rangeType)) {
            throw new IllegalArgumentException("rangeType은 MONTH 또는 YEAR만 가능합니다.");
        }

        if ("MONTH".equals(rangeType)) {
            if (month == null) {
                throw new IllegalArgumentException("월간 조회는 month가 필수입니다.");
            }

            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("month는 1부터 12 사이여야 합니다.");
            }
        }

        List<CategoryAnalysisResponseDto> categories;

        if ("MONTH".equals(rangeType)) {
            LocalDate monthStart = LocalDate.of(year, month, 1);
            LocalDate nextMonthStart = monthStart.plusMonths(1);

            categories = consumptionMapper.selectMonthlyCategoryAnalysis(
                    userId,
                    monthStart.toString(),
                    nextMonthStart.toString()
            );
        } else {
            LocalDate yearStart = LocalDate.of(year, 1, 1);
            LocalDate nextYearStart = yearStart.plusYears(1);

            categories = consumptionMapper.selectYearlyCategoryAnalysis(
                    userId,
                    yearStart.toString(),
                    nextYearStart.toString()
            );
        }

        int totalAmount = categories.stream()
                .mapToInt(category -> category.getTotalAmount() == null ? 0 : category.getTotalAmount())
                .sum();

        for (CategoryAnalysisResponseDto category : categories) {
            double ratio = totalAmount == 0
                    ? 0.0
                    : (category.getTotalAmount() * 100.0 / totalAmount);

            category.setRatio(Math.round(ratio * 10) / 10.0);
        }

        return new CategoryAnalysisResultDto(year, month, rangeType, totalAmount, categories);
    }
}