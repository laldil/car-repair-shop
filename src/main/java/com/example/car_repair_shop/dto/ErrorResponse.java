package com.example.car_repair_shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {

    private String msg;
    private Date timestamp;

    public ErrorResponse(String msg) {
        this.msg = msg;
        this.timestamp = new Date();
    }
}