package com.example.filmapp.ui.film_list;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.ItemFilmBriefBinding;
import com.example.filmapp.ui.film_detail.OnClick;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder> {
    private List<Film> films=new ArrayList<>();
    private OnClick onClick;

    public FilmsAdapter() {

    }

    /*public void setFilm(Film film){
        films.contains(film);
         notifyDataSetChanged();
    }*/

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public void setFilms(List<Film> films){
        this.films = films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBriefBinding binding= ItemFilmBriefBinding.inflate(LayoutInflater
                .from(parent.getContext()),parent,false);
        return new FilmsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(films.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClick(films.get(position).getId());
                Log.d("Film id:",films.get(position).getId() );
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    protected class FilmsViewHolder extends RecyclerView.ViewHolder{
        private ItemFilmBriefBinding binding;
        public FilmsViewHolder(@NonNull ItemFilmBriefBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void onBind(Film film) {
            binding.tvTitle.setText(film.getTitle());
            binding.tvProducer.setText(film.getProducer());


        }

    }
}
