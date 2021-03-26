package com.example.filmapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.filmapp.R;
import com.example.filmapp.data.dao.App;
import com.example.filmapp.data.dao.AppDataBase;
import com.example.filmapp.data.models.FilmAdapter;
import com.example.filmapp.data.models.FilmModel;
import com.example.filmapp.databinding.ActivityFavoriteFilmBinding;
import com.example.filmapp.databinding.ActivityMainBinding;

import java.util.List;

public class FavoriteFilmActivity extends AppCompatActivity implements FilmAdapter.OnClickListener {
    private ActivityFavoriteFilmBinding binding;
    private FilmAdapter adapter;
    private List<FilmModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteFilmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        if (App.getAppDataBase().filmDao().getAllElements() == null)
            Toast.makeText(this, "Нет сохраненных фильмов", Toast.LENGTH_SHORT).show();
        else {
            list = App.getAppDataBase().filmDao().getAllElements();
            load();
        }
    }

    private void load() {
        adapter = new FilmAdapter(this);
        adapter.setFilmList(list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(FilmModel film) {
        Intent intent = new Intent(this, FilmActivity.class);
        intent.putExtra("ID", film.getId());
        startActivity(intent);
    }
}