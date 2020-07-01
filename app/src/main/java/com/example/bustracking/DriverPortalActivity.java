package com.example.bustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class DriverPortalActivity extends AppCompatActivity {
    Button driverlogout;
    Button currentloc;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_portal);
        driverlogout=(Button)findViewById(R.id.button2);
        currentloc=(Button)findViewById(R.id.button5);

        driverlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(DriverPortalActivity.this,DriverActivity.class);
                startActivity(i);


            }
        });

        currentloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DriverPortalActivity.this,MapsActivity3.class);
                startActivity(i);
            }
        });
    }

}
