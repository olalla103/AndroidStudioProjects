package com.example.appgestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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


        // Agregar algunos elementos de ejemplo
        datos.add(new Encapsulador(R.drawable.camisetanegra_mujer, "Camiseta negra", Estilos.Femenimo.name(), "Camiseta básica negra, ideal para cualquier ocasión", true));
        datos.add(new Encapsulador(R.drawable.vestidolentejuelas_mujer, "Vestido lentejuelas", Estilos.Femenimo.name(), "Vestido de lentejuelas, colorido y divertido", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.bolsomarron, "Bolso marrón cuero", Estilos.Femenimo.name(), "Bolso espacioso y duradero gracias a su gran material", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.vaqueroballoon_mujer, "Vaquero Balloon", Estilos.Femenimo.name(), "Vaqueros para el día a día, anchos y cómodos", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.botasojales_mujer, "Botín ojales", Estilos.Femenimo.name(), "Botas de tacón, atrevidas para dar un toque informal al conjunto", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.faldaplisadapicos_mujer, "Falda plisada picos", Estilos.Femenimo.name(), "Falda básica de fondo de armario", false));


    }

    // Inflar menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    // Recibir los datos del Intent cuando se regresa de AniadirElemento
    // En ListaElementos.java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                // Recuperar los datos
                String nombre = data.getStringExtra("nombre");
                String descripcion = data.getStringExtra("descripcion");
                String estilo = data.getStringExtra("estilo");

                // Verificar que los datos no sean nulos o vacíos
                if (nombre != null && !nombre.isEmpty() &&
                        descripcion != null && !descripcion.isEmpty() &&
                        estilo != null && !estilo.isEmpty()) {

                    // Crear un nuevo objeto Encapsulador
                    Encapsulador nuevoElemento = new Encapsulador(
                            R.drawable.imagen_defecto,
                            nombre,
                            estilo,
                            descripcion,
                            false
                    );

                    // Agregar el nuevo elemento a la lista
                    datos.add(nuevoElemento);

                    // Notificar al adaptador que la lista ha cambiado
                    adaptador.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "Datos incompletos recibidos", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Mensaje opcional para el caso en que el resultado sea cancelado
                Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }



  /*  @Override
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
    }*/


    // Clase encapsuladora
    class Encapsulador {
        private final int imagen;
        private final String titulo;
        private final String descripcion; // Nueva propiedad para la descripción
        private Estilos estilo;
        private final boolean favorito;

        // Constructor actualizado
        public Encapsulador(int idImagen, String textoTitulo, String descripcion, String estilo, boolean favorito) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.descripcion = descripcion; // Asignar la descripción
            if (estilo.equals("Mujer")) {
                this.estilo = Estilos.Femenimo;
            }
            if (estilo.equals("Hombre")) {
                this.estilo = Estilos.Masculino;
            }
            if (estilo.equals("Neutro")) {
                this.estilo = Estilos.Neutro;
            }
            this.favorito = favorito;
        }

        // Getters
        public int get_imagen() {
            return imagen;
        }

        public String get_titulo() {
            return titulo;
        }

        public String get_descripcion() {
            return descripcion;
        }

        public Estilos get_estilo() {
            return estilo;
        }

        public boolean is_favorito() {
            return favorito;
        }
    }


}
