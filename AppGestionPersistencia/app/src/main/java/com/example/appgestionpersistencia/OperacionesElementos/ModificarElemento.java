package com.example.appgestionpersistencia.OperacionesElementos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appgestionpersistencia.R;

public class ModificarElemento extends AppCompatActivity {

    private Spinner estiloPrenda;
    private RadioGroup radioGroupTallas;
    private Button botonAceptar, botonCancelar;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_elemento);

        // Inicialización
        estiloPrenda = findViewById(R.id.spinner);
        radioGroupTallas = findViewById(R.id.radioGroupTallas);
        botonAceptar = findViewById(R.id.aceptar);
        botonCancelar = findViewById(R.id.cancelar);

        // Configurar Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estilos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estiloPrenda.setAdapter(adapter);

        // Recuperar datos del intent
        Intent intent = getIntent();
        if (intent != null) {
            position = intent.getIntExtra("position", -1);

            String estilo = intent.getStringExtra("estilo");
            if (estilo != null) {
                int spinnerPosition = adapter.getPosition(estilo);
                estiloPrenda.setSelection(spinnerPosition);
            }

            String talla = intent.getStringExtra("talla");
            if (talla != null) {
                selectRadioButtonForTalla(talla);
            }
        }

        // Configurar botón aceptar
        botonAceptar.setOnClickListener(v -> {
            String estiloSeleccionado = estiloPrenda.getSelectedItem().toString();
            String tallaSeleccionada = getSelectedTalla();

            if (estiloSeleccionado.isEmpty() || tallaSeleccionada.isEmpty()) {
                setResult(RESULT_CANCELED);
                finish();
                return;
            }

            // Enviar datos de vuelta
            Intent resultIntent = new Intent();
            resultIntent.putExtra("estilo", estiloSeleccionado);
            resultIntent.putExtra("talla", tallaSeleccionada);
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

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
        return "";
    }

    private void selectRadioButtonForTalla(String talla) {
        for (int i = 0; i < radioGroupTallas.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroupTallas.getChildAt(i);
            if (radioButton.getText().toString().equalsIgnoreCase(talla)) {
                radioButton.setChecked(true);
                break;
            }
        }
    }
}
