package com.example.bustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserPortalActivity extends AppCompatActivity {
    Button mylogout;
    TextView txt1;
    TextView txt2;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portal);
        mylogout=(Button)findViewById(R.id.button);
        txt1=(TextView) findViewById(R.id.textView4);
        txt2=(TextView) findViewById(R.id.textView6);

        mylogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(UserPortalActivity.this,NewUserActivity.class);
                startActivity(i);
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserPortalActivity.this,Route12.class);
                startActivity(i);

            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserPortalActivity.this,Route60.class);
                startActivity(i);
            }
        });


    }

}
