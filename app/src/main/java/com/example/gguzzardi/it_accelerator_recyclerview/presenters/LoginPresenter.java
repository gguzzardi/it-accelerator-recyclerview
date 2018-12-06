package com.example.gguzzardi.it_accelerator_recyclerview.presenters;

import com.example.gguzzardi.it_accelerator_recyclerview.model.UserLoginData;

public class LoginPresenter {

    private final UserLoginData mUserLoginData;

    public LoginPresenter(UserLoginData userLoginData) {
        mUserLoginData = userLoginData;
    }

    public void updateEmail(String email) {
        mUserLoginData.setEmail(email);
    }

    public void updatePassword(String password) {
        mUserLoginData.setPassword(password);
    }
}
