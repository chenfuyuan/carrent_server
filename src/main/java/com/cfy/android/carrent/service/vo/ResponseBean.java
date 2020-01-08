package com.cfy.android.carrent.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseBean implements Serializable  {
    private boolean success;
    private String message;

}
