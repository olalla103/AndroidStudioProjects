package com.example.appgestionpersistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appgestionpersistencia.BBDD.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;
    private SharedPreferences sharedPreferences;
    private EditText usernameField, passwordField;
    private MediaPlayer mediaPlayer; // Instancia para la música

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la base de datos
        dbHelper = new DataBaseHelper(this);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("AppGestionPrefs", Context.MODE_PRIVATE);

        // Referencias a los elementos de la interfaz
        usernameField = findViewById(R.id.usuario);
        passwordField = findViewById(R.id.contrasenia);
        Button loginButton = findViewById(R.id.entrarButton);

        // Cargar el último usuario conectado
        String lastUsername = sharedPreferences.getString("lastUsername", "");
        if (!lastUsername.isEmpty()) {
            usernameField.setText(lastUsername);
        }

        // Iniciar la música
        iniciarMusica();

        // Configurar el botón de inicio de sesión
        loginButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (dbHelper.validarUsuario(username, password)) {
                // Guardar el último usuario conectado
                sharedPreferences.edit().putString("lastUsername", username).apply();

                // Mostrar el Toast personalizado
                showCustomToast("Inicio de sesión exitoso");

                // Detener la música antes de cambiar de pantalla
                detenerMusica();

                // Ir a la actividad de lista de elementos
                Intent intent = new Intent(MainActivity.this, ListaElementos.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        // Insertar usuarios por defecto en la base de datos
        insertarUsuariosPorDefecto();
    }

    // Método para iniciar la música
    private void iniciarMusica() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.yesandcortado);
            mediaPlayer.setLooping(false); // Repetir en bucle
            mediaPlayer.start();
        }
    }

    // Método para detener la música
    private void detenerMusica() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detenerMusica(); // Asegurar que la música se detiene al cerrar la app
    }

    // Método para insertar usuarios por defecto
    private void insertarUsuariosPorDefecto() {
        dbHelper.insertarUsuario("olallalnc", "Towel4");
        dbHelper.insertarUsuario("inigolnc", "inigoFeo234");
        dbHelper.insertarUsuario("bertabl", "huerta177");
        dbHelper.insertarUsuario("paolabl", "pacoula1011");
    }

    // Método para mostrar el Toast personalizado
    private void showCustomToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_root));

        TextView toastText = layout.findViewById(R.id.toast_text);
        toastText.setText(message);

        ImageView toastIcon = layout.findViewById(R.id.toast_icon);
        toastIcon.setImageResource(R.drawable.icono);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
