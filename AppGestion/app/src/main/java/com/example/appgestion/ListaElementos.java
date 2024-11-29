package com.example.appgestion;

import android.os.Bundle;
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
        datos.add(new ListaElementos.Encapsulador(R.drawable.vestidolentejuelasMujer, "Vestido lentejuelas", false));
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

//                    // Configurar el listener del RadioButton para mostrar la descripción en lugar de "MARCADA UNA OPCION"
//                    miRadio.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            // Desmarcar el RadioButton anterior si existe
//                            if (radioButton_pulsado != null) radioButton_pulsado.setChecked(false);
//
//                            // Marcar el nuevo RadioButton y guardarlo como el seleccionado
//                            radioButton_pulsado = miRadio;
//                            radioButton_pulsado.setChecked(true); // Asegurar que el actual se marque
//
//                            // Mostrar la descripción del elemento seleccionado
//                            texto.setText(encapsulador.get_textoContenido());
//                        }
//                    });

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

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Acción al presionar el icono (puedes abrir un DrawerLayout o mostrar un mensaje)
            Toast.makeText(this, "Menú hamburguesa pulsado", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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