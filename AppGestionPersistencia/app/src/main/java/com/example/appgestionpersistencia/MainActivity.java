package com.example.appgestionpersistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private DataBaseHelper dbHelper; // Instancia de SQLite
    private SharedPreferences sharedPreferences; // Instancia de SharedPreferences
    private EditText usernameField, passwordField;

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

        // Cargar el último usuario conectado (si existe)
        String lastUsername = sharedPreferences.getString("lastUsername", "");
        if (!lastUsername.isEmpty()) {
            usernameField.setText(lastUsername);
        }

        // Configurar el botón de inicio de sesión
        loginButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (dbHelper.validarUsuario(username, password)) {
                // Guardar el último usuario conectado en SharedPreferences
                sharedPreferences.edit().putString("lastUsername", username).apply();

                // Mostrar el Toast personalizado
                showCustomToast("Inicio de sesión exitoso");

                // Ir a la actividad de lista de elementos
                Intent intent = new Intent(MainActivity.this, ListaElementos.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        // Insertar usuarios de prueba en la base de datos
        insertarUsuariosPorDefecto();
    }

    // Método para insertar usuarios por defecto en la base de datos
    private void insertarUsuariosPorDefecto() {
        dbHelper.insertarUsuario("olallalnc", "Towel4");
        dbHelper.insertarUsuario("inigolnc", "inigoFeo234");
        dbHelper.insertarUsuario("bertabl", "huerta177");
        dbHelper.insertarUsuario("paolabl", "pacoula1011");
    }

    // Método para mostrar el Toast personalizado
    private void showCustomToast(String message) {
        // Inflar el diseño personalizado del Toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_root));

        // Configurar el texto del Toast
        TextView toastText = layout.findViewById(R.id.toast_text);
        toastText.setText(message);

        // Configurar el icono si es necesario (opcional)
        ImageView toastIcon = layout.findViewById(R.id.toast_icon);
        toastIcon.setImageResource(R.drawable.icono); // Cambia por el recurso de tu icono

        // Crear y mostrar el Toast
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
