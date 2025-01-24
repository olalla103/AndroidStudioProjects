package com.example.actividadpropuesta5_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BotonPulsado extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_boton_pulsado);

        Button bot3 = (Button) findViewById(R.id.bt3), bot4 = (Button) findViewById(R.id.bt4);
        bot3.setOnClickListener(this);
        bot4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt3) {
            // Acción para bot3
            Intent intent = new Intent(BotonPulsado.this, Boton3.class);
            startActivity(intent);
        } else if (view.getId() == R.id.bt4) {
            // Acción para bot4
            Intent intent = new Intent(BotonPulsado.this, Boton4.class);
            startActivity(intent);
        }
    }

}