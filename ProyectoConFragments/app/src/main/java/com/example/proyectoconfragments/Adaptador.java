// Adaptador actualizado para manejar el menú contextual con opción "Descripción"
package com.example.proyectoconfragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private Activity context;
    private int layout;
    private ArrayList<PrendaRopa> datos;


    public Adaptador(Activity context, int layout, ArrayList<PrendaRopa> datos) {
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
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.imagen = convertView.findViewById(R.id.imagen);
            holder.titulo = convertView.findViewById(R.id.texto_titulo);
            holder.botonMenu = convertView.findViewById(R.id.boton_menu);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PrendaRopa prenda = datos.get(position);
        // Configurar título de la prenda
        holder.titulo.setText(prenda.getNombre());
        // Configurar la imagen
        if (prenda.getImagenUri() != null) {
            holder.imagen.setImageURI(Uri.parse(prenda.getImagenUri())); // Si tiene URI personalizada
        } else {
            holder.imagen.setImageResource(prenda.getImagen()); // Imagen predeterminada
        }

        // Configurar el botón del menú
        holder.botonMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.botonMenu);
            popupMenu.inflate(R.menu.menu_item);

            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.opcion_descripcion) {
                    mostrarDescripcion(prenda);
                    return true;
                } else if (item.getItemId() == R.id.opcion_editar) {
                    Intent intent = new Intent(context, ModificarElemento.class);
                    intent.putExtra("position", position);
                    intent.putExtra("estilo", prenda.getEstilo().name());
                    intent.putExtra("talla", prenda.getTalla());
                    ((ListaElementos) context).startActivityForResult(intent, 2);
                    return true;
                } else if (item.getItemId() == R.id.opcion_eliminar) {
                    eliminarElemento(position);
                    return true;
                } else {
                    return false;
                }
            });
            popupMenu.show();
        });

        return convertView;
    }

    private void eliminarElemento(int position) {
        datos.remove(position);
        notifyDataSetChanged(); // Opcionalmente, animar la eliminación
    }

    private void mostrarDescripcion(PrendaRopa prenda) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Descripción de " + prenda.getNombre())
                .setMessage("Estilo: " + prenda.getEstilo() + "\n"
                        + "Talla: " + prenda.getTalla() + "\n"
                        + "Precio: $" + prenda.getPrecio())
                .setPositiveButton("Cerrar", null)
                .show();
    }

    public void actualizarDatos(ArrayList<PrendaRopa> nuevasPrendas) {
        datos.clear();
        datos.addAll(nuevasPrendas);
        notifyDataSetChanged();
    }


    static class ViewHolder {
        ImageView imagen;
        TextView titulo;
        ImageButton botonMenu;
    }
}
