package com.example.smartlockersystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SaRe1 extends AppCompatActivity {

    String mmpid;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.re1_sa);

        Intent intent = getIntent();
        mmpid = intent.getExtras().getString("user_id");

        Button b1 = (Button) findViewById(R.id.gore1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(getApplicationContext(), ReSa1.class);
                intent11.putExtra("user_id", mmpid);
                startActivity(intent11);
            }
        });
    }

}
