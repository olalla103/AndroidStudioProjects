package com.example.ejemplosanimaciones;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

public class Traslasion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Animation translacion = AnimationUtils.loadAnimation(this, R.anim.traslacion);
        translacion.setRepeatMode(Animation.RESTART);
        translacion.setRepeatCount(20);
    }
}