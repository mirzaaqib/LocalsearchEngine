package com.nuppu.LocalSearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ElectricianDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Electrician electrician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician_detail);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        electrician= (Electrician) bundle.getSerializable(Fields.Electrician);

        ImageView profile;
        TextView tName= (TextView) findViewById(R.id.mName);
        TextView tDescription= (TextView) findViewById(R.id.mdescription);
        TextView tDistance= (TextView) findViewById(R.id.distance);
        TextView tPhone= (TextView) findViewById(R.id.phoneNumber);

        tName.setText(electrician.getName());
        tDescription.setText(electrician.getDescription());
        tDistance.setText(electrician.getDistance());
        tPhone.setText(electrician.getNumber());

        SupportMapFragment mapFragment=(SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        LatLng latLng = new LatLng(Double.valueOf(electrician.getLatitude()), Double.valueOf(electrician.getLongitude()));
//        LatLng latLng = new LatLng(22.75, 75.88);
        mMap.addMarker(new MarkerOptions().position(latLng).title(electrician.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
