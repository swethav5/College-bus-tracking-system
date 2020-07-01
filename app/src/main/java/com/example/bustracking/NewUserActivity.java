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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewUserActivity extends AppCompatActivity {
    public EditText emailid,password,username;
    Button signupbtn;
    TextView textViewloginhere;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        mFirebaseAuth=FirebaseAuth.getInstance();
        mydatabase=FirebaseDatabase.getInstance();
        emailid=(EditText)findViewById(R.id.emaileditText);
        password=(EditText)findViewById(R.id.passwordeditText);
        username=(EditText)findViewById(R.id.editText4);
        signupbtn=(Button)findViewById(R.id.SignUpbutton);
        textViewloginhere=(TextView)findViewById(R.id.alreadyhaveaccounttextView);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailid.getText().toString(),pass=password.getText().toString();
                String user=username.getText().toString();
                final CustomFields storeobject=new CustomFields(email,pass,user);
                if(email.isEmpty())
                {
                    emailid.setError("Please enter Email Id");
                    emailid.requestFocus();
                }
                else if(pass.isEmpty())
                {
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                else if(email.isEmpty()&&pass.isEmpty())
                {
                    Toast.makeText(NewUserActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty()&&pass.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(NewUserActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(NewUserActivity.this, "Error!!Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                String user_ID=mFirebaseAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db=FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(user_ID);
                                current_user_db.setValue(storeobject);
                                startActivity(new Intent(NewUserActivity.this,UserPortalActivity.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(NewUserActivity.this, "Error!!Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewloginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(NewUserActivity.this,UserLoginActivity.class);
                startActivity(i);
            }
        });

    }
}
