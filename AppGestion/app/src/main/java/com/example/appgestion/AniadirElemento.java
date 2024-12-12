package com.example.appgestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AniadirElemento extends AppCompatActivity {

    private EditText nombrePrenda, descripcionPrenda;
    private Spinner estiloPrenda;
    private Button botonAceptar, botonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_elemento);

        // Inicializar componentes
        nombrePrenda = findViewById(R.id.editTextText);
        descripcionPrenda = findViewById(R.id.editTextTextMultiLine);
        estiloPrenda = findViewById(R.id.spinnerEstilo); // Cambiado a Spinner
        botonAceptar = findViewById(R.id.button3);
        botonCancelar = findViewById(R.id.button4);

        // Configurar opciones del Spinner si no están predefinidas en XML
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, new String[]{"Femenino", "Masculino", "Neutro"});
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estiloPrenda.setAdapter(adaptador);

        // Configurar evento para el botón "Aceptar"
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener datos ingresados
                String nombre = nombrePrenda.getText().toString().trim();
                String descripcion = descripcionPrenda.getText().toString().trim();
                String estilo = estiloPrenda.getSelectedItem().toString(); // Obtener opción seleccionada

                // Pasar datos a la actividad principal
                Intent intent = new Intent();
                intent.putExtra("nombre", nombre);
                intent.putExtra("descripcion", descripcion);
                intent.putExtra("estilo", estilo);

                setResult(RESULT_OK, intent);
                finish(); // Finalizar la actividad
            }
        });

        // Configurar evento para el botón "Cancelar"
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cancelar la operación y volver a la actividad anterior
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

}
