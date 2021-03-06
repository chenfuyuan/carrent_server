package com.cfy.android.carrent.controller;


import com.cfy.android.carrent.service.SignUpService;
import com.cfy.android.carrent.service.vo.ImageUploadMessage;
import com.cfy.android.carrent.service.vo.SendSmsMessage;
import com.cfy.android.carrent.service.vo.SignUpMessage;
import com.cfy.android.carrent.service.vo.SignUpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Value("${com.cfy.tmpLocation}")
    private String resourceLocation;
    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @GetMapping("/authCode")
    @ResponseBody
    public SendSmsMessage getAuthCode(@RequestParam String phone) {
        SendSmsMessage sendSmsMessage;
        System.out.println("phone"+phone);
        boolean phoneIsExist = signUpService.phoneIsExist(phone);
        if (phoneIsExist) {
            sendSmsMessage = new SendSmsMessage();
            //设置错误信息
            sendSmsMessage.setPhone(phone);
            sendSmsMessage.setMessage("该手机号已被注册");
        }else {
            //进行短信发送
            sendSmsMessage = signUpService.sendSms(phone);//传递手机进行短信发送
        }

        return sendSmsMessage;
    }

    @PostMapping("/checkSignUp")
    @ResponseBody
    public SignUpMessage signUp(@RequestBody SignUpVo signUpVo) {
        System.out.println(signUpVo);
        String phone = signUpVo.getPhone();
        String name = signUpVo.getName();
        String password = signUpVo.getPassword();
        String authCode = signUpVo.getAuthCode();
        String imagePath = signUpVo.getImagePath();

        //新建注册信息
        SignUpMessage message = new SignUpMessage();
        message.setPhone(phone);

        //判断用户名是否存在
        if (signUpService.nameIsExist(name)) {
            message.setSuccess(false);
            message.setMessage("用户名已存在");
            return message;
        }

        //判断电话号码是否已存在
        if (signUpService.phoneIsExist(phone)) {
            message.setSuccess(false);
            message.setMessage("电话号码已注册");
            return message;
        }

        //判断验证码是否正确
        if (signUpService.checkAuthCode(phone,authCode)) {
            message.setMessage("成功注册");
            message.setSuccess(true);
            //保存用户
            signUpService.saveUser(signUpVo);
            return message;
        }else{
            message.setSuccess(false);
            message.setMessage("验证码错误");
        }

        return message;
    }

    @PostMapping("/upLoadImage")
    @ResponseBody
    public ImageUploadMessage upLoadImage(@RequestParam(value = "file") MultipartFile file) {
        return signUpService.saveImage(file);
    }


}
