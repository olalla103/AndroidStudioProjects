package com.example.laveldadela11_4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establecer VistaTexto como la vista principal
        setContentView(new VistaTexto(this));
    }
}
