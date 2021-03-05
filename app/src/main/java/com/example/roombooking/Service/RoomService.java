package com.example.roombooking.Service;

import com.example.roombooking.Model.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RoomService {
    @GET("Room")
    Call<List<Room>> viewRoom();

    @GET("Room/{id}")
    Call<Room> viewRoomDetail(@Path("id") int id);

    @POST("Room")
    Call<Room> makeRoom(@Body Room room);

    @PATCH("Room/{id}")
    Call<Room> updateRoom(@Path("id") int id, @Body Room room);

    @DELETE("Room/{id}")
    Call<Void> deleteRoom(@Path("id") int id);
}
