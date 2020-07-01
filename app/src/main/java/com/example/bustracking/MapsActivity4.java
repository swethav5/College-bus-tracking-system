package com.example.bustracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

//import android.location.Location;
import android.os.Bundle;
import android.util.Log;
//import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity4 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseReference reff;
   // String x,y;
    Double lat,lng;
    //private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps4);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        reff = FirebaseDatabase.getInstance().getReference("12").child("Location");
       ValueEventListener listener=reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lat=  dataSnapshot.child("lat").getValue(Double.class);
                 lng=  dataSnapshot.child("lng").getValue(Double.class);
                    LatLng currentlocation = new LatLng(lat,lng);
                    mMap.addMarker(new MarkerOptions().position(currentlocation).title("Current location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentlocation,14f));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }

        /*         @Override
                                       public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String prevChildKey) {
                                           LatLng newLocation=new LatLng(
                                                   dataSnapshot.child("lat").getValue(Long.class),
                                                   dataSnapshot.child("lng").getValue(Long.class)
                                           );
                                           mMap.addMarker(new MarkerOptions().position(newLocation).title("Current location"));
                                           mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation,14f));


                                       }

                                       @Override
                                       public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                       }

                                       @Override
                                       public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                       }

                                       @Override
                                       public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError databaseError) {

                                       }
                                   });*/


                // Add a marker in Sydney and move the camera

                // mMap.addMarker(new MarkerOptions().position(newLocation).title("Current location"));

    }


   /* //@Override
    public void onLocationChanged(Location location) {
        String msg = "Update Location" +
                Double.toString(location.getLatitude()) + " " +
                Double.toString(location.getLongitude());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        latLng = new LatLng(location.getLatitude(), location.getLongitude());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (location != null) {
            DatabaseReference mdata;
            mdata = FirebaseDatabase.getInstance().getReference();
            mdata.child("Location").child("lat").setValue(location.getLatitude());
            mdata.child("Location").child("lng").setValue(location.getLongitude());

        }
        mMap.clear();
        mapFragment.getMapAsync(this);*/


