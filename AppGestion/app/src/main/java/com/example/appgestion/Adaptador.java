package com.example.appgestion;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private Activity context;
    private int layout;
    private ArrayList<ListaElementos.Encapsulador> datos;

    // Constructor
    public Adaptador(Activity context, int layout, ArrayList<ListaElementos.Encapsulador> datos) {
        this.context = context;
        this.layout = layout;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Si la vista ya está creada, reutilízala, si no, infla una nueva vista
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, null);
        }

        // Obtén las vistas dentro de la fila (en este caso, la imagen y el texto)
        TextView textoTitulo = convertView.findViewById(R.id.texto_titulo);
        ImageView imagenPrenda = convertView.findViewById(R.id.imagen);

        // Obtén el elemento actual
        ListaElementos.Encapsulador elemento = datos.get(position);

        // Configura los valores de las vistas
        textoTitulo.setText(elemento.get_textoTitulo());
        imagenPrenda.setImageResource(elemento.get_idImagen());

        return convertView;
    }
}
