package com.cfy.android.carrent.service.vo;


import com.cfy.android.carrent.model.Car;
import lombok.Data;

@Data
public class SubCarVo {
    private String phone;
    private String subDateTime;
    private String token;
    private Car car;
}
