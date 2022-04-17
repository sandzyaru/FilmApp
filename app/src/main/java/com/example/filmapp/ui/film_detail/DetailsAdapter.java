package com.example.filmapp.ui.film_detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.ItemFilmBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    private List<Film> films=new ArrayList<>();
    public DetailsAdapter() {
    }
    public void setFilms(List<Film> films){
        this.films = films;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding= ItemFilmBinding.inflate(LayoutInflater
                .from(parent.getContext()),parent,false);
        return new DetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        holder.onBind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    protected class   DetailsViewHolder extends RecyclerView.ViewHolder{
        private ItemFilmBinding binding;
        public DetailsViewHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void onBind(Film film) {
            binding.tvTitle.setText(film.getTitle());
            binding.tvProducer.setText(film.getProducer());
            binding.tvDescription.setText(film.getDescription());
            Glide.with(itemView).load(film.getImage()).into(binding.tvImage);
            binding.tvOriginalTitle.setText(film.getOriginalTitle());
            binding.tvDirector.setText(film.getDirector());
        }
    }
}
