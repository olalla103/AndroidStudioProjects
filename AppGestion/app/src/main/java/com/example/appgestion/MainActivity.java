package com.example.appgestion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Mapa con usuarios y contraseñas válidos
    private final HashMap<String, String> validUsers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Añade usuarios y contraseñas
        // Habrá que hacer un método para añadir usuarios y contraseñas
        validUsers.put("olallalnc", "Towel4");
        validUsers.put("inigolnc", "inigoFeo234");
        validUsers.put("bertabl", "huerta177");
        validUsers.put("paolabl", "pacoula1011");

        // Referencias a los elementos de la interfaz
        EditText usernameField = findViewById(R.id.usuario);
        EditText passwordField = findViewById(R.id.contrasenia);
        Button loginButton = findViewById(R.id.entrarButton);

        // Pulsar el botón de validación
        // Pulsar el botón de validación
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                if (isValidUser(username, password)) {
                    // Crear un Toast personalizado con imagen y fondo de color
                    Toast toast = Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT);

                    // Crear un diseño personalizado para el Toast
                    LinearLayout layout = new LinearLayout(MainActivity.this);  // Usa MainActivity.this para obtener el contexto
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    layout.setPadding(10, 10, 10, 10);

                    // Crear un ImageView para la imagen
                    ImageView imageView = new ImageView(MainActivity.this);  // Usa MainActivity.this para obtener el contexto
                    imageView.setImageResource(R.drawable.tostada);  // Usa tu imagen aquí

                    // Crear un TextView para el mensaje
                    TextView textView = new TextView(MainActivity.this);  // Usa MainActivity.this para obtener el contexto
                    textView.setText("Este es un toast con imagen!");
                    textView.setTextColor(Color.WHITE);  // Puedes poner cualquier color
                    textView.setTextSize(16);

                    // Añadir la imagen y el texto al layout
                    layout.addView(imageView);
                    layout.addView(textView);

                    // Establecer el layout en el Toast
                    toast.setView(layout);
                    toast.show();

                    // Vamos a la actividad de la lista de la ropa
                    Intent intent = new Intent(MainActivity.this, ListaElementos.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    // Validación de usuarios
    private boolean isValidUser(String username, String password) {
        return validUsers.containsKey(username) && validUsers.get(username).equals(password);
    }

}