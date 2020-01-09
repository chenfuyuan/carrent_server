package com.cfy.android.carrent.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("subCar")
public class SubCar {
    private int id;
    private User user;
    private Car car;
    private String subDateTime;
    private String phone;
    private int state;
}