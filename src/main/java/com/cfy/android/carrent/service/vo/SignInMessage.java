package com.cfy.android.carrent.service.vo;

import com.cfy.android.carrent.model.User;
import lombok.Data;

@Data
public class SignInMessage {
    private String message;
    private boolean success;
    private boolean isRememberPassword;
    private User user;
}
