package com.example.roombooking.View.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.Room;
import com.example.roombooking.R;
import com.example.roombooking.Service.RoomService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ManageRoomActivity extends AppCompatActivity {

    TextView nameTxt, typeTxt, capacityTxt;
    Button editBtn, deleteBtn;
    RoomService roomService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);

        nameTxt = findViewById(R.id.nameTxt);
        typeTxt = findViewById(R.id.typeTxt);
        capacityTxt = findViewById(R.id.capacityTxt);
        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        int room_id = getIntent().getIntExtra("room_id", 0);

        Retrofit retrofit = ApiClient.getRetrofit();
        roomService = retrofit.create(RoomService.class);

        Call<Room> call = roomService.viewRoomDetail(room_id);
        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                Room roomDetail = response.body();
                nameTxt.setText(roomDetail.getName());
                typeTxt.setText(roomDetail.getType());
                capacityTxt.setText(String.valueOf(roomDetail.getCapacity()));

            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageRoomActivity.this, EditRoomActivity.class);
                intent.putExtra("room_id", room_id);

                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRoom(room_id);

            }
        });
    }

    private void deleteRoom(int room_id) {
        Call<Void> call = roomService.deleteRoom(room_id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ManageRoomActivity.this, "room deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ManageRoomActivity.this, "delete failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}