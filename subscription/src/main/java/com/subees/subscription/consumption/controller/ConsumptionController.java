package com.subees.subscription.consumption.controller;

import com.subees.subscription.consumption.dto.DateDetailListResponseDto;
import com.subees.subscription.consumption.dto.CalendarSummaryResultDto;
import com.subees.subscription.consumption.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/consumption")
@RequiredArgsConstructor
public class ConsumptionController {

    private final ConsumptionService consumptionService;

    @GetMapping("calendar-summary")
    public  ResponseEntity<CalendarSummaryResultDto> getMonthlyTotalAmount(@RequestParam Long userId, @RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(consumptionService.getCalendarSummary(userId, year, month));
    }

    @GetMapping("/date-details")
    public DateDetailListResponseDto selectDateDetailList(@RequestParam Long userId, @RequestParam int year, @RequestParam int month, @RequestParam Integer date){

        return consumptionService.getDetail(userId, year, month, date);
    }





}
