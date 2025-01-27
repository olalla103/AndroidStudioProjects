package com.example.hito7;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InfoUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);

        TextView textoInformacion = findViewById(R.id.texto_informacion);

        // Leer el archivo desde la carpeta raw
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.manualusuario);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
            inputStream.close();

            // Establecer el texto leído en el TextView
            textoInformacion.setText(stringBuilder.toString());
        } catch (IOException e) {
            Toast.makeText(this, "Error al leer el archivo de información", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
