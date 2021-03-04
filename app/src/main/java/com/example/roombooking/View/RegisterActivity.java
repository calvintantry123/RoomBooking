package com.example.roombooking.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.User;
import com.example.roombooking.R;
import com.example.roombooking.Service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    EditText nameTxt, emailTxt, pwsTxt, phoneTxt;
    Button registerBtn, loginBtn;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        pwsTxt = findViewById(R.id.pwsTxt);
        phoneTxt = findViewById(R.id.phoneTxt);

        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);

        Retrofit retrofit = ApiClient.getRetrofit();
        userService = retrofit.create(UserService.class);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerAccount() {
        String name, email, pws, phone, role;
        name = nameTxt.getText().toString();
        email = emailTxt.getText().toString();
        pws = pwsTxt.getText().toString();
        phone = phoneTxt.getText().toString();
        role = "user";

        User user = new User(name, phone, pws, email, role);

        Call<User> call = userService.registerUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}