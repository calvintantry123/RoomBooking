package com.example.roombooking.View.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.roombooking.Adapter.UserAdapter;
import com.example.roombooking.ApiClient;
import com.example.roombooking.Model.User;
import com.example.roombooking.R;
import com.example.roombooking.Service.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ManageUserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        recyclerView = findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(ManageUserActivity.this));

//        postAdapter = new PostAdapter(ManageUserActivity.this);
//        recyclerView.setAdapter(postAdapter);
//
//        Retrofit retrofit = ApiClient.getRetrofit();
//        postService = retrofit.create(PostService.class);
//
//        Call<List<Post>> call = postService.getPost();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                List<Post> posts = response.body();
//                postAdapter.setPostList(posts);
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//
//            }
//        });

        userAdapter = new UserAdapter(ManageUserActivity.this);
        recyclerView.setAdapter(userAdapter);

        Retrofit retrofit = ApiClient.getRetrofit();
        userService = retrofit.create(UserService.class);

        Call<List<User>> call = userService.getAllUser();

//        Log.d("callbackMsg", "go to callback");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                Log.d("debugMsg", "onResponse: onresponse");
                List<User> userList = response.body();
                userAdapter.setUserList(userList);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failMsg", t.getLocalizedMessage());

            }
        });
    }
}