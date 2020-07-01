package com.example.bustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   // TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //imageView=(ImageView)findViewById(R.id.mainlogoimage);
        //textView=(TextView)findViewById(R.id.croponsync);
        button=(Button)findViewById(R.id.gotoappbtn);

        // imageView.animate().alpha(1).setDuration(2000);
       // textView.animate().alpha(1).setDuration(2000);
        button.animate().alpha(1).setDuration(2000);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserDriverActivity.class);
                startActivity(intent);
            }
        });
    }
}

