package com.example.appgestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Mapa con usuarios y contraseñas válidos
    private final HashMap<String, String> validUsers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Añadir usuarios y contraseñas válidos
        validUsers.put("olallalnc", "Towel4");
        validUsers.put("inigolnc", "inigoFeo234");
        validUsers.put("bertabl", "huerta177");
        validUsers.put("paolabl", "pacoula1011");

        // Referencias a los elementos de la interfaz
        EditText usernameField = findViewById(R.id.usuario);
        EditText passwordField = findViewById(R.id.contrasenia);
        Button loginButton = findViewById(R.id.entrarButton);

        // Configurar el botón de inicio de sesión
        loginButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (isValidUser(username, password)) {
                // Mostrar el Toast personalizado
                showCustomToast("Inicio de sesión exitoso");

                // Ir a la actividad de lista de elementos
                Intent intent = new Intent(MainActivity.this, ListaElementos.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Validación de usuarios
    private boolean isValidUser(String username, String password) {
        return validUsers.containsKey(username) && validUsers.get(username).equals(password);
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
