package com.subees.subscription.community.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestDto {

    private int page;
    private int size;

    public PageRequestDto() {
        this.page = 1;
        this.size = 10;
    }

    public PageRequestDto(int page, int size) {
        this.page = (page < 1) ? 1 : page;
        this.size = size;
    }

    public int getOffset() {
        return (page - 1) * size;
    }
}