package com.subees.subscription.consumption.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarSummaryResponseDto {
    private int subscriptionId;

    private String itemName;

    private int price;

    private int payDay;

}
