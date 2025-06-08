package com.example.car_repair_shop.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageDto<T> {
    private final List<T> content;
    private final long totalElements;

    public PageDto(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    public PageDto(List<T> content) {
        this.content = content;
        this.totalElements = content.size();
    }
}
