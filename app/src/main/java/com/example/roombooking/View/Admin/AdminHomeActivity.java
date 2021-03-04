package com.example.roombooking.View.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roombooking.R;

public class AdminHomeActivity extends AppCompatActivity {

    CardView createRoom, manageUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        manageUser = findViewById(R.id.manage_card);
        manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, ManageUserActivity.class);
                startActivity(intent);
            }
        });
    }
}