package com.example.appgestion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ListaElementos extends AppCompatActivity {

    private ListView lista;
    private Adaptador adaptador;
    ArrayList<Encapsulador> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elementos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);
        // Inicializar los datos (lista vacía al principio)
        adaptador = new Adaptador(this, R.layout.entrada, datos);
        lista.setAdapter(adaptador);

        // Configurar el listener de clics
        setupItemClickListener();

        // Configurar el listener de clic largo
        setupItemLongClickListener();


        // Agregar algunos elementos de ejemplo
        datos.add(new Encapsulador(R.drawable.camisetanegra_mujer, "Camiseta negra", "Femenino", "Camiseta básica negra, ideal para cualquier ocasión", true));
        datos.add(new Encapsulador(R.drawable.vestidolentejuelas_mujer, "Vestido lentejuelas", "Femenino", "Vestido de lentejuelas, colorido y divertido", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.bolsomarron, "Bolso marrón cuero", Estilos.mujer.name(), "Bolso espacioso y duradero gracias a su gran material", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.vaqueroballoon_mujer, "Vaquero Balloon", Estilos.mujer.name(), "Vaqueros para el día a día, anchos y cómodos", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.botasojales_mujer, "Botín ojales", Estilos.mujer.name(), "Botas de tacón, atrevidas para dar un toque informal al conjunto", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.faldaplisadapicos_mujer, "Falda plisada picos", Estilos.mujer.name(), "Falda básica de fondo de armario", false));


    }

    private void setupItemLongClickListener() {
        lista.setOnItemLongClickListener((parent, view, position, id) -> {
            // Eliminar el elemento de la lista
            Encapsulador elemento = datos.get(position);
            datos.remove(position);
            adaptador.notifyDataSetChanged();

            // Mostrar un Toast indicando que el elemento fue eliminado
            Toast.makeText(ListaElementos.this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
            return true; // Devuelve true para indicar que el clic largo ha sido manejado
        });
    }


    private void setupItemClickListener() {
        lista.setOnItemClickListener((parent, view, position, id) -> {
            // Obtener el elemento clickeado
            Encapsulador elemento = datos.get(position);
            String descripcion = elemento.get_descripcion();

            // Mostrar la descripción en un Toast
            Toast.makeText(ListaElementos.this, "Descripción: " + descripcion, Toast.LENGTH_SHORT).show();

            // Registrar el clic en el log
            Log.d("ITEM_CLICK", "Elemento clickeado en la posición: " + position);
        });
    }


    // Inflar menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    // Al seleccionar añadir y eliminar en el menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.aniadir) {
            // Acción para añadir un elemento
            Intent intent = new Intent(this, AniadirElemento.class);
            startActivityForResult(intent, 1);
            return true;
        } else if (item.getItemId() == R.id.eliminar) {
            Toast.makeText(this, "Toque prolongadamente un elemento de la lista para eliminarlo", Toast.LENGTH_LONG).show();
            // Lógica para eliminar un elemento
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Recibir los datos del Intent cuando se regresa de AniadirElemento
    // En ListaElementos.java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Recuperar los datos
            String nombre = data.getStringExtra("nombre");
            String descripcion = data.getStringExtra("descripcion");
            String estilo = data.getStringExtra("estilo");

            // Crear un nuevo objeto Encapsulador
            Encapsulador nuevoElemento = new Encapsulador(R.drawable.imagen_defecto, nombre, estilo, descripcion, false);

            // Agregar el nuevo elemento a la lista
            datos.add(nuevoElemento);

            // Notificar al adaptador que la lista ha cambiado
            adaptador.notifyDataSetChanged();  // Esto debería refrescar la lista
        }
    }


    // Clase encapsuladora
    class Encapsulador {
        private int imagen;
        private String titulo;
        private String descripcion; // Nueva propiedad para la descripción
        private Estilos estilo;
        private boolean favorito;

        // Constructor actualizado
        public Encapsulador(int idImagen, String textoTitulo, String descripcion, String estilo, boolean favorito) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.descripcion = descripcion; // Asignar la descripción
            if (estilo.equals("Mujer")) {
                this.estilo = Estilos.mujer;
            }
            if (estilo.equals("Hombre")) {
                this.estilo = Estilos.hombre;
            }
            if (estilo.equals("Neutro")) {
                this.estilo = Estilos.neutro;
            }
            this.favorito = favorito;
        }

        public String get_textoTitulo() {
            return titulo;
        }

        public String get_descripcion() {
            return descripcion;  // Getter para la descripción
        }

        public Estilos get_estilo() {
            return estilo;
        }

        public int get_idImagen() {
            return imagen;
        }

        public boolean get_favorito() {
            return favorito;
        }
    }

}
