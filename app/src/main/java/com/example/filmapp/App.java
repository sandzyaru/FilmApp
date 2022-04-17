package com.example.filmapp;

import android.app.Application;

import com.example.filmapp.data.remote.FilmApi;
import com.example.filmapp.data.remote.RetroFitClient;

public class App extends Application {

    private RetroFitClient retroFitClient;
    public static FilmApi api;
    @Override
    public void onCreate() {
        super.onCreate();
        retroFitClient = new RetroFitClient();
        api = retroFitClient.createFilmApi();
    }
}
