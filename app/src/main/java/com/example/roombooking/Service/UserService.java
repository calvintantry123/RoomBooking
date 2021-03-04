package com.example.roombooking.Service;

import com.example.roombooking.Model.LoginResponse;
import com.example.roombooking.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("users")
    Call<List<User>> getAllUser();

    @POST("users")
    Call<User> registerUser(@Body User user);

    @POST("users")
    Call<LoginResponse> loginUser(@Body LoginResponse loginResponse);

    @PATCH("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

//    @POST("")
}
