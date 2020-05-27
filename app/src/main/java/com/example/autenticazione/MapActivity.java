//qui ci sarà la mappa

package com.example.autenticazione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private MapView mapView;

    private ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initUI();

        createMapView(savedInstanceState);

    }

    public void initUI(){
        ibBack=findViewById(R.id.ibBack);
    }

    public void goBackToMainActivity (View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void createMapView(Bundle savedInstanceState){
        Bundle mapViewBundle = null;
        if (savedInstanceState != null)
            //se savedInstanceState è diverso da null si valorizza mapViewBundle
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);

        //stiamo accedendo alla mapView che abbiamo nel nostro layout
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap gmap = googleMap;

        // 45 4 7 sono grosso modo le coordinate di Torino
        LatLng city = new LatLng(45, 7);

        //si aggiunge un marker in versione semplificata
        gmap.addMarker(new MarkerOptions().position(city));
        // si muove la camera su il punto LatLng definito
        gmap.moveCamera(CameraUpdateFactory.newLatLng(city));

    }
}
