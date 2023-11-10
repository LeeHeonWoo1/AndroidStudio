package com.example.tatproject;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.URL;

public class PackDetailActivity extends AppCompatActivity {

    // 본인 DB계정으로 변경
    private static final String DRIVER = "oracle.jdbc.OracleDriver";

    private static final String URL = "jdbc:oracle:thin:@192.168.30.4:1521:xe";
    private static final String USERNAME = "c##group";
    private static final String PASSWORD = "1234";
    private Connection connection;

    private String username;

    ImageView imageView;
    Bitmap bitmap;

    String durl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_detail);

        Intent fromMain = getIntent();
        this.username = fromMain.getStringExtra("username");

        TextView logedout = findViewById(R.id.logedout);
        TextView login = findViewById(R.id.logined);

        if (username.equals("")){
            logedout.setVisibility(View.VISIBLE);
            login.setVisibility(View.INVISIBLE);
        }else {
            logedout.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
        }

        //보안정책

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        TextView title_tv = findViewById(R.id.titleTv);
        TextView date_tv = findViewById(R.id.dateTv);
        TextView price_tv = findViewById(R.id.priceTv);
        TextView content_tv = findViewById(R.id.mainTv);

        imageView = findViewById(R.id.urlImg);
        //안드로이드에서 네트워크와 관련된 작업을 할 때,
        //반드시 메인 Thread가 아닌 별도의 작업 Thread를 생성하여 작업해야 한다.

        try{
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            StringBuffer urlBuffer = new StringBuffer();
            StringBuffer titleBuffer = new StringBuffer();
            StringBuffer dateStartBuffer = new StringBuffer();
            StringBuffer dateEndBuffer = new StringBuffer();
            StringBuffer contentBuffer = new StringBuffer();
            StringBuffer priceBuffer = new StringBuffer();

            Intent getIntent = getIntent();
            String title = getIntent.getStringExtra("title");
            String id = getIntent.getStringExtra("id");

            ResultSet resultSet = statement.executeQuery
                    ("SELECT PACK_NAME, to_char(JOURNEY_START,'yyyy-mm-dd'),CONTENTS,PRICE,IMG_URL" +
                            ",to_char(JOURNEY_END,'yyyy-mm-dd') FROM CREATE_PACK WHERE PACK_ID = "+id);

            while(resultSet.next()){
                titleBuffer.append(resultSet.getString(1));
                dateStartBuffer.append(resultSet.getString(2));
                contentBuffer.append(resultSet.getString(3));
                priceBuffer.append(resultSet.getString(4));
                urlBuffer.append(resultSet.getString(5));
                dateEndBuffer.append(resultSet.getString(6));
            }
            durl =  urlBuffer.toString();
            title_tv.setText(titleBuffer.toString());
            price_tv.setText(priceBuffer.toString()+"원");
            date_tv.setText("일정: " +dateStartBuffer.toString()+"~"+dateEndBuffer.toString());
            content_tv.setText(contentBuffer.toString());


            // Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
        }


        // 새로운 스레드를 생성하고 이미지 url 불러오기
        Thread uThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(durl);

                    // web에서 이미지를 가져와 ImageView에 저장할 Bitmap을 만든다.
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true); // 서버로부터 응답 수신
                    conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        uThread.start();

        try{
            //메인 Thread는 별도의 작업 Thread가 작업을 완료할 때까지 대기해야 한다.
            //join() 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다리도록 한다.
            //join() 메서드는 InterruptedException을 발생시킨다.
            uThread.join();

            //작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
            //UI 작업을 할 수 있는 메인 Thread에서 ImageView에 이미지 지정
            imageView.setImageBitmap(bitmap);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
