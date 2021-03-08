package com.example.roombooking.Service;

import com.example.roombooking.Model.Booking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BookService {
    @POST("Borrowing")
    Call<Booking> makeBooking(@Body Booking booking);

    @GET("Borrowing")
    Call<List<Booking>> viewBooking();
}
