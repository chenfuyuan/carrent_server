package com.cfy.android.carrent.controller;

import com.cfy.android.carrent.model.Car;
import com.cfy.android.carrent.model.SubCar;
import com.cfy.android.carrent.model.User;
import com.cfy.android.carrent.service.CarService;
import com.cfy.android.carrent.service.IndexService;
import com.cfy.android.carrent.service.vo.ResponseBean;
import com.cfy.android.carrent.service.vo.SubCarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private IndexService indexService;
    @PostMapping("/saveCarMessage")
    @ResponseBody
    public ResponseBean saveCarMessage(@RequestBody Car car) {

        System.out.println("car = " + car);
        carService.saveCarMessage(car);
        return new ResponseBean(true,"发布信息成功");
    }

    @GetMapping("/getCarList")
    @ResponseBody
    public List<Car> getCarList() {
        List<Car> carList = carService.getCarList();
        return carList;
    }

    @PostMapping("/subCar")
    @ResponseBody
    public ResponseBean subCar(@RequestBody SubCarVo subCarVo) {
        System.out.println("subCar = " + subCarVo);
        User user = indexService.signInByToken(subCarVo.getToken());
        SubCar subCar = new SubCar();
        subCar.setUser(user);
        subCar.setPhone(subCarVo.getPhone());
        subCar.setSubDateTime(subCarVo.getSubDateTime());
        subCar.setCar(subCarVo.getCar());
        subCar.setState(0);

        carService.saveSubCarMessage(subCar);
        return new ResponseBean(true, "预约成功");
    }


    @GetMapping("/getAllSubCarList")
    @ResponseBody
    public List<SubCar> getAllSubCarList(@RequestParam int uid) {
        return carService.getAllSubCarByUid(uid);
    }


    @GetMapping("/endSubCar")
    @ResponseBody
    public ResponseBean endSubCar(@RequestParam int id) {
        carService.endSubCar(id);
        System.out.println(id);
        System.out.println("操作成功");
        return new ResponseBean(true,"结束成功");
    }

    @GetMapping("/getSubCarList")
    @ResponseBody
    public List<SubCar> getSubCarList(@RequestParam int uid) {
        return carService.getSubCarByUid(uid);
    }

    @GetMapping("/getEndSubCarList")
    @ResponseBody
    public List<SubCar> getEndSubCarList(@RequestParam int uid) {
        return carService.getEndSubCarByUid(uid);
    }


    @GetMapping("/getCheckCarList")
    @ResponseBody
    public List<Car> getCheckCarList() {
        List<Car> carList = carService.getCheckCarList();
        return carList;
    }

    @GetMapping("/pass")
    @ResponseBody
    public ResponseBean pass(@RequestParam int id) {
        carService.pass(id);
        System.out.println("审核的id为"+id);
        return new ResponseBean(true,"审核通过");
    }
}
