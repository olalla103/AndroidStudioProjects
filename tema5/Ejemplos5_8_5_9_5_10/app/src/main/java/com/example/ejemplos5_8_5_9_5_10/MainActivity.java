package com.example.ejemplos5_8_5_9_5_10;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // EJEMPLO 5.7
        String[] opciones = {"Opción 1", "Opción 2", "Opción 3", "Opción 4", "Opción 5"};
        AutoCompleteTextView textoLeido = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, opciones);
        textoLeido.setAdapter(adaptador);
    }
}