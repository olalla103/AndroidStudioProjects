package com.example.propuesta11_3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establecer VistaDibujo como la vista principal
        setContentView(new VistaDibujo(this));
    }
}
