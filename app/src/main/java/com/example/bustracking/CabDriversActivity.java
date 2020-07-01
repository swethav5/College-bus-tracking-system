package com.example.bustracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CabDriversActivity extends AppCompatActivity {

    EditText cabdriveremail,cabdriverpassword;
    TextView cabdriversignup;
    Button cabdriverloginbtn;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_drivers);
        mFirebaseAuth=FirebaseAuth.getInstance();
        cabdriveremail=(EditText)findViewById(R.id.editTextcabdriveremail);
        cabdriverpassword=(EditText)findViewById(R.id.editTextcabdriverpassword);
        cabdriversignup=(TextView)findViewById(R.id.textViewcabtosignup);
        cabdriverloginbtn=(Button)findViewById(R.id.buttoncabdriver);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser!=null)
                {
                    Toast.makeText(CabDriversActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(CabDriversActivity.this,DriverPortalActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(CabDriversActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        cabdriverloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cabemail=cabdriveremail.getText().toString(),cabpassword=cabdriverpassword.getText().toString();
                if(cabemail.isEmpty())
                {
                    cabdriveremail.setError("Please enter Email Id");
                    cabdriveremail.requestFocus();
                }
                else if(cabpassword.isEmpty())
                {
                    cabdriverpassword.setError("Please enter password");
                    cabdriverpassword.requestFocus();
                }
                else if(cabemail.isEmpty()&&cabpassword.isEmpty())
                {
                    Toast.makeText(CabDriversActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(cabemail.isEmpty()&&cabpassword.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(cabemail,cabpassword).addOnCompleteListener(CabDriversActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(CabDriversActivity.this, "Login Error!! Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent i=new Intent(CabDriversActivity.this, sharelocation.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(CabDriversActivity.this, "Login Error!!!! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cabdriversignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CabDriversActivity.this,DriverActivity.class);
                startActivity(i);
            }
        });
    }

}

