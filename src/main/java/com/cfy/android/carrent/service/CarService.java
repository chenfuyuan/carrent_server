package com.cfy.android.carrent.service;

import com.cfy.android.carrent.model.Car;
import com.cfy.android.carrent.model.SubCar;

import java.util.List;

public interface CarService {

    void saveCarMessage(Car car);

    List<Car> getCarList();

    void saveSubCarMessage(SubCar subCar);

    List<SubCar> getAllSubCarByUid(int uid);

    List<SubCar> getSubCarByUid(int uid);

    List<SubCar> getEndSubCarByUid(int uid);
    void endSubCar(int id);

    List<Car> getCheckCarList();

    void pass(int id);
}
