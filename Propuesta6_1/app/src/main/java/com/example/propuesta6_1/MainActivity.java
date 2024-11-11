package com.example.propuesta6_1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ListView lista = findViewById(R.id.lista);
        final String[] valoresLista = {"España", "Alemania", "Lituania", "Croacia", "Italia", "Eslovenia", "Dinamarca", "República Checa"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, valoresLista);
        lista.setAdapter(adaptador);
        Button aniadir = findViewById(R.id.aniadir);
        Button eliminar = findViewById(R.id.eliminar);

        aniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para el botón "eliminar"
                Toast.makeText(getApplicationContext(), "Eliminar pulsado", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static void aniadir(String[] valoresLista, String palabra) {
        valoresLista = Arrays.copyOf(valoresLista, valoresLista.length + 1);
        valoresLista[valoresLista.length - 1] = palabra;

    }

    public static void eliminar(String[] valoresLista) {
        valoresLista = Arrays.copyOf(valoresLista, valoresLista.length - 1);

    }
}