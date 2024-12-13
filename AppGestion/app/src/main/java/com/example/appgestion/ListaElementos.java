package com.example.appgestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ListaElementos extends AppCompatActivity {

    private ListView lista;
    private Adaptador adaptador;
    private ArrayList<PrendaRopa> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elementos);

        // Configuración de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);

        // Datos de ejemplo
        datos.add(new PrendaRopa("Camiseta negra", "M", Estilos.Femenino, 19.99, R.drawable.camisetanegra_mujer));
        datos.add(new PrendaRopa("Vaqueros", "L", Estilos.Neutro, 39.99, R.drawable.vaqueroballoon_mujer));
        datos.add(new PrendaRopa("Chaqueta", "XL", Estilos.Masculino, 59.99, R.drawable.chaquetaacolchada_hombre));

        adaptador = new Adaptador(this, R.layout.entrada, datos);
        lista.setAdapter(adaptador);
    }

    // Inflar el menú de la Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    // Manejar las opciones seleccionadas del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.aniadir) {
            // Abrir la actividad para añadir un nuevo elemento
            Intent intent = new Intent(this, AniadirElemento.class);
            startActivityForResult(intent, 1); // Código de solicitud 1
            return true;
        }

        if (id == R.id.ver_estadisticas) {
            Intent intent = new Intent(this, EstadisticasActivity.class);
            intent.putExtra("datos", datos); // Pasa la lista de prendas
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Recuperar datos del Intent
            String nombre = data.getStringExtra("nombre");
            String talla = data.getStringExtra("talla");
            String estiloString = data.getStringExtra("estilo");
            double precio = data.getDoubleExtra("precio", 0.0);
            int imagen = data.getIntExtra("imagen", R.drawable.imagen_defecto);

            if (nombre != null && talla != null && estiloString != null) {
                Estilos estilo = Estilos.valueOf(estiloString);
                PrendaRopa nuevaPrenda = new PrendaRopa(nombre, talla, estilo, precio, imagen);
                datos.add(nuevaPrenda);
                adaptador.notifyDataSetChanged();
            }
        }
    }

}
