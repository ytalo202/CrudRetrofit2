package com.example.yoshino.recyclerviewretrofit.remote;

public class APIUtils {

    private APIUtils(){

    }
    public static  String API_URL = "http://192.168.1.15:8080/";


    public static UserService getUserService(){
        return  RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}
