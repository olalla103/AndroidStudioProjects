package com.example.olalla_prueba5t5;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Actividad2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad2);

        ImageView miImagen = findViewById(R.id.miImagen); // Asegúrate de que este es el ID correcto

        // Cargar la animación
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.aparece_desaparece);

        // Iniciar la animación
        miImagen.startAnimation(fadeIn);
    }
}