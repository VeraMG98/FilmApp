package com.example.filmapp.data.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.filmapp.data.models.FilmModel;

@Database(entities = {FilmModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract FilmDao filmDao();
}
