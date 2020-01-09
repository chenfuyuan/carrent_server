package com.cfy.android.carrent;

import com.cfy.android.carrent.mapper.CarMapper;
import com.cfy.android.carrent.mapper.SubCarMapper;
import com.cfy.android.carrent.model.Car;
import com.cfy.android.carrent.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CarrentApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CarMapper mapper;

    @Test
    public void insertCar() {
        Car car = new Car();
        User user = new User();
        user.setId(27);
        car.setCarNumber("123456");
        car.setCarType("丰田");
        car.setStartTime("2020-1-8 20:12:00");
        car.setEndTime("2020-2-8 20:13:00");
        car.setUser(user);
        car.setImagePath("saasfa.jpg");
        mapper.insertCar(car);
    }

    @Test
    public void getCarList() {
        List<Car> carList = mapper.getCarList();
        for (Car car : carList) {
            System.out.println(car);
        }
    }

    @Autowired
    private SubCarMapper subCarMapper;

    @Test
    public void getSubCarList() {
        System.out.println(subCarMapper.getAllSubCar(30));
    }

    @Test
    public void endSubCar() {
        subCarMapper.endSubCar(1);
    }
}
