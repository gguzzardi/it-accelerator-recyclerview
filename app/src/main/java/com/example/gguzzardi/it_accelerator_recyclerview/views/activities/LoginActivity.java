package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.UserLoginData;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mEmailInputLayout;
    private TextInputLayout mPasswordInputLayout;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailInputLayout = findViewById(R.id.inputLayout_email);
        mPasswordInputLayout = findViewById(R.id.inputLayout_password);
        mEmailEditText = findViewById(R.id.input_email);
        mPasswordEditText = findViewById(R.id.input_password);

        mLoginPresenter = new LoginPresenter(new UserLoginData());

        setupLoginButton();
    }

    private void setupLoginButton() {
        final Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUser()) {
                    openMainActivity();
                }
            }
        });
    }

    private void openMainActivity() {
        Intent openMainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(openMainActivityIntent);
    }

    private boolean validateUser() {
        boolean emailValid = validateEmail();
        boolean passwordValid = validatePassword();
        return emailValid && passwordValid;
    }

    private boolean validateEmail() {
        String email = mEmailEditText.getText().toString();
        if (email.isEmpty()) {
            String field = getResources().getString(R.string.hint_email);
            String message = String.format(getResources().getString(R.string.error_empty_field), field);
            mEmailInputLayout.setError(message);
            return false;
        }
        mEmailInputLayout.setErrorEnabled(false);
        mLoginPresenter.updateEmail(email);
        return true;
    }


    private boolean validatePassword() {
        String password = mPasswordEditText.getText().toString();
        if (password.isEmpty()) {
            String field = getResources().getString(R.string.hint_password);
            String message = String.format(getResources().getString(R.string.error_empty_field), field);
            mPasswordInputLayout.setError(message);
            return false;
        }
        mPasswordInputLayout.setErrorEnabled(false);
        mLoginPresenter.updatePassword(password);
        return true;
    }
}
