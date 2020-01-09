package com.cfy.android.carrent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.android.carrent.model.SubCar;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubCarMapper extends BaseMapper<SubCar> {

    @Insert("insert into subCar (uid,cid,sub_datetime,phone,state) values(#{user.id},#{car.id},#{subDateTime}," +
            "#{phone},#{state})")
    void insertSubCarMessage(SubCar subCar);

    @Select("select * from subcar where uid = #{uid}")
    @Results({@Result(property = "user",
            column = "uid",
            one = @One(select = "com.cfy.android.carrent.mapper.UserMapper.selectById")),
            @Result(property = "car",
            column = "cid",
            one = @One(select = "com.cfy.android.carrent.mapper.CarMapper.selectById"))
    })
    List<SubCar> getAllSubCar(int uid);

    @Insert("update subcar set state = 1 where id = #{id}")
    void endSubCar(int id);

    @Select("select * from subcar where uid = #{uid} and state = 0")
    @Results({@Result(property = "user",
            column = "uid",
            one = @One(select = "com.cfy.android.carrent.mapper.UserMapper.selectById")),
            @Result(property = "car",
                    column = "cid",
                    one = @One(select = "com.cfy.android.carrent.mapper.CarMapper.selectById"))
    })
    List<SubCar> getSubCar(int uid);

    @Select("select * from subcar where uid = #{uid} and  state = 1")
    @Results({@Result(property = "user",
            column = "uid",
            one = @One(select = "com.cfy.android.carrent.mapper.UserMapper.selectById")),
            @Result(property = "car",
                    column = "cid",
                    one = @One(select = "com.cfy.android.carrent.mapper.CarMapper.selectById"))
    })
    List<SubCar> getEndSubCar(int uid);
}
