package com.example.ejemplosanimaciones;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

public class Aparece_desaparece extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Animation aparece_desaparece = AnimationUtils.loadAnimation(this, R.anim.aparece_desaparece);
        aparece_desaparece.setRepeatMode(Animation.RESTART);
        aparece_desaparece.setRepeatCount(20);
    }
}