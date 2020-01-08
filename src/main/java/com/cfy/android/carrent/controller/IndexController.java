package com.cfy.android.carrent.controller;


import com.cfy.android.carrent.model.Car;
import com.cfy.android.carrent.model.User;
import com.cfy.android.carrent.service.IndexService;
import com.cfy.android.carrent.service.vo.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;


    @GetMapping("/checkToken")
    @ResponseBody
    public User index(HttpServletRequest request, @RequestParam String token) {
        User user = indexService.signInByToken(token);
        return user;
    }

    @GetMapping("/changeImagePath")
    @ResponseBody
    public ResponseBean changeImage(@RequestParam String id,@RequestParam String imagePath) {
        indexService.changeImage(id, imagePath);
        ResponseBean responseBean = new ResponseBean(true,"上传成功");
        return responseBean;
    }


}
