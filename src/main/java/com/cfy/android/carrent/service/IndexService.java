package com.cfy.android.carrent.service;

import com.cfy.android.carrent.model.User;

public interface IndexService {

    User signInByToken(String token);

    void changeImage(String id, String path);


}
