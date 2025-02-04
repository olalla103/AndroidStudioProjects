package com.example.propuesta11_6;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String audioFilePath;
    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Verificar permisos
        if (!checkPermissions()) {
            requestPermissions();
        }

        // Configurar el archivo de audio
        audioFilePath = getExternalFilesDir(Environment.DIRECTORY_MUSIC) + "/audioGrabado.3gp";

        // Referencias a los botones
        ImageButton botonGrabar = findViewById(R.id.botonCirculo);
        ImageButton botonDetener = findViewById(R.id.doblebarra);
        ImageButton botonReproducir = findViewById(R.id.botonTriangulo);

        // Botón para grabar
        botonGrabar.setOnClickListener(v -> {
            if (isRecording) {
                Toast.makeText(this, "Ya estás grabando", Toast.LENGTH_SHORT).show();
            } else {
                startRecording();
            }
        });

        // Botón para detener
        botonDetener.setOnClickListener(v -> {
            if (isRecording) {
                stopRecording();
            } else {
                Toast.makeText(this, "No estás grabando", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para reproducir
        botonReproducir.setOnClickListener(v -> playRecording());
    }

    // Iniciar la grabación
    private void startRecording() {
        try {
            // Eliminar el archivo anterior si existe
            File audioFile = new File(audioFilePath);
            if (audioFile.exists()) {
                audioFile.delete();
            }

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(audioFilePath);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
            Toast.makeText(this, "Grabando audio...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al iniciar la grabación", Toast.LENGTH_SHORT).show();
        }
    }

    // Detener la grabación
    private void stopRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            Toast.makeText(this, "Grabación detenida", Toast.LENGTH_SHORT).show();
        }
    }

    // Reproducir el audio grabado
    private void playRecording() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(audioFilePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Reproduciendo grabación...", Toast.LENGTH_SHORT).show();

            // Liberar el MediaPlayer al finalizar
            mediaPlayer.setOnCompletionListener(mp -> {
                mediaPlayer.release();
                mediaPlayer = null;
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "No se pudo reproducir el audio", Toast.LENGTH_SHORT).show();
        }
    }

    // Verificar permisos
    private boolean checkPermissions() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    // Solicitar permisos
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, 200);
    }

    // Manejar el resultado de los permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permisos denegados", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
