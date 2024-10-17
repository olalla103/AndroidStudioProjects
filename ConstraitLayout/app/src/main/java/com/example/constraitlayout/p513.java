package com.example.constraitlayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class p513 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p511); // Asegúrate de que el nombre del layout sea correcto
        TextView texto3 = (TextView) findViewById(R.id.texto3);
        texto3.append("\nBotón 3");
        texto3.setTextColor(getResources().getColor(R.color.color3));
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "font/umbrella.ttf");
        texto3.setTypeface(miFuente);
    }
}