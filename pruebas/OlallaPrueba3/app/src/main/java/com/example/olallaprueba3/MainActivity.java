package com.example.olallaprueba3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private ArrayList<Encapsulador> datos;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lista);

        // datos
        datos = new ArrayList<>();
        datos.add(new Encapsulador(R.drawable.im_buho, "Búho", "Búho es el nombre común de aves de la familia Strigidae, del orden de las estrigiformes o aves rapaces nocturnas. Habitualmente designa especies que, a diferencia de las lechuzas, tienen plumas alzadas que parecen orejas.", true));
        datos.add(new Encapsulador(R.drawable.im_colibri, "Colibrí", "Los troquilinos (Trochilinae) son una subfamilia de aves apodiformes de la familia Trochilidae, conocidas vulgarmente como colibríes, quindes, tucusitos, picaflores, chupamirtos, chuparrosas, huichichiquis (idioma nahuatl), mainumby (idioma guaraní) o guanumby. Conjuntamente con las ermitas, que pertenecen a la subfamilia Phaethornithinae, conforman la familia Trochilidae que, en la sistemática de Charles Sibley, se clasifica en un orden propio: Trochiliformes, independiente de los vencejos del orden Apodiformes. La subfamilia Trochilinae incluye más de 100 géneros que comprenden un total de 330 a 340 especies.", false));
        datos.add(new Encapsulador(R.drawable.im_cuervo, "Cuervo", "El cuervo común (Corvus corax) es una especie de ave paseriforme de la familia de los córvidos (Corvidae). Presente en todo el hemisferio septentrional, es la especie de córvido con la mayor superficie de distribución. Con el cuervo de pico grueso, es el mayor de los córvidos y probablemente la paseriforme más pesada; en su madurez, el cuervo común mide entre 52 y 69 centímetros de longitud y su peso varía de 0,69 a 1,7 kilogramos. Los cuervos comunes viven generalmente de 10 a 15 años pero algunos individuos han vivido 40 años. Los juveniles pueden desplazarse en grupos pero las parejas ya formadas permanecen juntas toda su vida, cada pareja defendiendo un territorio. Existen 8 subespecies conocidas que se diferencian muy poco aparentemente, aunque estudios recientes hayan demostrado diferencias genéticas significativas entre las poblaciones de distintas regiones.", false));
        datos.add(new Encapsulador(R.drawable.im_flamenco, "Flamenco", "Los fenicopteriformes (Phoenicopteriformes), los cuales reciben el nombre vulgar de flamencos, son un orden de aves neognatas, con un único género viviente: Phoenicopterus. Son aves que se distribuyen tanto por el hemisferio occidental como por el hemisferio oriental: existen cuatro especies en América y dos en el Viejo Mundo. Tienen cráneo desmognato holorrino, con 16 a 20 vértebras cervicales y pies anisodáctilos.", false));
        datos.add(new Encapsulador(R.drawable.im_kiwi, "Kiwi", "Los kiwis (Apterix, gr. 'sin alas') son un género de aves paleognatas compuesto por cinco especies endémicas de Nueva Zelanda.1 2 Son aves no voladoras pequeñas, aproximadamente del tamaño de una gallina. Antes de la llegada de los humanos alrededor del año 1300, en Nueva Zelanda los únicos mamíferos que había eran murciélagos, y los nichos ecológicos que en otras partes del mundo eran ocupados por animales tan diversos como caballos, lobos y ratones fueron utilizados en Nueva Zelanda por pájaros (y en menor proporción por ciertas especies de reptiles). La denominación kiwi es maorí, idioma del pueblo homónimo de linaje malayopolinesio que colonizó Nueva Zelanda antes de la llegada de los europeos.", false));
        datos.add(new Encapsulador(R.drawable.im_loro, "Loro", "Las Psitácidas (Psittacidae) son una familia de aves psitaciformes llamadas comúnmente loros o papagayos, e incluye a los guacamayos, las cotorras, los periquitos, los agapornis y formas afines.", false));
        datos.add(new Encapsulador(R.drawable.im_pavo, "Pavo", "Pavo es un género de aves galliformes de la familia Phasianidae, que incluye dos especies, el pavo real común (Pavo cristatus) y el pavo real cuelliverde (Pavo muticus).", false));
        datos.add(new Encapsulador(R.drawable.im_pinguino, "Pingüino", "Los pingüinos (familia Spheniscidae, orden Sphenisciformes) son un grupo de aves marinas, no voladoras, que se distribuyen únicamente en el Hemisferio Sur, sobre todo en sus altas latitudes.", false));

        // Adaptador y se asigna a la vista
        adaptador = new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada = view.findViewById(R.id.texto_datos);
                    ImageView imagen_entrada = view.findViewById(R.id.imagen);

                    Encapsulador encapsulador = (Encapsulador) entrada;

                    texto_superior_entrada.setText(encapsulador.get_textoTitulo());
                    texto_inferior_entrada.setText(encapsulador.get_textoContenido());
                    imagen_entrada.setImageResource(encapsulador.get_idImagen());

                }
            }
        };

        lista.setAdapter(adaptador);

        setupItemClickListener();

        setupItemLongClickListener();
    }

    private void setupItemClickListener() {
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Encapsulador itemSeleccionado = datos.get(position);
                Toast.makeText(MainActivity.this, itemSeleccionado.get_textoContenido(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Configuro el evento de long click (eliminar elemento)
    private void setupItemLongClickListener() {
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Elimino el elemento de la lista de datos
                datos.remove(position);
                adaptador.notifyDataSetChanged();
                return true;
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
    }
}
