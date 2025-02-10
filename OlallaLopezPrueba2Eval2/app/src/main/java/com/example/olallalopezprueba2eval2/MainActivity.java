package com.example.olallalopezprueba2eval2;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new VistaCanvas(this)); // Cambiado a VistaCanvas
    }
}

