package com.example.appgestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ListaElementos extends AppCompatActivity {

    private ListView lista;
    private TextView texto;
    private RadioButton radioButton_pulsado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_elementos);

        lista = (ListView) findViewById(R.id.lista);
        ArrayList<Encapsulador> datos = new ArrayList<>();

        // tengo que cambiar las imágenes
        datos.add(new ListaElementos.Encapsulador(R.drawable.camisetanegra_mujer, "Camiseta negra", true));
        datos.add(new ListaElementos.Encapsulador(R.drawable.vestidolentejuelas_mujer, "Vestido lentejuelas", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.bolsomarron, "Bolso marrón cuero", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.vaqueroballoon_mujer, "Vaquero Balloon", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.botasojales_mujer, "Botín ojales", false));
        datos.add(new ListaElementos.Encapsulador(R.drawable.faldaplisadapicos_mujer, "Falda plisada picos", false));


        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen);

                    Encapsulador encapsulador = (Encapsulador) entrada; // Obtener el objeto Encapsulador actual

                    // Configurar los datos
                    texto_superior_entrada.setText(encapsulador.get_textoTitulo());
                    imagen_entrada.setImageResource(encapsulador.get_idImagen());
                }
            }
        });
        // Configurar la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el icono de hamburguesa
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu); // Reemplaza con tu ícono
        }
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
            // Intent para ir a la otra actividad
            Intent intent = new Intent(this, AniadirElemento.class);
            startActivity(intent);
            Toast.makeText(this, "Añadiendo elemento", Toast.LENGTH_SHORT).show();
            // Lógica para añadir un elemento a la lista
            lista.setAdapter(new Adaptador(this, R.layout.entrada, datos)); // tengo que coger los elementos de la otra clase

            return true;
        } else if (item.getItemId() == R.id.eliminar) {
            // Acción para eliminar un elemento
            Toast.makeText(this, "Eliminando elemento", Toast.LENGTH_SHORT).show();
            // Lógica para eliminar un elemento de la lista
            return true;
        } else {
            return false;
        }
    }


    // Clase encapsuladora
    class Encapsulador {
        private int imagen;
        private String titulo, texto;
        private boolean dato1;

        public Encapsulador(int idImagen, String textoTitulo, boolean favorito) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.dato1 = favorito;
        }

        public String get_textoTitulo() {
            return titulo;
        }

        public String get_textoContenido() {
            return texto;
        }

        public int get_idImagen() {
            return imagen;
        }

        public boolean get_checkBox1() {
            return dato1;
        }
    }
}