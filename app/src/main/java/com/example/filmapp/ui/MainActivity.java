package com.example.filmapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.filmapp.databinding.ActivityMainBinding;
import com.example.filmapp.data.models.FilmAdapter;
import com.example.filmapp.data.models.FilmModel;
import com.example.filmapp.R;
import com.example.filmapp.data.retrofit.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FilmAdapter.OnClickListener {
    private FilmAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        load();
    }

    private void load() {
        RetrofitBuilder.getInstance()
                .getFilms()
                .enqueue(new Callback<List<FilmModel>>() {
                    @Override
                    public void onResponse(Call<List<FilmModel>> call, Response<List<FilmModel>> response) {
                        if (response.isSuccessful() && response != null){
                            adapter.setFilmList(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<FilmModel>> call, Throwable t) {

                    }
                });
    }

    private void init() {
        adapter = new FilmAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        binding.btnFavoriteList.setOnClickListener(v -> openFavorite());
    }

    private void openFavorite() {
        Intent intent = new Intent(this, FavoriteFilmActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(FilmModel film) {
        Intent intent = new Intent(this, FilmActivity.class);
        intent.putExtra("ID", film.getId());
        startActivity(intent);
    }
}