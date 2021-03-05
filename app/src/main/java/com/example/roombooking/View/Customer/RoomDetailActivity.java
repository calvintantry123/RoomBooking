package com.example.roombooking.View.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.Booking;
import com.example.roombooking.Model.Room;
import com.example.roombooking.R;
import com.example.roombooking.Service.BookService;
import com.example.roombooking.Service.RoomService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RoomDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner startSpinner, endSpinner;
    TextView nameTxt, typeTxt, capacityTxt;
    RoomService roomService;
    Button bookBtn;
    String start, end;
    BookService bookService;
    int userId;
    String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        nameTxt = findViewById(R.id.nameTxt);
        typeTxt = findViewById(R.id.typeTxt);
        capacityTxt = findViewById(R.id.capacityTxt);
        startSpinner = findViewById(R.id.startSpinner);
        endSpinner = findViewById(R.id.endSpinner);
        bookBtn = findViewById(R.id.bookBtn);

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
        userId = getIntent().getIntExtra("userId", 0);
        userRole = getIntent().getStringExtra("userRole");

        Retrofit retrofit = ApiClient.getRetrofit();
        roomService = retrofit.create(RoomService.class);
        Call<Room> detailCall = roomService.viewRoomDetail(roomId);

        detailCall.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                Room room = response.body();

                nameTxt.setText(room.getName());
                typeTxt.setText(room.getType());
                capacityTxt.setText(String.valueOf(room.getCapacity()));

            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {

            }
        });

        bookService = retrofit.create(BookService.class);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingRoom(roomId);
            }
        });


    }

    private void bookingRoom(int roomId) {
        Booking booking = new Booking(roomId, start, end);
        Call<Booking> bookingCall = bookService.makeBooking(booking);

        bookingCall.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if(response.isSuccessful()){
                    Booking booked = response.body();
                    Intent intent = new Intent(RoomDetailActivity.this, CustHomeActivity.class);
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        String value = parent.getItemAtPosition(position).toString();
        if(value.contains("20")){
            start = value;
        }
        else{
            end = value;
        }
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}