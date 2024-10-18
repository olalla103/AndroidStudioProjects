package com.example.ejemplosanimaciones;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView miTexto = findViewById(R.id.texto);
        Animation miAnimacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
        miAnimacion.setRepeatMode(Animation.RESTART);
        miAnimacion.setRepeatCount(20);

    }
}