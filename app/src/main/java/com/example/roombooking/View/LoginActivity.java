package com.example.roombooking.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.LoginResponse;
import com.example.roombooking.Model.User;
import com.example.roombooking.R;
import com.example.roombooking.Service.UserService;
import com.example.roombooking.View.Admin.AdminHomeActivity;
import com.example.roombooking.View.Customer.CustHomeActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    RadioButton user_rd, admin_rd;
    EditText emailTxt, pwsTxt;
    Button loginBtn, registerBtn;
    UserService userService;

    User user;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_rd = findViewById(R.id.userRd);
        admin_rd = findViewById(R.id.adminRd);
        emailTxt = findViewById(R.id.emailTxt);
        pwsTxt = findViewById(R.id.pwsTxt);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        Retrofit retrofit = ApiClient.getRetrofit();
        userService = retrofit.create(UserService.class);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        Call<List<User>> getCall = userService.getAllUser();
        getCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userList = response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
//                startActivity(intent);

                boolean user_check = user_rd.isChecked();
                String role = "";
                if(user_check){
//                  check if it is a user
                    role = "user";
                    userLogin(role);
                }
                boolean admin_check = admin_rd.isChecked();
                if(admin_check){
//                  check if it is an admin
                    role = "admin";
                    adminLogin(role);
                }
            }
        });


    }

    private void userLogin(String role) {
        String email, pws;
        email = emailTxt.getText().toString();
        pws = pwsTxt.getText().toString();

        LoginResponse loginResponse = new LoginResponse(pws, email, role);
        Call<LoginResponse> call = userService.loginUser(loginResponse);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

//                LoginResponse logresp = response.body();

                if (response.isSuccessful()){
                    String loggedEmail = response.body().getEmail();
                    int size = userList.size();
                    for(int i = 0; i < userList.size(); i++){
                        if (userList.get(i).getEmail().equals(loggedEmail)){
                            Toast.makeText(LoginActivity.this, response.code() + ": Login success", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, CustHomeActivity.class);
                            intent.putExtra("userId", userList.get(i).getId());
                            intent.putExtra("userRole", userList.get(i).getRole());
                            startActivity(intent);
                        }
                    }

//                    Intent intent = new Intent(LoginActivity.this, CustHomeActivity.class);
//                    intent.putExtra("customerEmail", response.body().getEmail());
//                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, response.code() + ": login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage() + ": login failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void adminLogin(String role){

        String email, pws;
        email = emailTxt.getText().toString();
        pws = pwsTxt.getText().toString();

        LoginResponse loginResponse = new LoginResponse(pws, email, role);
        Call<LoginResponse> call = userService.loginUser(loginResponse);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

//                LoginResponse logresp = response.body();

                if (response.isSuccessful()){

                    String loggedEmail = response.body().getEmail();
                    int size = userList.size();
                    for(int i = 0; i < userList.size(); i++){
                        if (userList.get(i).getEmail().equals(loggedEmail)){
                            Toast.makeText(LoginActivity.this, response.code() + ": Login success", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                            intent.putExtra("userId", userList.get(i).getId());
                            intent.putExtra("userRole", userList.get(i).getRole());
                            startActivity(intent);
                        }
                    }

//                    Toast.makeText(LoginActivity.this, response.code() + ": Login success", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
//                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, response.code() + ": login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage() + ": login failed", Toast.LENGTH_SHORT).show();

            }
        });

    }
}