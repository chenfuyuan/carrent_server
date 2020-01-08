package com.cfy.android.carrent.controller;

import com.cfy.android.carrent.model.Car;
import com.cfy.android.carrent.service.vo.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarController {


    @PostMapping("/saveCarMessage")
    @ResponseBody
    public ResponseBean saveCarMessage(@RequestBody Car car) {
        System.out.println("car = " + car);
        return null;

    }
}
