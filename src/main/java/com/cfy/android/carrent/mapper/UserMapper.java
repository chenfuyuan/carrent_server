package com.cfy.android.carrent.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.android.carrent.model.User;
import org.apache.ibatis.annotations.Update;


public interface UserMapper extends BaseMapper<User> {

    @Update("update user set image_path = #{path} where id = #{id}")
    void changeImage(String id,String path);
}
