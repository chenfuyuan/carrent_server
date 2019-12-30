package com.cfy.android.carrent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfy.android.carrent.mapper.UserMapper;
import com.cfy.android.carrent.model.User;
import com.cfy.android.carrent.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过token查找user，进行登录
     * @return
     */
    @Override
    public User signInByToken(String token) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("token",token);
        User user = userMapper.selectOne(wrapper);
        return user;

    }
}
