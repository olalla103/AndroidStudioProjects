package com.example.olallaproymap;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.olallaproymap.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private static final String API_KEY = "AIzaSyCBmHyIjqETu1QEfrSLVrGJgxKMiTYPAHw"; // Coloca tu API Key de Google Places aquí

    FusedLocationProviderClient fusedLocationClient;
    private GoogleMap mMap;
    private com.example.olallaproymap.databinding.ActivityMapsBinding binding;
    private LatLng ubicacionActual;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    private Button btnToggleMap, btnBuscar;
    private EditText etSearch;
    private boolean isSatelliteView = false; // Alternar entre vistas
    private RequestQueue requestQueue; // Para Volley

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener el fragmento del mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Inicializar el cliente de ubicación
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requestQueue = Volley.newRequestQueue(this);

        // Referencia a los elementos de UI
        btnToggleMap = findViewById(R.id.btnToggleMap);
        etSearch = findViewById(R.id.etSearch);
        btnBuscar = findViewById(R.id.btnBuscar);

        // Alternar vista de mapa
        btnToggleMap.setOnClickListener(v -> toggleMapType());

        // Evento para buscar lugares
        btnBuscar.setOnClickListener(v -> buscarLugares());

        obtenerUbicacionActual();
    }

    private void toggleMapType() {
        if (mMap != null) {
            if (isSatelliteView) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                btnToggleMap.setText("Vista Satélite");
                Toast.makeText(this, "Mapa en vista normal", Toast.LENGTH_SHORT).show();
            } else {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                btnToggleMap.setText("Vista Normal");
                Toast.makeText(this, "Mapa en vista satélite", Toast.LENGTH_SHORT).show();
            }
            isSatelliteView = !isSatelliteView; // Alternar estado
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void obtenerUbicacionActual() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                ubicacionActual = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionActual, 15));
                mMap.addMarker(new MarkerOptions().position(ubicacionActual).title("Mi ubicación"));
            }
        });
    }

    private void buscarLugares() {
        String tipoLugar = etSearch.getText().toString().trim();

        if (tipoLugar.isEmpty()) {
            Toast.makeText(this, "Ingrese un lugar a buscar", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ubicacionActual == null) {
            Toast.makeText(this, "Esperando ubicación actual...", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                "location=" + ubicacionActual.latitude + "," + ubicacionActual.longitude +
                "&radius=5000" +
                "&type=" + tipoLugar +
                "&key=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray results = response.getJSONArray("results");
                        mMap.clear(); // Limpiar marcadores anteriores

                        for (int i = 0; i < results.length(); i++) {
                            JSONObject place = results.getJSONObject(i);
                            JSONObject location = place.getJSONObject("geometry").getJSONObject("location");
                            String name = place.getString("name");

                            LatLng placeLocation = new LatLng(location.getDouble("lat"), location.getDouble("lng"));
                            mMap.addMarker(new MarkerOptions().position(placeLocation).title(name));
                        }

                        if (results.length() > 0) {
                            JSONObject firstPlace = results.getJSONObject(0);
                            JSONObject firstLocation = firstPlace.getJSONObject("geometry").getJSONObject("location");
                            LatLng firstLatLng = new LatLng(firstLocation.getDouble("lat"), firstLocation.getDouble("lng"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 14));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MapsActivity.this, "Error procesando los datos", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(MapsActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show());

        requestQueue.add(request);
    }
}
