package com.example.roombooking.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.roombooking.R;
import com.example.roombooking.View.Admin.AdminHomeActivity;

public class LoginActivity extends AppCompatActivity {

    RadioButton user_rd, admin_rd;
    EditText emailTxt, pwsTxt;
    Button loginBtn, registerBtn;

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

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                startActivity(intent);

                boolean user_check = user_rd.isChecked();
                if(user_check){
//                  check if it is a user
//                    login(role);
                }
                boolean admin_check = admin_rd.isChecked();
                if(admin_check){
//                  check if it is an admin
//                    login(role);
                }
            }
        });


    }
}