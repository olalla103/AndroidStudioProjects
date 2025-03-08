package com.example.juegoolalla;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Obtener el puntaje final desde GameView
        int finalScore = getIntent().getIntExtra("score", 0);

        // Mostrar el puntaje en la pantalla
        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText("Puntaje Final: " + finalScore);

        // Botón para volver a jugar
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, GameActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Reiniciar el juego
                startActivity(intent);
                finish();
            }
        });

        // Botón para abandonar partida
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Regresar al menú principal
                startActivity(intent);
                finish();
            }
        });
    }
}
