package com.example.ejtema5;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class Propuesta5_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView texto =(TextView) findViewById(R.id.texto5);
        texto.setTextColor(getResources().getColor(R.color.miColor));
        texto.append("Sigue siendo 17 de octubre");


    }
}