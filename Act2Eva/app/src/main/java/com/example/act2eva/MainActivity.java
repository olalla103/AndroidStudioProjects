package com.example.act2eva;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView textView;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Referencias a los componentes
        spinner = (Spinner) findViewById(R.id.spinner);
        textView = findViewById(R.id.txt);
        boton = (Button) findViewById(R.id.button);

        // Elementos para el Spinner
        String[] valores = {"Valor 1", "Valor 2", "Valor 3", "Valor 4"};
        spinner.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores));

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seleccionado = spinner.getSelectedItem().toString();
                textView.setText(seleccionado);
            }
        });
    }

}