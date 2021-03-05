package com.example.roombooking.View.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class RoomDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner startSpinner, endSpinner;
    TextView idTxt, capacityTxt;
    RoomService roomService;
    Button bookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        idTxt = findViewById(R.id.idTxt);
        capacityTxt = findViewById(R.id.capacityTxt);
        startSpinner = findViewById(R.id.startSpinner);
        endSpinner = findViewById(R.id.endSpinner);
        ArrayAdapter<CharSequence> startAdapter = ArrayAdapter.createFromResource(this, R.array.startTime, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> endAdapter = ArrayAdapter.createFromResource(this, R.array.endTime, android.R.layout.simple_spinner_item);

        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        startSpinner.setAdapter(startAdapter);
        startSpinner.setOnItemSelectedListener(RoomDetailActivity.this);
        endSpinner.setAdapter(endAdapter);
        endSpinner.setOnItemSelectedListener(RoomDetailActivity.this);

        Intent fromList = getIntent();
        int roomId = fromList.getIntExtra("room_id", 0);
        Retrofit retrofit = ApiClient.getRetrofit();
        roomService = retrofit.create(RoomService.class);
        Call<Room> detailCall = roomService.viewRoomDetail(roomId);

        detailCall.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                Room room = response.body();
                idTxt.setText(room.getId());
                capacityTxt.setText(room.getCapacity());

            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String start = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, start, Toast.LENGTH_SHORT);
        String end = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}