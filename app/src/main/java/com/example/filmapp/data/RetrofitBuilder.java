package com.example.filmapp.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitBuilder {

    private RetrofitBuilder() { }
    private static GhibliApi instance;

    public static GhibliApi getInstance() {
        if (instance == null)
            instance = buildRetrofit();
        return instance;
    }

    private static GhibliApi buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GhibliApi.class);
    }
}
