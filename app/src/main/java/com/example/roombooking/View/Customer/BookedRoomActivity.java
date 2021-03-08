package com.example.roombooking.View.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.roombooking.Adapter.BookingAdapter;
import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.Booking;
import com.example.roombooking.Model.Room;
import com.example.roombooking.Model.User;
import com.example.roombooking.R;
import com.example.roombooking.Service.BookService;
import com.example.roombooking.Service.RoomService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookedRoomActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookingAdapter bookingAdapter;
    BookService bookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_room);

        recyclerView = findViewById(R.id.booked_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookingAdapter = new BookingAdapter(this);

        recyclerView.setAdapter(bookingAdapter);

        Retrofit retrofit = ApiClient.getRetrofit();
        bookService = retrofit.create(BookService.class);

        Call<List<Booking>> call = bookService.viewBooking();
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                List<Booking> bookingList = response.body();
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {

            }
        });
    }
}