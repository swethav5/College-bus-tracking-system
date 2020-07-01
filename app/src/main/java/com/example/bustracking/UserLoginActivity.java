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

public class UserLoginActivity extends AppCompatActivity {
    EditText lemailid,lpassword;
    Button loginbtn;
    TextView signuphere;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        mFirebaseAuth=FirebaseAuth.getInstance();
        lemailid=(EditText)findViewById(R.id.emaileditTextlogin);
        lpassword=(EditText)findViewById(R.id.passwordeditTextlogin);
        loginbtn=(Button)findViewById(R.id.Loginbutton);
        signuphere=(TextView)findViewById(R.id.donthaveaccounttextView);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser!=null)
                {
                    Toast.makeText(UserLoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(UserLoginActivity.this,UserPortalActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(UserLoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }

            }
        };
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=lemailid.getText().toString(),pass1=lpassword.getText().toString();
                if(email1.isEmpty())
                {
                    lemailid.setError("Please enter Email ID");
                    lemailid.requestFocus();
                }
                else if(pass1.isEmpty())
                {
                    lpassword.setError("Please enter password");
                    lpassword.requestFocus();
                }
                else if(email1.isEmpty()&&pass1.isEmpty())
                {
                    Toast.makeText(UserLoginActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(email1.isEmpty()&&pass1.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email1,pass1).addOnCompleteListener(UserLoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!task.isSuccessful())
                            {
                                Toast.makeText(UserLoginActivity.this, "Login Error!!Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent i=new Intent(UserLoginActivity.this,UserPortalActivity.class);
                                startActivity(i);
                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(UserLoginActivity.this, "Login Error!!!!Try Again", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signuphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserLoginActivity.this,NewUserActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
