package com.example.gguzzardi.it_accelerator_recyclerview.model;

public class UserLoginData {

    private String mEmail;
    private String mPassword;

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public boolean validate() {
        boolean validEmail = validateEmail();
        boolean validPassword = validatePassword();
        return validEmail && validPassword;
    }

    private boolean validateEmail() {
        return !mEmail.isEmpty();
    }

    private boolean validatePassword() {
        return !mPassword.isEmpty();
    }

}
