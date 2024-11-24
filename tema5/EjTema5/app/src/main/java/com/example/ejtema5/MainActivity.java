package com.example.ejtema5;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView miTexto = (TextView) findViewById(R.id.texto);
        miTexto.setText("Nuevo texto para mostrar");
        miTexto.setTextColor(getResources().getColor(R.color.miColor));
        miTexto.append("\n Nueva línea de texto");
        // Uso de nuevas fuentes
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "fonts/Umbrella.ttf");
        miTexto.setTypeface(miFuente);
    }
}