package com.subees.subscription.consumption.controller;

import com.subees.subscription.common.model.dto.BaseResponseDto;
import com.subees.subscription.common.model.dto.ItemsResponseDto;
import com.subees.subscription.consumption.model.dto.CalendarResultDto;
import com.subees.subscription.consumption.model.dto.CategoryAnalysisResultDto;
import com.subees.subscription.consumption.model.dto.CategoryResponseDto;
import com.subees.subscription.consumption.model.dto.DateDetailResponseDto;
import com.subees.subscription.consumption.model.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumption")
@RequiredArgsConstructor
public class ConsumptionController {

    private final ConsumptionService consumptionService;

    @GetMapping("/calendar-summary")
    public ResponseEntity<BaseResponseDto<CalendarResultDto>> getCalendarSummary(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam int month) {

        CalendarResultDto result = consumptionService.getCalendarSummary(userId, year, month);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, result));
    }

    @GetMapping("/date-details")
    public ResponseEntity<ItemsResponseDto<DateDetailResponseDto>> getDateDetails(@RequestParam Long userId, @RequestParam int year,
                                                                                  @RequestParam int month,
                                                                                  @RequestParam Integer date) {

        List<DateDetailResponseDto> items = consumptionService.getDetail(userId, year, month, date);

        return ResponseEntity.ok(new ItemsResponseDto<>(HttpStatus.OK, items, 1, items.size()));
    }
    @GetMapping("/category-summary")
    public ResponseEntity<ItemsResponseDto<CategoryResponseDto>> getCategory(@RequestParam Long userId, @RequestParam int year, @RequestParam int month) {

        List<CategoryResponseDto> items = consumptionService.getCategory(userId, year, month);
        return ResponseEntity.ok(new ItemsResponseDto<>(HttpStatus.OK, items, 1, items.size()));
    }

    @GetMapping("/analysis/categories")
    public ResponseEntity<BaseResponseDto<CategoryAnalysisResultDto>> getCategoryAnalysis(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam(required = false) Integer month,
            @RequestParam String rangeType
    ) {
        CategoryAnalysisResultDto result = consumptionService.getCategoryAnalysis(userId, year, month, rangeType);
        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, result));
    }

}
