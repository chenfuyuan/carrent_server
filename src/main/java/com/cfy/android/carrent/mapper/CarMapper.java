package com.cfy.android.carrent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.android.carrent.model.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CarMapper extends BaseMapper<Car> {

    @Insert("insert into car (car_type,car_number,start_time,end_time,u_id,image_path,state) values (#{carType}," +
            "#{carNumber}," +
            "#{startTime},#{endTime},#{user.id},#{imagePath},0)")
    void insertCar(Car car);

    @Select("select * from car where state = 1")
    @Results(@Result(
            property = "user",
            column = "u_id",
            one = @One(select = "com.cfy.android.carrent.mapper.UserMapper.selectById")
    ))
    List<Car> getCarList();

    @Update("update car set state = 2 where id = #{id}")
    void updateState(int id);

    @Select("select * from car where id = #{id}")
    @Results(@Result(
            property = "user",
            column = "u_id",
            one = @One(select = "com.cfy.android.carrent.mapper.UserMapper.selectById")
    ))
    Car selectById(int id);

    @Select("select * from car where state = 0")
    @Results(@Result(
            property = "user",
            column = "u_id",
            one = @One(select = "com.cfy.android.carrent.mapper.UserMapper.selectById")
    ))
    List<Car> getCheckCarList();


    @Update("update car set state = 1 where id = #{id}")
    void pass(int id);
}
