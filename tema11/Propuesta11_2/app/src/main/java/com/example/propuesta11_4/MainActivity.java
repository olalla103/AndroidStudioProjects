package com.example.propuesta11_4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_propuesta112);

        // Referenciar el ImageView
        ImageView miImagen = findViewById(R.id.miImagen);

        // Configurar el scaleType para ocupar toda la pantalla
        miImagen.setScaleType(ImageView.ScaleType.FIT_XY);

        // Imagen desde recurso
        Bitmap miBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.smiskiojopes);

        // Asignar la imagen al ImageView
        miImagen.setImageBitmap(miBitMap);
    }
}
