package com.cfy.android.carrent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfy.android.carrent.mapper.UserMapper;
import com.cfy.android.carrent.model.User;
import com.cfy.android.carrent.provider.AliyunSmsProvider;
import com.cfy.android.carrent.service.SignUpService;
import com.cfy.android.carrent.service.vo.ImageUploadMessage;
import com.cfy.android.carrent.service.vo.SendSmsMessage;
import com.cfy.android.carrent.service.vo.SignUpVo;
import com.cfy.android.carrent.utils.AuthCodeRandom;
import com.cfy.android.carrent.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {


    @Autowired
    private AliyunSmsProvider aliyunSmsProvider;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public SendSmsMessage sendSms(String phone) {
        //用于传递信息
        System.out.println("向"+phone+"发送信息");
        SendSmsMessage sendSmsMessage = new SendSmsMessage();
        sendSmsMessage.setPhone(phone);

        //验证码是存在redis缓存中的
        String authCode = (String) redisTemplate.opsForValue().get(phone);
        //查看缓存中是否有该电话，有的话，继续用以前的验证码
        System.out.println(phone+"在缓存中的验证码为："+authCode);

        if (authCode == null) {
            System.out.println("缓存中未有"+phone+"的验证码");
            //获取6位随机数字
            authCode = AuthCodeRandom.getRandomNumberCode(6);
            //设置验证码
//            redisTemplate.opsForValue().set(phone, authCode,5, TimeUnit.MINUTES);//将验证码存入缓存中,并设置过期时间为5分钟
            //用于测试 将验证码设置缓存为永久 方便测试
            redisTemplate.opsForValue().set(phone, authCode);

            System.out.println("将" + authCode + "加入缓存");
        }
//        通过阿里云发送短信验证码
        sendSmsMessage.setAuthCode(authCode);
//        //调用将验证码和手机传递给阿里云短信进行短信发送
//        aliyunSmsProvider.sendSms(sendSmsMessage);

        //模拟发送成功，需要注释掉

        sendSmsMessage.setSuccess(true);
        return sendSmsMessage;
    }

    @Override
    public boolean phoneIsExist(String phone) {
        //设置查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);    //添加phone限制条件
        //根据查询条件用户
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
        if (user != null) {
            return true;
        }
        return false;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public boolean nameIsExist(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);

        if (user != null) {
            return true;
        }
        return false;
    }


    @Override
    public boolean checkAuthCode(String phone, String authCode) {
        //从缓存中获取验证码
        String phoneAuthCode = (String) redisTemplate.opsForValue().get(phone);
        if (phoneAuthCode == null) {
            System.out.println("未发送验证码");
            return false;
        }

        System.out.println(phoneAuthCode);

        if (phoneAuthCode.equals(authCode)) {
            System.out.println("验证码正确");
            return true;
        }
        System.out.println("验证码错误");
        return false;
    }

    /**
     * 保存数据库
     * @param signUpVo
     * @return
     */
    @Override
    public boolean saveUser(SignUpVo signUpVo) {
        User user = new User();
        user.setName(signUpVo.getName());
        String md5Password = MD5Utils.MD5Encode(signUpVo.getPassword());
        user.setPassword(md5Password);
        user.setPhone(signUpVo.getPhone());
        long nowTime = new Date().getTime();
        user.setCreateTime(nowTime);
        user.setUpdateTime(nowTime);
        user.setToken(UUID.randomUUID().toString());
        user.setImagePath(signUpVo.getImagePath());
        user.setType(0);
        userMapper.insert(user);
        return false;
    }

    @Override
    public ImageUploadMessage saveImage(MultipartFile file) {
        if (file.isEmpty()) {
            return new ImageUploadMessage(false, "图片为空",null);
        }

        //获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为:" + fileName);

        //获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("文件的后缀名为:" + suffixName);

        //文件上传后的路径

        fileName = UUID.randomUUID() + suffixName;
        String path = "image/";
        File dest = new File(path+fileName);
        System.out.println(dest);
        //检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            ImageUploadMessage message = new ImageUploadMessage(true,"头像上传成功",fileName);
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ImageUploadMessage(false, "上传照片失败", null);

    }
}
