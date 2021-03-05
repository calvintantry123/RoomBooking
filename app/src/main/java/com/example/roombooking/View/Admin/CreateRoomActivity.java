package com.example.roombooking.View.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.Room;
import com.example.roombooking.R;
import com.example.roombooking.Service.RoomService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateRoomActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nameTxt, capacityTxt;
    Spinner typeSpinner;
    Button createBtn;
    String value;
    RoomService roomService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        nameTxt = findViewById(R.id.nameTxt);
        capacityTxt = findViewById(R.id.capacityTxt);
        typeSpinner = findViewById(R.id.typeSpinner);
        createBtn = findViewById(R.id.createBtn);

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.roomType, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(this);

        Retrofit retrofit = ApiClient.getRetrofit();
        roomService = retrofit.create(RoomService.class);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRoom(value);
            }
        });
    }

    private void createRoom(String value) {
        String name;
        name = nameTxt.getText().toString();
        int capacity = Integer.valueOf(capacityTxt.getText().toString());

        Room roomData = new Room(name, capacity, value);

        Call<Room> call = roomService.makeRoom(roomData);
        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                Room createdRoom = response.body();
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                String error = t.getLocalizedMessage();
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