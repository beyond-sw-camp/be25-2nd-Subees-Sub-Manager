package com.subees.subscription.consumption.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateDetailListResponseDto {
    private String itemName;

    private int billingDay;

    private int price;
}
