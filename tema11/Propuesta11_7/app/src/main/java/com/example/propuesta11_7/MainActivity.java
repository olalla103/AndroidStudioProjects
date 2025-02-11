package com.example.propuesta11_7;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView vistaCamara;
    private Button btnIniciar, btnDetener, btnReproducir;
    private MediaRecorder grabadora;
    private MediaPlayer reproductor;
    private String rutaArchivo;
    private boolean estaGrabando = false;

    private static final int CODIGO_PERMISOS = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas
        vistaCamara = findViewById(R.id.surface);
        btnIniciar = findViewById(R.id.botonGrabar);
        btnDetener = findViewById(R.id.botonPausa);
        btnReproducir = findViewById(R.id.botonPlay);

        // Configurar la ruta de almacenamiento del video
        File archivoVideo = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES), "video_creado.mp4");
        rutaArchivo = archivoVideo.getAbsolutePath();

        // Configurar la vista de la cámara
        vistaCamara.getHolder().addCallback(this);

        // Solicitar permisos necesarios
        verificarPermisos();

        // Configurar eventos de botones
        btnIniciar.setOnClickListener(view -> comenzarGrabacion());
        btnDetener.setOnClickListener(v -> detenerProceso());
        btnReproducir.setOnClickListener(v -> reproducirVideo());
    }

    // Método para solicitar permisos al usuario
    private void verificarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permisosRequeridos = new ArrayList<>();

            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permisosRequeridos.add(Manifest.permission.CAMERA);
            }
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                permisosRequeridos.add(Manifest.permission.RECORD_AUDIO);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permisosRequeridos.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (!permisosRequeridos.isEmpty()) {
                requestPermissions(permisosRequeridos.toArray(new String[0]), CODIGO_PERMISOS);
            }
        }
    }

    // Iniciar la grabación de video
    private void comenzarGrabacion() {
        if (grabadora != null) {
            grabadora.release();
        }

        grabadora = new MediaRecorder();
        grabadora.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabadora.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        grabadora.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        grabadora.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        grabadora.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        grabadora.setOutputFile(rutaArchivo);
        grabadora.setPreviewDisplay(vistaCamara.getHolder().getSurface());

        try {
            grabadora.prepare();
            grabadora.start();
            estaGrabando = true;
            btnIniciar.setEnabled(false);
            btnDetener.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al iniciar la grabación", Toast.LENGTH_SHORT).show();
        }
    }

    // Detener grabación o reproducción
    private void detenerProceso() {
        if (estaGrabando) {
            grabadora.stop();
            grabadora.release();
            grabadora = null;
            estaGrabando = false;
            btnIniciar.setEnabled(true);
            btnDetener.setEnabled(false);
        } else if (reproductor != null && reproductor.isPlaying()) {
            reproductor.stop();
            reproductor.reset();
            btnReproducir.setText("REPRODUCIR");
        }
    }

    // Reproducir el video grabado
    private void reproducirVideo() {
        if (reproductor == null) {
            reproductor = new MediaPlayer();
            reproductor.setDisplay(vistaCamara.getHolder());
        }

        if (!reproductor.isPlaying()) {
            try {
                reproductor.setDataSource(rutaArchivo);
                reproductor.prepare();
                reproductor.start();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al reproducir el video", Toast.LENGTH_SHORT).show();
            }
        } else {
            reproductor.pause();
        }
    }

    // Métodos del SurfaceHolder
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (reproductor == null) {
            reproductor = new MediaPlayer();
            reproductor.setDisplay(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (grabadora != null) {
            grabadora.release();
            grabadora = null;
        }
        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
    }

    // Manejo de permisos del usuario
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PERMISOS) {
            boolean permisosConcedidos = true;
            for (int resultado : grantResults) {
                if (resultado != PackageManager.PERMISSION_GRANTED) {
                    permisosConcedidos = false;
                    break;
                }
            }
            if (!permisosConcedidos) {
                Toast.makeText(this, "Se requieren permisos para grabar", Toast.LENGTH_LONG).show();
            }
        }
    }
}
