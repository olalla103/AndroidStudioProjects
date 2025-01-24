package com.example.proyecto1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Actividad1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad1);

        // Aquí puedes navegar a Actividad2 si lo deseas en onCreate
        Intent ejemplo = new Intent(Actividad1.this, Actividad2.class);
        startActivity(ejemplo);
            finish();  // Finaliza Actividad1 si no necesitas volver a ella
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // No deberías iniciar una nueva Activity en onDestroy, es mejor dejarlo en onCreate o en un evento de usuario
    }




}
