package com.example.juegoolalla;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juegoolalla.game.GameView;

public class GameActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }
}