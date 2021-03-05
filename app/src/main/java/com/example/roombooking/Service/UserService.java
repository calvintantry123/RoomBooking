package com.example.roombooking.Service;

import com.example.roombooking.Model.LoginResponse;
import com.example.roombooking.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("User")
    Call<List<User>> getAllUser();

    @POST("User")
    Call<User> registerUser(@Body User user);

    @POST("User")
    Call<LoginResponse> loginUser(@Body LoginResponse loginResponse);

    @PATCH("User/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @DELETE("User/{id}")
    Call<Void> deleteUser(@Path("id") int id);

//    @POST("")
}
