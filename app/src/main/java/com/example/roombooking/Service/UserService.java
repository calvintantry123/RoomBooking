package com.example.roombooking.Service;

import com.example.roombooking.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("users")
    Call<List<User>> getAllUser();

    @POST("users")
    Call<User> registerUser(User user);

//    @POST("")
}
