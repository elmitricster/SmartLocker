package com.example.smartlockersystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReSa1 extends AppCompatActivity {

    private static String IP_ADDRESS = "118.221.14.79";
    private static String TAG = "phptest";

    private EditText mEditTextName;
    private EditText mEditTextCountry;
    private TextView mTextViewResult;

    String l_num = "1";
    String b_num = "1";
    String u_id;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sa1_re);

        Intent intent = getIntent();
        u_id = intent.getExtras().getString("user_id");


        mEditTextName = (EditText)findViewById(R.id.s_day);
        mEditTextCountry = (EditText)findViewById(R.id.l_day);
        mTextViewResult = (TextView)findViewById(R.id.textView_main_result1);
        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());


        Button buttonInsert = (Button)findViewById(R.id.button22);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mEditTextName.getText().toString();
                String country = mEditTextCountry.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://118.221.14.79/insertquery.php", name,country,l_num,b_num,u_id);


                mEditTextName.setText("");
                mEditTextCountry.setText("");

            }
        });

    }

class InsertData extends AsyncTask<String, Void, String>{
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(ReSa1.this,
                "Please Wait", null, true, true);
    }


    @Override
    protected void onPostExecute(String result) {
       super.onPostExecute(result);

        progressDialog.dismiss();
        mTextViewResult.setText(result);
        Log.d(TAG, "POST response  - " + result);
    }


    @Override
    protected String doInBackground(String... params) {

        String name = (String)params[1];
        String country = (String)params[2];
        String l_num = (String)params[3];
        String b_num = (String)params[4];
        String u_id = (String)params[5];

        String serverURL = (String)params[0];
        String postParameters = "s_day=" + name + "&l_day=" + country + "&l_num=" + l_num + "&b_num=" + b_num + "&u_id=" + u_id;


        try {

            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();


            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(postParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();


            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d(TAG, "POST response code - " + responseStatusCode);

            InputStream inputStream;
            if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            }
            else{
                inputStream = httpURLConnection.getErrorStream();
            }


            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }


            bufferedReader.close();


            return sb.toString();


        } catch (Exception e) {

            Log.d(TAG, "InsertData: Error ", e);

            return new String("Error: " + e.getMessage());
        }
    }
}
}