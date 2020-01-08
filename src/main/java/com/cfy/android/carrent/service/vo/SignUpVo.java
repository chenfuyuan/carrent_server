package com.cfy.android.carrent.service.vo;

import lombok.Data;

@Data
public class SignUpVo {
    private String name;
    private String password;
    private String authCode;
    private String phone;
    private String imagePath;
}
