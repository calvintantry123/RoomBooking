package com.example.roombooking.Service;

import com.example.roombooking.Model.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RoomService {
    @GET("room")
    Call<List<Room>> viewRoom();
}
