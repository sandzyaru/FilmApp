package com.example.filmapp.ui.film_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.filmapp.App;
import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.FragmentDetailsBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsFragment extends Fragment implements OnClick   {
    FragmentDetailsBinding binding;
    private DetailsAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new DetailsAdapter();
        binding.recycler.setAdapter(adapter);
        LoadData();





    }
    private void LoadData() {
         String id = DetailsFragmentArgs.fromBundle(getArguments()).getMyArg();
        App.api.getFilmDetail(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if(response.isSuccessful() && response.body() !=null){
                    adapter.setFilms(Collections.singletonList(response.body()));
                    Log.d("Call GG: ",call.toString());
                }else{
                    Snackbar.make(
                            binding.getRoot(),
                            response.message(),
                            BaseTransientBottomBar.LENGTH_LONG).
                            setBackgroundTint(Color.RED)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Snackbar.make(
                        binding.getRoot(),
                        t.getLocalizedMessage(),
                        BaseTransientBottomBar.LENGTH_LONG).
                        setBackgroundTint(Color.RED)
                        .show();
            }
        });

    }



    @Override
    public void onClick(String id) {


    }
}