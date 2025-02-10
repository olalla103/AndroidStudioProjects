package com.example.appgestion;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Inicializar MediaPlayer con la música de la carpeta raw
        mediaPlayer = MediaPlayer.create(this, R.raw.yesandcortado);
        mediaPlayer.setLooping(false); // No repetir la música
        mediaPlayer.start(); // Iniciar la música

        // Redirigir a la actividad principal después de 3 segundos
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Cierra la actividad para que no se pueda volver atrás
        }, 3000); // 3000 ms = 3 segundos
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Liberar recursos del MediaPlayer
            mediaPlayer = null;
        }
    }
}
