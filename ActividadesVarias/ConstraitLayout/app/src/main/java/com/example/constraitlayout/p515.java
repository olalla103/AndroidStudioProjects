package com.example.constraitlayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class p515 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p515); // Asegúrate de que el nombre del layout sea correcto
        TextView texto5 = (TextView) findViewById(R.id.texto5);
        texto5.append("\nHola");
        texto5.setTextColor(getResources().getColor(R.color.color));
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "font/umbrella.ttf");
        texto5.setTypeface(miFuente);
    }
}