package com.example.constraitlayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class p514 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p511); // Asegúrate de que el nombre del layout sea correcto
        TextView texto4 = (TextView) findViewById(R.id.texto4);
        texto4.append("\nTexto botón 4");
        texto4.setTextColor(getResources().getColor(R.color.miColor));
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "font/umbrella.ttf");
        texto4.setTypeface(miFuente);
    }
}