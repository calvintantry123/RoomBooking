package com.example.roombooking.View.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.User;
import com.example.roombooking.R;
import com.example.roombooking.Service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditActivity extends AppCompatActivity {

    EditText nameTxt, emailTxt, pwsTxt, phoneTxt;
    Button updateBtn;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        pwsTxt = findViewById(R.id.pwsTxt);
        phoneTxt = findViewById(R.id.phoneTxt);

        updateBtn = findViewById(R.id.updateBtn);

        Retrofit retrofit = ApiClient.getRetrofit();
        userService = retrofit.create(UserService.class);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

    }

    private void updateData() {
        String name, email, pws, phone;
        name = nameTxt.getText().toString();
        email = emailTxt.getText().toString();
        pws = pwsTxt.getText().toString();
        phone = phoneTxt.getText().toString();

        User updateUser = new User(name, phone, pws, email, "user");
        Call<User> call = userService.updateUser(2, updateUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User updated = response.body();
                    Toast.makeText(EditActivity.this, "update success", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditActivity.this, "update failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}