package com.example.appgestion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private TextView texto;
    private RadioButton radioButton_pulsado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        texto = (TextView) findViewById(R.id.texto);
        ArrayList<Encapsulador> datos = new ArrayList<Encapsulador>();

        // tengo que cambiar las imágenes
        datos.add(new Encapsulador(R.drawable.camisetanegraZara, "Camiseta negra", "Camiseta negra de manga larga y tejido de canalé (textura acanalada). Tiene un escote redondeado y amplio, con un pequeño detalle de lazo decorativo en la parte superior del centro. Es ajustada al cuerpo.", true));
        datos.add(new Encapsulador(R.drawable.vestidoLentejuelasZara, "Vestido lentejuelas", "El vestido está decorado completamente con lentejuelas multicolor que forman un patrón brillante en tonos rosados, dorados y plateados. Es un vestido midi con un corte ajustado y detalles distintivos: el escote es cruzado en la parte superior, dejando una pequeña abertura en el centro del pecho, y tiene un diseño halter con tiras que se atan en el cuello. ", false));
        datos.add(new Encapsulador(R.drawable.vestidoLentejuelasZara, "Bolso marrón cuero", "diseño tipo hobo, amplio y funcional. Está en cuero de color marrón oscuro, con un acabado liso y elegante. Cuenta con dos cremalleras decorativas en la parte frontal que añaden un toque moderno", false));
        datos.add(new Encapsulador(R.drawable.vestidoLentejuelasZara, "Vaquero Balloon", "", false));
        datos.add(new Encapsulador(R.drawable.vestidoLentejuelasZara, "", "", false));
        datos.add(new Encapsulador(R.drawable.vestidoLentejuelasZara, "", "", false));


        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen);
                    RadioButton miRadio = (RadioButton) view.findViewById(R.id.boton);

                    Encapsulador encapsulador = (Encapsulador) entrada; // Obtener el objeto Encapsulador actual

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