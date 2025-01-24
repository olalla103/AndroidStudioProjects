package com.example.ejercicio7;


import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Act1 extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_act1);
    }

    Intent otro = new Intent(this, Act2.class);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // No deberías iniciar una nueva Activity en onDestroy, es mejor dejarlo en onCreate o en un evento de usuario
    }


}
