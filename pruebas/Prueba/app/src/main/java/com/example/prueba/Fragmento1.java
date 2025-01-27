package com.example.prueba;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class Fragmento1 extends Fragment {

    private OnFragmentInteractionListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragmento1, container, false);

        Button boton = view.findViewById(R.id.boton);
        EditText editText = view.findViewById(R.id.editText);

        // Envía el texto al listener al hacer clic en el botón
        boton.setOnClickListener(v -> {
            String texto = editText.getText().toString();
            listener.enviarTexto(texto);
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnFragmentInteractionListener) context;
    }

    public interface OnFragmentInteractionListener {
        void enviarTexto(String texto);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
