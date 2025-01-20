package com.example.proyectoconfragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AniadirElemento extends AppCompatActivity {

    private EditText nombrePrenda, precioPrenda;
    private Spinner estiloPrenda;
    private RadioGroup radioGroupTallas;
    private Button botonAceptar, botonCancelar;
    private static final int SELECCIONAR_IMAGEN = 1001;
    private String prendaImagenUri; // Almacena temporalmente la URI de la imagen seleccionada


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

        Button botonAñadirImagen = findViewById(R.id.botonAñadirImagen);
        botonAñadirImagen.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECCIONAR_IMAGEN); // Llama a onActivityResult con el código SELECCIONAR_IMAGEN
        });

        // Configurar opciones del Spinner de estilos
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.estilos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estiloPrenda.setAdapter(adapter);


        // Configurar botón "Aceptar"
        botonAceptar.setOnClickListener(v -> {
            try {
                // Obtener los datos del formulario
                String nombre = ((EditText) findViewById(R.id.nombrePrenda)).getText().toString().trim();
                String precioTexto = ((EditText) findViewById(R.id.precioPrenda)).getText().toString().trim();
                String estilo = ((Spinner) findViewById(R.id.spinnerEstilo)).getSelectedItem().toString();
                String talla = getSelectedTalla();

                // Validar que no haya campos vacíos
                if (nombre.isEmpty() || precioTexto.isEmpty() || talla.isEmpty()) {
                    Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar que el precio sea un número válido
                double precio;
                try {
                    precio = Double.parseDouble(precioTexto);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Preparar los datos para devolverlos
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nombre", nombre);
                resultIntent.putExtra("precio", precio);
                resultIntent.putExtra("estilo", estilo);
                resultIntent.putExtra("talla", talla);
                resultIntent.putExtra("imagenUri", prendaImagenUri);
                setResult(RESULT_OK, resultIntent);
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECCIONAR_IMAGEN && resultCode == RESULT_OK && data != null) {
            Uri imagenSeleccionada = data.getData();
            if (imagenSeleccionada != null) {
                prendaImagenUri = imagenSeleccionada.toString(); // Guardar la URI como String
                Log.d("DEBUG", "URI de la imagen seleccionada: " + prendaImagenUri);
            }
        }
    }
}
