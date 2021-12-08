package com.example.smartlockersystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.util.Calendar;
import java.util.Date;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ReservationMy extends AppCompatActivity {

    String smid; // 유저 아이디
    TextView smtv;
    Calendar cal;
    int crday; //현재 yymmddhh
    String test;

    private static String TAG = "phpquerytest";

    private static final String TAG_JSON="Locler";
    //private static final String TAG_ID = "id";
    private static final String TAG_S_DAY = "s_day";
    private static final String TAG_L_day ="l_day";

    private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mListViewList;
    EditText mEditTextSearchKeyword1, mEditTextSearchKeyword2;
    String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_reservation);

        Intent intent = getIntent();
        smid = intent.getExtras().getString("user_id");
        cal = Calendar.getInstance();
        cal.setTime(new Date());

        crday = cal.get(Calendar.YEAR) * 1000000 + (cal.get(Calendar.MONTH) + 1) * 10000 + cal.get(Calendar.DATE) * 100 + cal.get(Calendar.HOUR) - 2000000000;

        mTextViewResult = (TextView) findViewById(R.id.textView_main_result);
        mListViewList = (ListView) findViewById(R.id.listView_main_list);
        //mEditTextSearchKeyword1 = (EditText) findViewById(R.id.editText_main_searchKeyword1);
        //mEditTextSearchKeyword2 = (EditText) findViewById(R.id.editText_main_searchKeyword2);


        Button button_search = (Button) findViewById(R.id.button_main_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mArrayList.clear();


                GetData task = new GetData();
                task.execute(smid, String.valueOf(crday));
            }
        });
        mArrayList = new ArrayList<>();
    }

    private class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ReservationMy.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //result.split()

            char[] s = result.toCharArray();

            String answer;

            if(String.valueOf(s[5]).equals("3")) {
                answer = "현 시간이후의 예약기록이 없습니다.";
                //String cs_day = String.valueOf(s[49]) + String.valueOf(s[50]) + String.valueOf(s[51]) + String.valueOf(s[52]) + String.valueOf(s[53]) + String.valueOf(s[54]) + String.valueOf(s[55]) + String.valueOf(s[56]);
                //String cl_day = String.valueOf(s[81]) + String.valueOf(s[82]) + String.valueOf(s[83]) + String.valueOf(s[84]) + String.valueOf(s[85]) + String.valueOf(s[86]) + String.valueOf(s[87]) + String.valueOf(s[88]);
                //answer = "예약을 찾았습니다. " + cs_day + "부터 " + cl_day + "까지 예약하셨습니다.(yymmddhh)";
            }
            else{
                String cs_day = String.valueOf(s[49]) + String.valueOf(s[50]) + String.valueOf(s[51]) + String.valueOf(s[52]) + String.valueOf(s[53]) + String.valueOf(s[54]) + String.valueOf(s[55]) + String.valueOf(s[56]);
                String cl_day = String.valueOf(s[81]) + String.valueOf(s[82]) + String.valueOf(s[83]) + String.valueOf(s[84]) + String.valueOf(s[85]) + String.valueOf(s[86]) + String.valueOf(s[87]) + String.valueOf(s[88]);
                answer = "예약을 찾았습니다. " + cs_day + "부터 " + cl_day + "까지 예약하셨습니다.(yymmddhh)";
            }

            //mTextViewResult.setText(String.valueOf(s[0]));
            mTextViewResult.setText(answer);
            //mTextViewResult.setText(String.valueOf(s[80])+String.valueOf(s[82])+String.valueOf(s[84])+String.valueOf(s[86])+String.valueOf(s[88])+String.valueOf(s[90])+String.valueOf(s[92])+String.valueOf(s[94]));
            Log.d(TAG, "response - " + result);

            if (result == null){

                mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String searchKeyword1 = params[0];
            String searchKeyword2 =params[1];//Integer.valueOf(params[1]);

            String serverURL = "http://118.221.14.79/remyquery.php";
            String postParameters = "u_id=" + searchKeyword1 + "&cr_day=" + searchKeyword2;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

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
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){
        //TextView mTextViewResult1;
        //mTextViewResult1 = findViewById(R.id.textView4);
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                //String id = item.getString(TAG_ID);
                String s_day = item.getString(TAG_S_DAY);
                String l_day = item.getString(TAG_L_day);
                //String vie = "예약하셨습니다. 예약시작일은 "+s_day+" 이고 예약끝일은 "+l_day+"입니다.";

                //mTextViewResult1.setText(vie);
                //Toast.makeText(this, s_day, Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, l_day, Toast.LENGTH_SHORT).show();

                HashMap<String,String> hashMap = new HashMap<>();

                //hashMap.put(TAG_ID, id);
                hashMap.put(TAG_S_DAY, s_day);
                hashMap.put(TAG_L_day, l_day);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    ReservationMy.this, mArrayList, R.layout.item_list,
                    new String[]{TAG_S_DAY, TAG_L_day},
                    new int[]{R.id.textView_list_id, R.id.textView_list_name, R.id.textView_list_num}
            );

            mListViewList.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}
