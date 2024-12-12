package com.example.appgestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ListaElementos extends AppCompatActivity {

    private ListView lista;
    private Adaptador adaptador;
    ArrayList<Encapsulador> datos = new ArrayList<>();
    private int posicionSeleccionada = -1;

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
        datos.add(new Encapsulador(R.drawable.camisetanegra_mujer, "Camiseta negra", "Camiseta básica negra, ideal para cualquier ocasión", Estilos.Femenino.name(), true));
        datos.add(new Encapsulador(R.drawable.vestidolentejuelas_mujer, "Vestido lentejuelas", "Vestido de lentejuelas, colorido y divertido", Estilos.Femenino.name(), false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.bolsomarron, "Bolso marrón cuero", "Bolso espacioso y duradero gracias a su gran material", Estilos.Femenino.name(), false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.vaqueroballoon_mujer, "Vaquero Balloon", "Vaqueros para el día a día, anchos y cómodos", Estilos.Femenino.name(), false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.botasojales_mujer, "Botín ojales", "Botas de tacón, atrevidas para dar un toque informal al conjunto", Estilos.Femenino.name(), false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.faldaplisadapicos_mujer, "Falda plisada picos", "Falda básica de fondo de armario", Estilos.Femenino.name(), false));


        lista.setOnItemClickListener((parent, view, position, id) -> {
            posicionSeleccionada = position;
            view.setSelected(true); // Marcar el elemento como seleccionado visualmente
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.aniadir) {
            // Abrir la actividad para añadir un nuevo elemento
            Intent intent = new Intent(ListaElementos.this, AniadirElemento.class);
            startActivityForResult(intent, 1); // Código de solicitud 1
            return true;
        }

        if (id == R.id.opcion_editar) {
            editarElementoSeleccionado();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void editarElementoSeleccionado() {
        // Supongamos que seleccionaste el elemento con un clic en el ListView
        Encapsulador elementoSeleccionado = datos.get(posicionSeleccionada);

        // Crear un Intent para abrir ModificarElemento
        Intent intent = new Intent(ListaElementos.this, ModificarElemento.class);
        intent.putExtra("imagen", elementoSeleccionado.get_imagen());
        intent.putExtra("titulo", elementoSeleccionado.get_titulo());
        intent.putExtra("descripcion", elementoSeleccionado.get_descripcion());
        intent.putExtra("estilo", elementoSeleccionado.get_estilo().name());
        intent.putExtra("favorito", elementoSeleccionado.is_favorito());
        intent.putExtra("position", posicionSeleccionada);
        startActivityForResult(intent, 2);
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
                // Recuperar los datos recibidos desde otra actividad (para agregar un nuevo elemento)
                String nombre = data.getStringExtra("nombre");
                String descripcion = data.getStringExtra("descripcion");
                String estilo = data.getStringExtra("estilo");

                // Verificar que los datos no sean nulos o vacíos
                if (nombre != null && !nombre.isEmpty() &&
                        descripcion != null && !descripcion.isEmpty() &&
                        estilo != null && !estilo.isEmpty()) {
                    // Crear un nuevo objeto Encapsulador y agregarlo a la lista
                    Encapsulador nuevoElemento = new Encapsulador(
                            R.drawable.imagen_defecto,
                            nombre,
                            estilo,
                            descripcion,
                            false
                    );
                    datos.add(nuevoElemento);
                    adaptador.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "Datos incompletos recibidos", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show();
            }
        }

        // Aquí va el fragmento que has pedido
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            // Recuperar los datos modificados desde ModificarElemento
            String nuevaDescripcion = data.getStringExtra("descripcion");
            String nuevoEstilo = data.getStringExtra("estilo");
            int posicion = data.getIntExtra("position", -1);

            if (posicion >= 0) {
                // Llamar al método del adaptador para actualizar el elemento
                adaptador.actualizarElemento(posicion, nuevaDescripcion, nuevoEstilo);
            }
        }
    }


    // Clase encapsuladora
    class Encapsulador {
        private final int imagen;
        private final String titulo;
        private String descripcion; // Nueva propiedad para la descripción
        private Estilos estilo;
        private final boolean favorito;

        // Constructor actualizado
        public Encapsulador(int idImagen, String textoTitulo, String descripcion, String estilo, boolean favorito) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.descripcion = descripcion; // Asignar la descripción
            if (estilo.equals("Femenino")) {
                this.estilo = Estilos.Femenino;
            }
            if (estilo.equals("Masculino")) {
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

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public void setEstilo(String estilo) {
            if (estilo.equals("Femenino")) {
                this.estilo = Estilos.Femenino;
            } else if (estilo.equals("Masculino")) {
                this.estilo = Estilos.Masculino;
            } else if (estilo.equals("Neutro")) {
                this.estilo = Estilos.Neutro;
            }
        }
    }


}
