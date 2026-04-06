package com.subees.subscription.consumption.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategorySummary {
    private String categoryName;
    private Integer totalAmount;
    private String itemNames;
}
