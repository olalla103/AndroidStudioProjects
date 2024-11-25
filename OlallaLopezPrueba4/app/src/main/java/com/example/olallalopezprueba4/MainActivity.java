package com.example.olallalopezprueba4;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private TextView texto;
    private WebView webView; // WebView para cargar las páginas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lista);
        texto = findViewById(R.id.texto_datos);
        webView = findViewById(R.id.webView);

        // Configuramos WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Habilitar JavaScript si es necesario

        ArrayList<Encapsulador> datos = new ArrayList<>();

        // Las descripciones son las mismas porque no me da tiempo
        datos.add(new Encapsulador(R.drawable.caravaggio, "Caravaggio", "Pintor italiano entre los años de 1593 y 1610.", "https://es.wikipedia.org/wiki/Caravaggio"));
        datos.add(new Encapsulador(R.drawable.rafael, "Rafael Sanzio", "Pintor y arquitecto italiano del Renacimiento.", "https://es.wikipedia.org/wiki/Rafael"));
        datos.add(new Encapsulador(R.drawable.velazquez, "Velázquez", "Pintor Barroco español.", "https://es.wikipedia.org/wiki/Diego_Vel%C3%A1zquez"));
        datos.add(new Encapsulador(R.drawable.miguelangel, "Miguel Ángel", "Pintor Barroco español.", "http://es.wikipedia.org/wiki/Miguel_%C3%81ngel"));
        datos.add(new Encapsulador(R.drawable.rembrant, "Rembrant", "Pintor Barroco español.", "http://es.wikipedia.org/wiki/Rembrandt"));
        datos.add(new Encapsulador(R.drawable.boticelli, "Boticelli", "Pintor Barroco español.", "http://es.wikipedia.org/wiki/Sandro_Botticelli"));
        datos.add(new Encapsulador(R.drawable.leonardo, "Leonardo Da Vinci", "Pintor Barroco español.", "http://es.wikipedia.org/wiki/Leonardo_da_Vinci"));
        datos.add(new Encapsulador(R.drawable.renoir, "Renoir", "Pintor Barroco español.", "http://es.wikipedia.org/wiki/Pierre-Auguste_Renoir"));


        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada = view.findViewById(R.id.texto_datos);
                    ImageView imagen_entrada = view.findViewById(R.id.imagen);

                    Encapsulador encapsulador = (Encapsulador) entrada; // Obtener el objeto actual

                    // Configurar los datos
                    texto_superior_entrada.setText(encapsulador.get_textoTitulo());
                    texto_inferior_entrada.setText(encapsulador.get_textoContenido());
                    imagen_entrada.setImageResource(encapsulador.get_idImagen());

                    // Configurar el OnClickListener para la vista completa (abrir el enlace en WebView)
                    view.setOnClickListener(v -> {
                        // Desplazamos la lista para que la entrada seleccionada quede en la parte superior
                        lista.smoothScrollToPosition(0);

                        // Cargar el enlace en el WebView
                        webView.loadUrl(encapsulador.getEnlace());
                    });
                }
            }
        });
    }

    // Clase Encapsulador
    class Encapsulador {
        private int imagen;
        private String titulo, texto, enlace;

        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, String enlace) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.texto = textoContenido;
            this.enlace = enlace;
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

        public String getEnlace() {
            return enlace;
        }
    }
}
