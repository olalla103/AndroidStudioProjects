package com.example.appgestionpersistencia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appgestionpersistencia.OperacionesElementos.AniadirElemento;
import com.example.appgestionpersistencia.POJO.PrendaRopa;

import java.util.ArrayList;
import java.util.List;

public class ListaElementos extends AppCompatActivity {

    private ListView lista;
    private Adaptador adaptador;
    private ArrayList<PrendaRopa> datos = new ArrayList<>();
    private List<PrendaRopa> datosOriginales; // Guarda la lista completa sin filtros

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elementos);

        // Configuración de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Inicializa las listas
        datos = new ArrayList<>();
        datosOriginales = new ArrayList<>();
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);

        // Datos de ejemplo
        datos.add(new PrendaRopa("Camiseta negra", "M", Estilos.Femenino, 19.99, R.drawable.camisetanegra_mujer));
        datos.add(new PrendaRopa("Vaqueros", "L", Estilos.Neutro, 39.99, R.drawable.vaqueroballoon_mujer));
        datos.add(new PrendaRopa("Bolso", "M", Estilos.Neutro, 61.99, R.drawable.bolsomarron));
        datos.add(new PrendaRopa("Falda plisada", "S", Estilos.Femenino, 19.99, R.drawable.faldaplisadapicos_mujer));
        datos.add(new PrendaRopa("Vestido Lentejuelas", "S", Estilos.Femenino, 35.99, R.drawable.vestidolentejuelas_mujer));
        datos.add(new PrendaRopa("Chaqueta acolchada", "XL", Estilos.Masculino, 59.99, R.drawable.chaquetaacolchada_hombre));
        datos.add(new PrendaRopa("Chaqueta cuero", "L", Estilos.Neutro, 99.99, R.drawable.chaquetacuero_hombre));

        // Copiar datos originales
        datosOriginales.addAll(datos);

        adaptador = new Adaptador(this, R.layout.entrada, datos);
        lista.setAdapter(adaptador);

    }

    private void configurarSearchView(androidx.appcompat.widget.SearchView searchView) {
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Acción al enviar la búsqueda
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtra los elementos conforme se escribe
                if (newText.isEmpty()) {
                    datos.clear();
                    datos.addAll(datosOriginales); // Restaura la lista original
                    adaptador.notifyDataSetChanged();
                } else {
                    filtrarPrendas(newText);
                }
                return true;
            }
        });
    }


    // Inflar el menú de la Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        // Obtiene el SearchView desde el item del menú
        MenuItem searchItem = menu.findItem(R.id.action_search);
        // Obtén la vista SearchView asociada al item del menú
        androidx.appcompat.widget.SearchView searchView =
                (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        // Llama al método y le pasa el SearchView obtenido
        if (searchView != null) {
            configurarSearchView(searchView); // Llamada al método configurando el SearchView
        } else {
            Log.e("ListaElementos", "SearchView es nulo"); // Debug si falla
        }

        return true; // Devuelve true para mostrar el menú
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String nombre = data.getStringExtra("nombre");
            String estiloString = data.getStringExtra("estilo");
            String talla = data.getStringExtra("talla");
            double precio = data.getDoubleExtra("precio", 0.0);
            int imagen = data.getIntExtra("imagen", R.drawable.imagen_defecto);
            String imagenUri = data.getStringExtra("imagenUri"); // 🔹 Obtiene la URI si existe

            if (nombre != null && estiloString != null && talla != null) {
                Estilos estilo = Estilos.valueOf(estiloString);
                PrendaRopa nuevaPrenda = new PrendaRopa(nombre, talla, estilo, precio, imagen);

                if (imagenUri != null) {
                    nuevaPrenda.setImagenUri(imagenUri); // 🔹 Asigna la imagen capturada
                }

                datos.add(nuevaPrenda);
                adaptador.notifyDataSetChanged();
            }
        }
    }

    private void filtrarPrendas(String texto) {
        datos.clear();
        for (PrendaRopa prenda : datosOriginales) {
            if (prenda.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                datos.add(prenda);
            }
        }
        adaptador.notifyDataSetChanged();
    }


    private void actualizarPrenda(int position, String nombre, String estilo, String talla, double precio, int imagen) {
        if (position >= 0 && nombre != null && estilo != null && talla != null) {
            PrendaRopa prenda = datos.get(position);
            prenda.setNombre(nombre);
            prenda.setEstilo(Estilos.valueOf(estilo));
            prenda.setTalla(talla);
            prenda.setPrecio(precio);
            prenda.setImagen(imagen);
            adaptador.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show();
        }
    }


}
