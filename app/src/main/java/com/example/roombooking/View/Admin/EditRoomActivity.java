package com.example.roombooking.View.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.Room;
import com.example.roombooking.Model.User;
import com.example.roombooking.R;
import com.example.roombooking.Service.RoomService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditRoomActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView nameTxt, typeTxt, capacityTxt;
    EditText newNameTxt, newCapacityTxt;
    Spinner typeSpinner;
    Button editBtn;
    String value;
    int roomId;
    RoomService roomService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        nameTxt = findViewById(R.id.nameTxt);
        typeTxt = findViewById(R.id.typeTxt);
        capacityTxt = findViewById(R.id.capacityTxt);
        typeSpinner = findViewById(R.id.typeSpinner);
        newNameTxt = findViewById(R.id.newNameTxt);
        newCapacityTxt = findViewById(R.id.newCapacityTxt);
        editBtn = findViewById(R.id.editBtn);

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.roomType, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(this);

        roomId = getIntent().getIntExtra("room_id", 0);
        Retrofit retrofit = ApiClient.getRetrofit();
        roomService = retrofit.create(RoomService.class);

        Call<Room> call = roomService.viewRoomDetail(roomId);
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
                updateData(roomId, value);
            }
        });

    }

    private void updateData(int roomId, String value) {
        String name = newNameTxt.getText().toString();
        int capacity = Integer.valueOf(newCapacityTxt.getText().toString());

        Room room = new Room(name, capacity, value);
        Call<Room> call = roomService.updateRoom(roomId, room);
        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                Room test = response.body();
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        value = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}