package com.example.smartlockersystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainMenu extends AppCompatActivity{

    Button b1;
    Button b2;
    Button b3;
    String mmpid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        Intent intent = getIntent();

        mmpid = intent.getExtras().getString("user_id");


        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), ReservationLocation.class);
                intent1.putExtra("user_id", mmpid);
                startActivity(intent1);
        }
        });

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), ReservationMy.class);
                intent2.putExtra("user_id", mmpid);
                startActivity(intent2);
                //startActivity(intent2);
            }
        });

        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), QuestionandAnswer.class);
                startActivity(intent3);
            }
        });
    }
}