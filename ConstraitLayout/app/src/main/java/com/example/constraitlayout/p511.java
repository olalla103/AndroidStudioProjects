package com.example.constraitlayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class p511 extends AppCompatActivity {
    // Botón 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p511); // Asegúrate de que el nombre del layout sea correcto
        TextView texto1 = (TextView) findViewById(R.id.texto1);
        texto1.append("\nHola");
        texto1.setTextColor(getResources().getColor(R.color.miColor));
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "font/umbrella.ttf");
        texto1.setTypeface(miFuente);
    }
}