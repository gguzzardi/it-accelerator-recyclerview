package com.example.gguzzardi.it_accelerator_recyclerview.presenters;

import com.example.gguzzardi.it_accelerator_recyclerview.model.User;

public class LoginPresenter {

    private final User mUser;

    public LoginPresenter(User user) {
        mUser = user;
    }

    public void updateEmail(String email) {
        mUser.setEmail(email);
    }

    public void updatePassword(String password) {
        mUser.setPassword(password);
    }
}
