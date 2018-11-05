package com.example.yoshino.recyclerviewretrofit.remote;

import com.example.yoshino.recyclerviewretrofit.model.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @GET("users/")
    Call<List<User>> getUsers();
    @POST("users/")
    Call<User> addUser(@Body User user);
    @POST("users/")
    Call<User> updateUser(@Body User user);

    @DELETE("users/{id}")
    Call<User> deleteUser(@Path("id") Integer id);


}
