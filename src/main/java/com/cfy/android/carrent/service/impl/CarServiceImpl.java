package com.cfy.android.carrent.service.impl;

import com.cfy.android.carrent.mapper.CarMapper;
import com.cfy.android.carrent.mapper.SubCarMapper;
import com.cfy.android.carrent.model.Car;
import com.cfy.android.carrent.model.SubCar;
import com.cfy.android.carrent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Autowired
    private SubCarMapper subCarMapper;

    @Override
    public void saveCarMessage(Car car) {
        carMapper.insertCar(car);
    }

    @Override
    public List<Car> getCarList() {
        return carMapper.getCarList();
    }

    @Override
    public void saveSubCarMessage(SubCar subCar) {
        subCarMapper.insertSubCarMessage(subCar);
        carMapper.updateState(subCar.getCar().getId());
    }

    @Override
    public List<SubCar> getAllSubCarByUid(int uid) {
        return subCarMapper.getAllSubCar(uid);
    }

    @Override
    public void endSubCar(int id) {
        subCarMapper.endSubCar(id);
    }

    @Override
    public List<SubCar> getSubCarByUid(int uid) {
        return subCarMapper.getSubCar(uid);
    }

    @Override
    public List<SubCar> getEndSubCarByUid(int uid) {
        return subCarMapper.getEndSubCar(uid);
    }

    @Override
    public List<Car> getCheckCarList() {
        return carMapper.getCheckCarList();
    }

    @Override
    public void pass(int id) {
        carMapper.pass(id);
    }
}
