package com.example.filmapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.filmapp.Models.FilmAdapter;
import com.example.filmapp.Models.FilmModel;
import com.example.filmapp.R;
import com.example.filmapp.data.FilmStorage;
import com.example.filmapp.data.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FilmAdapter.OnClickListener {
    private FilmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new FilmAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }


    @Override
    public void onClick(FilmModel film) {
        Intent intent = new Intent(this, FilmActivity.class);
        intent.putExtra("ID", film.getId());
        startActivity(intent);
    }
}