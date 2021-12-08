package com.example.smartlockersystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class SelectLocker extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;
    String mmpid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locker_select);

        Intent intent = getIntent();
        mmpid = intent.getExtras().getString("user_id");

        //Toast.makeText(this, mmpid, Toast.LENGTH_SHORT).show();

        Button b1 = (Button) findViewById(R.id.sa1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(getApplicationContext(), SaRe1.class);
                intent11.putExtra("user_id", mmpid);
                startActivity(intent11);
            }
        });

        Button b2 = (Button) findViewById(R.id.sa2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), SaRe2.class);
                intent2.putExtra("user_id", mmpid);
                startActivity(intent2);
                //startActivity(intent2);
            }
        });

        Button b3 = (Button) findViewById(R.id.sa3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), SaRe3.class);
                intent3.putExtra("user_id", mmpid);
                startActivity(intent3);
            }
        });
    }
}
