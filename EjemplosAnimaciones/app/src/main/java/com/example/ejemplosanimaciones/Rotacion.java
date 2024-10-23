package com.example.ejemplosanimaciones;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

public class Rotacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotacion);
        Animation rotacion = AnimationUtils.loadAnimation(this, R.anim.rotacion);
        rotacion.setRepeatMode(Animation.RESTART);
        rotacion.setRepeatCount(20);
    }
}