package com.example.prueba;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Fragmento1.OnFragmentInteractionListener {

    private Fragmento2 fragmento2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Agrega los fragmentos a la actividad
        FragmentManager FManager = getSupportFragmentManager();
        FragmentTransaction FTransaction = FManager.beginTransaction();

        Fragmento1 fragmento1 = new Fragmento1();
        fragmento2 = new Fragmento2();
        FTransaction.add(R.id.caja1, fragmento1);
        FTransaction.add(R.id.caja2, fragmento2);
        FTransaction.commit();
    }

    // Recibe el texto de Fragmento1 y lo envía a Fragmento2
    public void enviarTexto(String texto) {
        fragmento2.nuevoTexto(texto);
    }
}
