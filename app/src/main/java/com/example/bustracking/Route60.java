package com.example.bustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Route60 extends AppCompatActivity {
    Button routemap;
    Button getloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route60);
        routemap=(Button)findViewById(R.id.button3);
        getloc=(Button)findViewById(R.id.button7);
        routemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Route60.this,MapsActivity2.class);
                startActivity(i);
            }
        });
        getloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Route60.this,MapsActivity5.class);
                startActivity(i);
            }
        });

    }
}
