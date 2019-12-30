package com.cfy.android.carrent.service;

import com.cfy.android.carrent.service.vo.SignInMessage;
import com.cfy.android.carrent.service.vo.SignInVo;

public interface SignInService {

    SignInMessage checksignIn(SignInVo signInVo);

    boolean rememberPassword(SignInMessage signInMessage);

}
