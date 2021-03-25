package com.example.filmapp.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapp.R;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {
    private List<FilmModel> filmList = new ArrayList<>();
    private OnClickListener listener;

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_films, parent,false);
        return new FilmHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
        holder.onBind(filmList.get(position));

    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public void setClickListener(OnClickListener listener){
        this.listener = listener;
    }

    public void setFilmList(List<FilmModel> body){
        filmList.addAll(body);
        notifyDataSetChanged();
    }

    public class FilmHolder extends RecyclerView.ViewHolder{
        private TextView textTitle, textDate;
        public FilmHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.film_title);
            textDate = itemView.findViewById(R.id.film_release_date);
        }

        public void onBind(FilmModel film) {
            textTitle.setText(film.getTitle());
            textDate.setText(film.getReleaseDate());
            itemView.setOnClickListener(v -> listener.onClick(filmList.get(getAdapterPosition())));
        }
    }
    public interface OnClickListener {
        void onClick(FilmModel film);
    }
}
