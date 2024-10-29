package com.example.propuesta5_5;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        RadioGroup miGrupo = (RadioGroup) findViewById(R.id.grupi);


        //miGrupo.clearCheck(); // limpiamos los checks que estén dados
        // hacemos un listener para ver el botón que está marcado
        final String[] textoBoton = {""};
        miGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton boton = findViewById(i);
                textoBoton[0] = (String) boton.getText();
                // creamos el editText que vamos a darle los nombres que coja en el listener
                TextView txtDia = (TextView) findViewById(R.id.textoDia);
                txtDia.setText(textoBoton[0]);

            }
        });


    }
}