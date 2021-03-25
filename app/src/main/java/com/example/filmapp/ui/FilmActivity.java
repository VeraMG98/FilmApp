package com.example.filmapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.filmapp.Models.FilmModel;
import com.example.filmapp.R;
import com.example.filmapp.data.RetrofitBuilder;
import com.example.filmapp.databinding.ActivityFilmBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmActivity extends AppCompatActivity {

    private ActivityFilmBinding binding;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFilmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        if (getIntent() != null)
            id = getIntent().getStringExtra("ID");
        load();
    }

    private void load() {
        RetrofitBuilder.getInstance().getFilmById(id).enqueue(new Callback<FilmModel>() {
            @Override
            public void onResponse(Call<FilmModel> call, Response<FilmModel> response) {
                if (response.body() != null)
                    setView(response.body());
                else
                    Log.d("tag", "error");
            }

            @Override
            public void onFailure(Call<FilmModel> call, Throwable t) {

            }
        });
    }

    private void setView(FilmModel body) {
        binding.txtTitle.setText(body.getTitle());
        binding.txtDescription.setText(body.getDescription());
        binding.txtDirector.setText(body.getDirector());
        binding.txtProducer.setText(body.getProducer());
        binding.txtReleaseDate.setText(body.getReleaseDate());
    }


}