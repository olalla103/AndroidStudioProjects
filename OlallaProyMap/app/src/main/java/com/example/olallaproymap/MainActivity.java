package com.example.olallaproymap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private Location currentLocation;
    private static final int REQUEST_CODE = 101;
    private Button btnBuscar, btnCambiarMapa;
    private AutoCompleteTextView searchBox;
    private boolean isSatellite = false;
    private RequestQueue requestQueue;

    private static final String API_KEY = "TU_API_KEY_DE_GOOGLE_MAPS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requestQueue = Volley.newRequestQueue(this);
        fetchLocation();

        btnBuscar = findViewById(R.id.btnBuscar);
        btnCambiarMapa = findViewById(R.id.btnCambiarMapa);
        searchBox = findViewById(R.id.searchBox);

        String[] places = {"restaurant", "hotel", "gas_station", "hospital", "atm", "supermarket", "park", "pharmacy"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, places);
        searchBox.setAdapter(adapter);

        btnBuscar.setOnClickListener(v -> buscarLugares());
        btnCambiarMapa.setOnClickListener(v -> cambiarTipoMapa());
    }

    private void fetchLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location;
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(MainActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng userLocation = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Ubicación actual"));
    }

    private void buscarLugares() {
        String tipo = searchBox.getText().toString().trim();
        if (tipo.isEmpty()) {
            Toast.makeText(this, "Introduce un tipo de lugar", Toast.LENGTH_SHORT).show();
            return;
        }

        mMap.clear(); // Limpiar el mapa

        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                "location=" + currentLocation.getLatitude() + "," + currentLocation.getLongitude() +
                "&radius=1000" + // Radio en metros (1km)
                "&type=" + tipo +
                "&key=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray results = response.getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject place = results.getJSONObject(i);
                            JSONObject location = place.getJSONObject("geometry").getJSONObject("location");

                            double lat = location.getDouble("lat");
                            double lng = location.getDouble("lng");
                            String nombre = place.getString("name");

                            LatLng latLng = new LatLng(lat, lng);
                            mMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title(nombre)
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(this, "Error al obtener lugares", Toast.LENGTH_SHORT).show());

        requestQueue.add(request);
    }

    private void cambiarTipoMapa() {
        mMap.setMapType(isSatellite ? GoogleMap.MAP_TYPE_NORMAL : GoogleMap.MAP_TYPE_SATELLITE);
        isSatellite = !isSatellite;
    }
}
