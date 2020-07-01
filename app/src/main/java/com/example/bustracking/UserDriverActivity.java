package com.example.bustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserDriverActivity extends AppCompatActivity {
    Button user,driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_driver);
        user=(Button)findViewById(R.id.userbutton);
        driver=(Button)findViewById(R.id.driverbutton);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserDriverActivity.this,NewUserActivity.class);
                startActivity(intent);
            }
        });
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserDriverActivity.this,DriverActivity.class);
                startActivity(intent);
            }
        });
    }
}

