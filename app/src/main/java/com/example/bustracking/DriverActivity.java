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

import java.sql.Driver;

public class DriverActivity extends AppCompatActivity {
    public EditText demail,dpassword,dusername,dRouteNo;
    Button dsignup;
    TextView dtextviewloginhere;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        mFirebaseAuth=FirebaseAuth.getInstance();
        demail=(EditText)findViewById(R.id.driveremaileditText);
        dpassword=(EditText)findViewById(R.id.driverpasswordeditText);
        dusername=(EditText)findViewById(R.id.driverusernameeditText);
        dRouteNo=(EditText)findViewById(R.id.RouteNo);
        dsignup=(Button)findViewById(R.id.driversignupbutton);
        dtextviewloginhere=(TextView)findViewById(R.id.alreadyhaveanaccountofdrivertextView);

        dsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String driveremail=demail.getText().toString(),driverpassword=dpassword.getText().toString();
                String driverusername=dusername.getText().toString();
                String Route=dRouteNo.getText().toString();
                final DriverCustomFields object=new DriverCustomFields(driveremail,driverpassword,driverusername,Route);
                if(driveremail.isEmpty())
                {
                    demail.setError("Please enter Email Id");
                    demail.requestFocus();
                }
                else if(driverpassword.isEmpty())
                {
                    dpassword.setError("Please enter password");
                    dpassword.requestFocus();
                }
                else if(Route.isEmpty())
                {
                    dRouteNo.setError("Please enter Route Number");
                    dRouteNo.requestFocus();
                }
                else if(driveremail.isEmpty()&&driverpassword.isEmpty())
                {
                    Toast.makeText(DriverActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(driveremail.isEmpty()&&driverpassword.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(driveremail,driverpassword).addOnCompleteListener(DriverActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(DriverActivity.this, "Error!! Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                String driver_id=mFirebaseAuth.getCurrentUser().getUid();
                                DatabaseReference current_driver= FirebaseDatabase.getInstance().getReference().child("Users").child("Riders").child(driver_id);
                                current_driver.setValue(object);
                                startActivity(new Intent(DriverActivity.this,MapsActivity3.class));
                            }
                        }
                    });
                }

                else
                {
                    Toast.makeText(DriverActivity.this, "Error!! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dtextviewloginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DriverActivity.this,CabDriversActivity.class);
                startActivity(intent);
            }
        });
    }
}
