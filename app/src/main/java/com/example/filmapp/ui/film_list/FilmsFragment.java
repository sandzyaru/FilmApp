package com.example.filmapp.ui.film_list;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.filmapp.App;
import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.FragmentFilmsBinding;
import com.example.filmapp.ui.film_detail.OnClick;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmsFragment extends Fragment implements OnClick {

    private FragmentFilmsBinding binding;

    private FilmsAdapter adapter;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        binding=FragmentFilmsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter=new FilmsAdapter();
        adapter.setOnClick(this);
        binding.recycler.setAdapter(adapter);

        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if(response.isSuccessful() && response.body() !=null){
                    adapter.setFilms(response.body());
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
            public void onFailure(Call<List<Film>> call, Throwable t) {
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
        @NonNull NavDirections action = (NavDirections) FilmsFragmentDirections.actionFilmsFragmentToDetailsFragment(id);
        NavHostFragment.findNavController(FilmsFragment.this).navigate(action);
        Log.d("Film id:",id);

    }
}