package com.example.smartlockersystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ReservationLocation extends AppCompatActivity{

    String smid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_reservation);

        Intent intent = getIntent();
        smid = intent.getExtras().getString("user_id");

        //Intent intent1 = new Intent(getApplicationContext(), MainMenu.class);
        //intent1.putExtra("user_id", smid);
        //startActivity(intent1);

        Button baegdo = (Button) findViewById(R.id.button4);
        baegdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), SelectLocker.class);
                intent1.putExtra("user_id", smid);
                startActivity(intent1);
            }
        });
    }
}
