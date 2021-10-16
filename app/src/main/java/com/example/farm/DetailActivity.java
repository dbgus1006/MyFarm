package com.example.farm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    TextView outputATemp, outputAHum, outputGHum;
    Button selectPotButton;

    phpDown task;
    ArrayList<DataList> dataList = new ArrayList<DataList>();
    DataList Item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        task = new phpDown();

        selectPotButton = (Button)findViewById(R.id.selectPotButton);
        outputATemp = (TextView)findViewById(R.id.outputATemp);
        outputAHum = (TextView)findViewById(R.id.outputAHum);
        outputGHum = (TextView)findViewById(R.id.outputGHum);



        task.execute("http://dbgus1006.ivyro.net/Detail.php");

    }

    private class phpDown extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
           StringBuilder jsonHtml = new StringBuilder();
           try {
               // 연결 url 설정
               URL url = new URL(urls[0]);
               // 커넥션 객체 생성
               HttpURLConnection conn = (HttpURLConnection)url.openConnection();
               // 연결되었으면
               if (conn != null)
               {
                   conn.setConnectTimeout(10000);
                   conn.setUseCaches(false);
                   // 연결되었음 코드가 리턴되면
                   if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
                   {
                       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                       for(;;)
                       {
                           // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장
                           String line = br.readLine();
                           if(line == null) break;
                           // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                           jsonHtml.append(line + "\n");
                       }
                       br.close();
                   }
                   conn.disconnect();
               }

           } catch (Exception ex) {
               ex.printStackTrace();
           }
           return jsonHtml.toString();
        }

        protected void onPostExecute(String str)
        {
            String potID;
            float potGHUM;
            float potATemp;
            float potAHum;
            boolean potWCON;
            boolean potVCON;
            String userID;

            try {
                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results");
                for (int i = 0; i < ja.length(); i++)
                {
                    JSONObject jo = ja.getJSONObject(i);
                    potID = jo.getString("potID");
                    potGHUM = Float.parseFloat(jo.getString("potGHUM"));
                    potATemp = Float.parseFloat(jo.getString("potATemp"));
                    potAHum = Float.parseFloat(jo.getString("potAHum"));
                    potWCON = Boolean.parseBoolean(jo.getString("potWCON"));
                    potVCON = Boolean.parseBoolean(jo.getString("potVCON"));
                    userID = jo.getString("userID");

                    dataList.add(new DataList(potID, potGHUM, potATemp, potAHum, potWCON, potVCON, userID));
                }
            } catch(JSONException e)
            {
                e.printStackTrace();
            }

            selectPotButton.setText(dataList.get(0).getData(0));
            outputATemp.setText("대기 온도: " + dataList.get(0).getData(2));
            outputAHum.setText("대기 습도: " + dataList.get(0).getData(3));
            outputGHum.setText("수분: " + dataList.get(0).getData(1));
        }

    }


}
