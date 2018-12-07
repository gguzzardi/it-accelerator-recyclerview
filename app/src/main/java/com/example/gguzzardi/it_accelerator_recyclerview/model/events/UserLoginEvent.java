package com.example.gguzzardi.it_accelerator_recyclerview.model.events;

import com.example.gguzzardi.it_accelerator_recyclerview.model.UserLoginData;

public class UserLoginEvent {
    public final UserLoginData mLoginData;

    public UserLoginEvent(UserLoginData loginData) {
        mLoginData = loginData;
    }
}
