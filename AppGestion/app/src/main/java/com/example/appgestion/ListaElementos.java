package com.example.appgestion;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        texto = (TextView) findViewById(R.id.texto);
        ArrayList<MainActivity.Encapsulador> datos = new ArrayList<MainActivity.Encapsulador>();

        // tengo que cambiar las imágenes
        datos.add(new ListaElementos.Encapsulador(R.drawable.camisetanegra, "Camiseta negra", "Camiseta negra de manga larga y tejido de canalé (textura acanalada). Tiene un escote redondeado y amplio, con un pequeño detalle de lazo decorativo en la parte superior del centro. Es ajustada al cuerpo.", true));
        datos.add(new MainActivity.Encapsulador(R.drawable.vestidolentejuelas, "Vestido lentejuelas", "El vestido está decorado completamente con lentejuelas multicolor que forman un patrón brillante en tonos rosados, dorados y plateados. Es un vestido midi con un corte ajustado y detalles distintivos: el escote es cruzado en la parte superior, dejando una pequeña abertura en el centro del pecho, y tiene un diseño halter con tiras que se atan en el cuello. ", false));
        datos.add(new MainActivity.Encapsulador(R.drawable.bolsomarron, "Bolso marrón cuero", "Diseño tipo hobo, amplio y funcional. Está en cuero de color marrón oscuro, con un acabado liso y elegante. Cuenta con dos cremalleras decorativas en la parte frontal que añaden un toque moderno", false));
        datos.add(new MainActivity.Encapsulador(R.drawable.vaqueroballoon, "Vaquero Balloon", "Corte relajado tipo \"balloon\" con cinco bolsillos, costuras torcidas y cierre de cremallera con botón metálico. Su diseño combina comodidad y estilo, con un fit amplio y cintura de tiro medio", false));
        datos.add(new MainActivity.Encapsulador(R.drawable.botasojales, "Botín ojales", "Diseño moderno y audaz, con un tacón alto estilizado y ojales metálicos decorativos que añaden un toque de vanguardia", false));
        datos.add(new MainActivity.Encapsulador(R.drawable.faldaplisadapicos, "Falda plisada picos", "Con un diseño de estilo midi con un corte asimétrico que presenta un bajo en picos, lo que añade un toque moderno y fluido a su apariencia. Está confeccionada en un tejido ligero y suave que permite un movimiento elegante, ideal para combinar con prendas casuales o más formales", false));


        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen);
                    RadioButton miRadio = (RadioButton) view.findViewById(R.id.boton);

                    MainActivity.Encapsulador encapsulador = (MainActivity.Encapsulador) entrada; // Obtener el objeto Encapsulador actual

                    // Configurar los datos
                    texto_superior_entrada.setText(encapsulador.get_textoTitulo());
                    texto_inferior_entrada.setText(encapsulador.get_textoContenido());
                    imagen_entrada.setImageResource(encapsulador.get_idImagen());

                    // Configurar el listener del RadioButton para mostrar la descripción en lugar de "MARCADA UNA OPCION"
                    miRadio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Desmarcar el RadioButton anterior si existe
                            if (radioButton_pulsado != null) radioButton_pulsado.setChecked(false);

                            // Marcar el nuevo RadioButton y guardarlo como el seleccionado
                            radioButton_pulsado = miRadio;
                            radioButton_pulsado.setChecked(true); // Asegurar que el actual se marque

                            // Mostrar la descripción del elemento seleccionado
                            texto.setText(encapsulador.get_textoContenido());
                        }
                    });
                }
            }
        });
    }
    class Encapsulador {
        private int imagen;
        private String titulo, texto;
        private boolean dato1;

        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, boolean favorito) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.texto = textoContenido;
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