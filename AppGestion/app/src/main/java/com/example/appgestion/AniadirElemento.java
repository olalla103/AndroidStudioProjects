package com.example.appgestion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AniadirElemento extends AppCompatActivity {

    private EditText nombrePrenda, precioPrenda;
    private Spinner estiloPrenda;
    private RadioGroup radioGroupTallas;
    private Button botonAceptar, botonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_elemento);

        // Inicializar componentes
        nombrePrenda = findViewById(R.id.nombrePrenda);
        precioPrenda = findViewById(R.id.precioPrenda);
        estiloPrenda = findViewById(R.id.spinnerEstilo);
        radioGroupTallas = findViewById(R.id.radioGroupTallas);
        botonAceptar = findViewById(R.id.aceptar);
        botonCancelar = findViewById(R.id.cancelar);

        // Configurar opciones del Spinner de estilos
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.estilos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estiloPrenda.setAdapter(adapter);

        // Configurar botón "Aceptar"
        botonAceptar.setOnClickListener(v -> {
            String nombre = nombrePrenda.getText().toString().trim();
            String precioTexto = precioPrenda.getText().toString().trim();
            String estilo = estiloPrenda.getSelectedItem().toString();
            String talla = getSelectedTalla();

            if (nombre.isEmpty() || precioTexto.isEmpty() || talla.isEmpty()) {
                setResult(RESULT_CANCELED);
                finish();
                return;
            }

            double precio = Double.parseDouble(precioTexto);

            // Crear Intent con los datos de la prenda
            Intent resultIntent = new Intent();
            resultIntent.putExtra("nombre", nombre);
            resultIntent.putExtra("talla", talla);
            resultIntent.putExtra("estilo", estilo);
            resultIntent.putExtra("precio", precio);

            // Imagen por defecto
            resultIntent.putExtra("imagen", R.drawable.imagen_defecto);

            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Configurar botón "Cancelar"
        botonCancelar.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    private String getSelectedTalla() {
        int selectedId = radioGroupTallas.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            return selectedRadioButton.getText().toString();
        }
        return ""; // Si no se selecciona nada
    }
}
