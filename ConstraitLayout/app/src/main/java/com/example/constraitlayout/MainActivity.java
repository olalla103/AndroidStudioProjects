package com.example.constraitlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);
        Button boton3 = findViewById(R.id.boton3);
        Button boton4 = findViewById(R.id.boton4);
        Button boton5 = findViewById(R.id.boton5);

        // Configuración de los listeners para cada botón
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, p511.class);
                startActivity(intent);
            }
        });


        // BOTÓN 1
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, p512.class);
                startActivity(intent);
            }
        });


        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, p513.class);
                startActivity(intent);
            }
        });


        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, p514.class);
                startActivity(intent);
            }
        });


        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, p515.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View view) {

    }
}
