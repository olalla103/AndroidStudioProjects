package com.example.appgestionpersistencia.OperacionesElementos;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import androidx.core.content.FileProvider;

import android.Manifest;

import com.example.appgestionpersistencia.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AniadirElemento extends AppCompatActivity {

    private EditText nombrePrenda, precioPrenda;
    private Spinner estiloPrenda;
    private RadioGroup radioGroupTallas;
    private Button botonAceptar, botonCancelar;
    private static final int SELECCIONAR_IMAGEN = 1001;
    private String prendaImagenUri; // Almacena temporalmente la URI de la imagen seleccionada
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private Uri photoUri;

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

        // Código para solicitar permisos si es necesario
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_MEDIA_IMAGES
                }, 100);
            }
        }

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

        Button botonAbrirCamara = findViewById(R.id.botonAbrirCamara);
        botonAbrirCamara.setOnClickListener(v -> {

            abrirCamara();
        });


    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    private void abrirCamara() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            File photoFile = crearArchivoImagen();
//            if (photoFile != null) {
//                photoUri = FileProvider.getUriForFile(this,
//                        "com.example.appgestionpersistencia.fileprovider", photoFile);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
//            } else {
//                Log.e("abrirCamara", "Error creating image file");
//            }
//        } else {
//            Log.e("abrirCamara", "No camera app available");
//        }
//    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = crearArchivoImagen();
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this,
                        "com.example.appgestionpersistencia.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    // Método para crear el archivo de imagen
    private File crearArchivoImagen() {
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            return File.createTempFile("IMG_", ".jpg", directorio);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (photoUri != null) {
                ImageView imagenPrenda = findViewById(R.id.imagenPrenda);
                imagenPrenda.setImageURI(photoUri);
            }
        }
    }


}
