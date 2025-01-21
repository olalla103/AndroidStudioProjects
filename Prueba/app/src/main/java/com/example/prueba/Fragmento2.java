package com.example.prueba;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragmento2 extends Fragment {

    private TextView texto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragmento2, container, false);
        texto = view.findViewById(R.id.texto);
        return view;
    }

    // Actualiza el contenido del TextView
    public void nuevoTexto(String frase) {
        if (frase != null) {
            texto.setText(frase);
        }
    }
}
