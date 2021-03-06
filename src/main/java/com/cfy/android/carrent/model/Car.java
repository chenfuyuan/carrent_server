package com.cfy.android.carrent.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("car")
public class Car {
    private int id;
    private String carType;
    private String carNumber;
    private User user;
    private int uid;
    private String startTime;
    private String endTime;
    private String imagePath;
    private int state;

}
