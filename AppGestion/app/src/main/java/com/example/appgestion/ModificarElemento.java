package com.example.appgestion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModificarElemento extends AppCompatActivity {

    private EditText editTextDescripcion;
    private Spinner spinnerEstilo;
    private Button btnAceptar, btnCancelar;

    private int position;
    private String titulo, descripcion, estilo;
    private boolean favorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_elemento);

        // Inicializamos los elementos de la UI
        editTextDescripcion = findViewById(R.id.editTextTextMultiLine);
        spinnerEstilo = findViewById(R.id.spinnerEstilo);
        btnAceptar = findViewById(R.id.button3);
        btnCancelar = findViewById(R.id.button4);

        // Recuperamos los datos del Intent
        Intent intent = getIntent();
        descripcion = intent.getStringExtra("descripcion");
        estilo = intent.getStringExtra("estilo");
        position = intent.getIntExtra("position", -1);

        // Mostramos la descripción en el EditText
        if (descripcion != null) {
            editTextDescripcion.setText(descripcion);
        }

        // Configuramos el Spinner con los estilos
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estilos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstilo.setAdapter(adapter);

        // Establecemos el estilo actual en el Spinner, si es válido
        if (estilo != null) {
            int spinnerPosition = adapter.getPosition(estilo);
            if (spinnerPosition >= 0) {
                spinnerEstilo.setSelection(spinnerPosition);
            } else {
                // Si el estilo no se encuentra en la lista, establecer la selección en 0 (por ejemplo, "Selecciona un estilo")
                spinnerEstilo.setSelection(0);
            }
        }

        // Acción del botón Aceptar cambios
        btnAceptar.setOnClickListener(v -> guardarCambios());

        // Acción del botón Cancelar
        btnCancelar.setOnClickListener(v -> finish()); // Cierra la actividad sin guardar cambios
    }


    private void guardarCambios() {
        // Obtener los valores modificados
        String nuevaDescripcion = editTextDescripcion.getText().toString();
        String nuevoEstilo = spinnerEstilo.getSelectedItem().toString();

        // Crear el Intent con los nuevos datos
        Intent resultIntent = new Intent();
        resultIntent.putExtra("descripcion", nuevaDescripcion);
        resultIntent.putExtra("estilo", nuevoEstilo);
        resultIntent.putExtra("position", position); // Enviar la posición para identificar el item modificado

        // Establecer el resultado y cerrar la actividad
        setResult(RESULT_OK, resultIntent);
        finish(); // Regresa a la actividad anterior
    }
}
