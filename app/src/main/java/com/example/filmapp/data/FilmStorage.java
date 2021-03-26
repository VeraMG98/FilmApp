package com.example.filmapp.data;

import com.example.filmapp.data.models.FilmModel;
import com.example.filmapp.data.retrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmStorage {

    public static void getFilmById(String id, Result result) {
        RetrofitBuilder.getInstance().getFilmById(id).enqueue(new Callback<FilmModel>() {
            @Override
            public void onResponse(Call<FilmModel> call, Response<FilmModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.onSuccess(response.body());
                } else {
                    result.onFailure("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<FilmModel> call, Throwable t) {
                result.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public interface Result {
        void onSuccess(FilmModel film);
        void onFailure(String t);
    }
}
