package com.example.mysplash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.mysplash.Service.DbContras;
import com.example.mysplash.json.MyInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    GoogleMap mMap;
    Button regresar;
    TextView lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        Intent intent = getIntent();
        Object object = null;
        MyInfo info = null;
        lat = findViewById(R.id.Lat);
        lon = findViewById(R.id.Long);
        int idusu;
        object = intent.getExtras().get("MyInfo");
        info = (MyInfo) object;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        regresar = findViewById(R.id.button3);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object object = null;
                MyInfo info = null;
                int i = 0;
                DbContras contrasbd = null;
                contrasbd = new DbContras(getBaseContext());
                object = intent.getExtras().get("MyInfo");
                info = (MyInfo) object;
                Intent intent2 = new Intent(mapa.this, principal.class);
                intent2.putExtra("MyInfo", info);
                startActivity(intent2);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);
        String latitud = getIntent().getStringExtra("Latitud");
        String longitud = getIntent().getStringExtra("Longitud");
        lat = findViewById(R.id.Lat);
        lon = findViewById(R.id.Long);
        lat.setText("Latitud: "+latitud);
        lon.setText("Longitud: "+longitud);
        LatLng mexico = new LatLng(Double.parseDouble(latitud),Double.parseDouble(longitud));
        mMap.addMarker(new MarkerOptions().position(mexico).title("ubicacion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }
}