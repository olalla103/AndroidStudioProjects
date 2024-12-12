package com.example.appgestion;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.Toast;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.entrada, parent, false);
        }

        ListaElementos.Encapsulador elemento = (ListaElementos.Encapsulador) getItem(position);

        // Configurar texto e imagen
        TextView titulo = convertView.findViewById(R.id.texto_titulo);
        titulo.setText(elemento.get_descripcion());

        ImageView imagen = convertView.findViewById(R.id.imagen);
        imagen.setImageResource(elemento.get_imagen());

        // Configurar el botón del menú
        ImageButton botonMenu = convertView.findViewById(R.id.boton_menu);
        botonMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, botonMenu);
            popupMenu.inflate(R.menu.menu_item);
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.opcion_editar) {
                    Toast.makeText(context, "Editar: " + elemento.get_descripcion(), Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.opcion_eliminar) {
                    datos.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Elemento eliminado", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }
            });
            popupMenu.show();
        });

        return convertView;
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

   /* @Override
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
    }*/
}
