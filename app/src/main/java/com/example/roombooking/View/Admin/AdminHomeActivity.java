package com.example.roombooking.View.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roombooking.R;
import com.example.roombooking.View.Customer.BookActivity;

public class AdminHomeActivity extends AppCompatActivity {

    CardView createRoom, manageUser, editRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        manageUser = findViewById(R.id.manage_card);
        createRoom = findViewById(R.id.create_card);
        editRoom = findViewById(R.id.edit_room_card);

        int id = getIntent().getIntExtra("userId", 0);
        String role = getIntent().getStringExtra("userRole");

        manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, ManageUserActivity.class);
                intent.putExtra("userId", id);
                intent.putExtra("userRole", role);
                startActivity(intent);
            }
        });

        createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, CreateRoomActivity.class);
                intent.putExtra("userId", id);
                intent.putExtra("userRole", role);
                startActivity(intent);
            }
        });

        editRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, BookActivity.class);
                intent.putExtra("userId", id);
                intent.putExtra("userRole", role);
                startActivity(intent);
            }
        });
    }
}