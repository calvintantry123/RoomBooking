package com.example.roombooking.View.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.roombooking.Adapter.RoomAdapter;
import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.Room;
import com.example.roombooking.R;
import com.example.roombooking.Service.RoomService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookActivity extends AppCompatActivity {

    RoomService roomService;
    RecyclerView recyclerView;
    RoomAdapter roomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        recyclerView = findViewById(R.id.room_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookActivity.this));
        roomAdapter = new RoomAdapter(BookActivity.this);
        recyclerView.setAdapter(roomAdapter);

        Retrofit retrofit = ApiClient.getRetrofit();
        roomService = retrofit.create(RoomService.class);

        Call<List<Room>> call = roomService.viewRoom();
        call.enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                List<Room> roomList = response.body();
                roomAdapter.setRoomList(roomList);
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {

            }
        });
    }
}