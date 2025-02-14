package com.example.appgestionpersistencia.OperacionesElementos;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
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
    private Button botonAceptar, botonCancelar, botonAbrirCamara, botonAñadirImagen;
    private ImageView imagenPrenda;
    private static final int SELECCIONAR_IMAGEN = 1001;
    private static final int REQUEST_IMAGE_CAPTURE = 1002;
    private String prendaImagenUri;
    private Uri imagenUri; // Para guardar la URI de la foto tomada con la cámara

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
        botonAñadirImagen = findViewById(R.id.botonAñadirImagen);
        botonAbrirCamara = findViewById(R.id.botonAbrirCamara);
        imagenPrenda = findViewById(R.id.imagenPrenda);

        // Configurar opciones del Spinner de estilos
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.estilos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estiloPrenda.setAdapter(adapter);

        // Botón para seleccionar imagen de la galería
        botonAñadirImagen.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECCIONAR_IMAGEN);
        });

        // Botón para abrir la cámara
        botonAbrirCamara.setOnClickListener(v -> abrirCamara());

        // Solicitar permisos si es necesario
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != getPackageManager().PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) != getPackageManager().PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_MEDIA_IMAGES
                }, 100);
            }
        }

        // Botón "Aceptar" para guardar los datos
        botonAceptar.setOnClickListener(v -> {
            try {
                String nombre = nombrePrenda.getText().toString().trim();
                String precioTexto = precioPrenda.getText().toString().trim();
                String estilo = estiloPrenda.getSelectedItem().toString();
                String talla = getSelectedTalla();

                if (nombre.isEmpty() || precioTexto.isEmpty() || talla.isEmpty()) {
                    Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    return;
                }

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
                if (prendaImagenUri != null) {
                    resultIntent.putExtra("imagenUri", prendaImagenUri); // 🔹 Guarda la URI en el intent
                }
                setResult(RESULT_OK, resultIntent);
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

        // Botón "Cancelar"
        botonCancelar.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    // Método para abrir la cámara y capturar imagen
    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) { // Verifica si hay una app de cámara disponible
            File fotoArchivo = null;
            try {
                fotoArchivo = crearImagenArchivo(); // Crea el archivo de imagen
            } catch (IOException ex) {
                Toast.makeText(this, "Error al crear archivo de imagen", Toast.LENGTH_SHORT).show();
            }

            if (fotoArchivo != null) {
                imagenUri = FileProvider.getUriForFile(this,
                        "com.example.appgestionpersistencia.fileprovider", // ⚠️ Debe coincidir con AndroidManifest.xml
                        fotoArchivo);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imagenUri);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        } else {
            Toast.makeText(this, "No se encontró una aplicación de cámara", Toast.LENGTH_SHORT).show();
        }
    }


    // Crear un archivo para la imagen capturada
    private File crearImagenArchivo() throws IOException {
        // Obtener el directorio de almacenamiento específico de la app
        File directorioAlmacenamiento = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MiApp");

        if (!directorioAlmacenamiento.exists()) {
            directorioAlmacenamiento.mkdirs(); // Crea el directorio si no existe
        }

        // Generar un nombre único para la imagen
        String nombreImagen = "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".jpg";

        // Crear el archivo dentro del directorio
        File imagenArchivo = new File(directorioAlmacenamiento, nombreImagen);

        return imagenArchivo;
    }


    // Obtener la talla seleccionada
    private String getSelectedTalla() {
        int selectedId = radioGroupTallas.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            return selectedRadioButton.getText().toString();
        }
        return "";
    }

    // Método para manejar el resultado de la cámara o selección de imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECCIONAR_IMAGEN && resultCode == RESULT_OK && data != null) {
            Uri imagenSeleccionada = data.getData();
            if (imagenSeleccionada != null) {
                prendaImagenUri = imagenSeleccionada.toString();
                ImageView imageViewPrenda = findViewById(R.id.imagenPrenda);
                imageViewPrenda.setImageURI(Uri.parse(prendaImagenUri));
            }
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (imagenUri != null) {
                prendaImagenUri = imagenUri.toString();
                ImageView imageViewPrenda = findViewById(R.id.imagenPrenda);
                imageViewPrenda.setImageURI(Uri.parse(prendaImagenUri));
            }
        }
    }

}
