package com.example.filmapp.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.filmapp.data.models.FilmModel;

import java.util.List;

@Dao
public interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(FilmModel filmModel);

    @Update
    void update(FilmModel filmModel);

    @Delete
    void delete(FilmModel filmModel);

    @Query("SELECT * FROM filmModel")
    List<FilmModel> getAllElements();

    @Query("SELECT * FROM filmModel WHERE id = :idSearch")
    boolean getById(String idSearch);
}
