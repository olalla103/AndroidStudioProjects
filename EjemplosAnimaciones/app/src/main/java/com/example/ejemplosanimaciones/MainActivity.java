package com.example.ejemplosanimaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.button)/*, b2 = findViewById(R.id.button2), b3 = findViewById(R.id.button3)*/;
        ImageView bastet = findViewById(R.id.imgBastet); // suponiendo que está en el XML
        // Configuración de los listeners para cada botón
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Traslasion.class);

                // Configuración de la animación
                AnimationSet animation = new AnimationSet(true);
                AlphaAnimation aparicion = new AlphaAnimation(0, 1);
                aparicion.setDuration(3000); // Duración de la animación (3 segundos)

                animation.addAnimation(aparicion);
                animation.setRepeatMode(Animation.RESTART); // Repetir animación
                animation.setRepeatCount(20); // 20 repeticiones

                bastet.startAnimation(animation); // Ejecuta la animación en la imagen (ImageView)
                startActivity(intent); // Inicia la nueva actividad
            }
        });

     /*   b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rotacion.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Aparece_desaparece.class);
                startActivity(intent);
            }
        });*/


    }

}