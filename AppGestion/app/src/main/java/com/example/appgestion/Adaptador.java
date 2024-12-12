package com.example.appgestion;

import android.app.Activity;
import android.content.Intent;
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

        // Configurar el nombre de la prenda o estilo, con comprobación de null
        TextView titulo = convertView.findViewById(R.id.texto_titulo);

        // Comprobamos si el estilo es null antes de acceder a su nombre
        if (elemento.get_estilo() != null) {
            titulo.setText(elemento.get_estilo().name()); // Si el estilo no es null, mostramos su nombre
        } else {
            titulo.setText("Estilo no asignado"); // Si es null, mostramos un mensaje predeterminado
        }

        ImageView imagen = convertView.findViewById(R.id.imagen);
        imagen.setImageResource(elemento.get_imagen());

        // Configurar el botón del menú
        ImageButton botonMenu = convertView.findViewById(R.id.boton_menu);
        botonMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, botonMenu);
            popupMenu.inflate(R.menu.menu_item);
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.opcion_editar) {
                    // Cuando se selecciona la opción "editar"
                    Toast.makeText(context, "Editar: " + elemento.get_descripcion(), Toast.LENGTH_SHORT).show();

                    // Crear un Intent para abrir la actividad de edición
                    Intent intent = new Intent(context, ModificarElemento.class);
                    // Pasar los datos del elemento a modificar
                    intent.putExtra("imagen", elemento.get_imagen());
                    intent.putExtra("titulo", elemento.get_titulo());
                    intent.putExtra("descripcion", elemento.get_descripcion());
                    intent.putExtra("estilo", elemento.get_estilo() != null ? elemento.get_estilo().name() : ""); // Asegurarse de pasar un valor no nulo
                    intent.putExtra("favorito", elemento.is_favorito());
                    intent.putExtra("position", position);

                    // Iniciar la actividad para recibir resultados (para editar)
                    context.startActivityForResult(intent, 2);

                    return true;
                } else if (item.getItemId() == R.id.opcion_eliminar) {
                    // Eliminar el elemento de la lista
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

    // Método para actualizar un elemento después de editarlo
    public void actualizarElemento(int position, String nuevaDescripcion, String nuevoEstilo) {
        ListaElementos.Encapsulador elemento = datos.get(position);
        elemento.setDescripcion(nuevaDescripcion);
        elemento.setEstilo(nuevoEstilo);  // Asumimos que tienes un setter para el estilo
        notifyDataSetChanged();
    }
}
