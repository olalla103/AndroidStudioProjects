package com.example.proyectoconfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import java.util.HashMap;

public class Actividad extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);

        // Verifica si el contenedor del fragmento está presente
        if (findViewById(R.id.fragment_container) != null) {
            // Si estamos siendo restaurados de un estado anterior, no necesitamos hacer nada
            if (savedInstanceState != null) {
                return;
            }

            // Crea un nuevo Fragmento para ser colocado en el diseño de la actividad
            Fragmento1 primerFragmento = new Fragmento1();

            // Añade el fragmento al FrameLayout 'fragment_container'
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, primerFragmento).commit();
        }
    }
}