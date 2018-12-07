package com.example.gguzzardi.it_accelerator_recyclerview.presenters;

import com.example.gguzzardi.it_accelerator_recyclerview.model.UserLoginData;
import com.example.gguzzardi.it_accelerator_recyclerview.model.events.UserLoginEvent;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.LoginView;

import org.greenrobot.eventbus.EventBus;

public class LoginPresenter {

    private final UserLoginData mUserLoginData;
    private final LoginView mLoginView;

    public LoginPresenter(LoginView loginView, UserLoginData userLoginData) {
        mUserLoginData = userLoginData;
        mLoginView = loginView;
    }

    public void updateEmail(String email) {
        mUserLoginData.setEmail(email);
    }

    public void updatePassword(String password) {
        mUserLoginData.setPassword(password);
    }

    public void login() {
        if (mUserLoginData.validate()) {
            EventBus.getDefault().post(new UserLoginEvent(mUserLoginData));
            mLoginView.onLoginSuccess();
        } else {
            mLoginView.onLoginError();
        }
    }
}
