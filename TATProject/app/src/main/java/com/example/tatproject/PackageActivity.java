package com.example.tatproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PackageActivity extends AppCompatActivity {
    // 본인 DB계정으로 변경
    private static final String DRIVER = "oracle.jdbc.OracleDriver";

    private static final String URL = "jdbc:oracle:thin:@192.168.30.4:1521:xe";
    private static final String USERNAME = "c##group";
    private static final String PASSWORD = "1234";
    private Connection connection;
    private String username;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        username = getIntent().getStringExtra("username");
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
        TableLayout table = findViewById(R.id.tableLayout);
        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String query = "SELECT PACK_ID,PACK_NAME, PRICE FROM CREATE_PACK";

            ResultSet resultSet = statement.executeQuery(query);
            StringBuffer stringBuffer = new StringBuffer();

            while (resultSet.next()){
                stringBuffer.append(resultSet.getString(1)+","); // 패키지 명
                stringBuffer.append(resultSet.getString(2)+","); // 가격
                stringBuffer.append(resultSet.getString(3)+",");
            }
            String[] newData = stringBuffer.toString().split(","); // 각 행을 담기 위한 배열 ,로 구분

            int idx = 0;
            for(int i = 0; i < newData.length / 3; i++){
                TextView packID = new TextView(getApplicationContext());
                packID.setWidth(80);
                packID.setHeight(90);
                packID.setText(newData[idx]+"\n");
                packID.setTextSize(17);
                packID.setTypeface(Typeface.SERIF,Typeface.BOLD);

                TextView packTitle = new TextView(getApplicationContext());
                packTitle.setWidth(200);
                packTitle.setHeight(90);
                if(newData[idx+1].length() >= 9){
                    packTitle.setText(newData[idx+1].replace(newData[idx+1].substring(9),"..."));
                }else{
                    packTitle.setText(newData[idx+1]+"\n");
                }
                packTitle.setTextSize(17);
                packTitle.setTypeface(Typeface.SERIF, Typeface.BOLD);
                packTitle.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

                TextView packPrice = new TextView(getApplicationContext());
                packPrice.setWidth(130);
                packPrice.setHeight(90);
                packPrice.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                packPrice.setText(newData[idx+2]+"원" + "\n");
                packPrice.setTextSize(17);
                packPrice.setTypeface(Typeface.SERIF,Typeface.BOLD);

                TableRow newRow = new TableRow(getApplicationContext());

                newRow.addView(packID);
                newRow.addView(packTitle);
                newRow.addView(packPrice);

                newRow.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(PackageActivity.this, PackDetailActivity.class);

                        intent.putExtra("title", packTitle.getText().toString());
                        intent.putExtra("price", packPrice.getText().toString());
                        intent.putExtra("id", packID.getText().toString());
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }
                });


                stringBuffer.setLength(0);
                table.addView(newRow);

                idx += 3;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickedToMain(View view){
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }

    public void onClickedToMyPage(View view){

    }

    public void onClickedToLogin(View view){
        Intent toLogin = new Intent(this, LoginActivity.class);

        startActivity(toLogin);
    }

    public void onClickedSearch(View view){
        TableLayout tb = findViewById(R.id.tableLayout);
        tb.removeAllViews();
        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            TextView searchKeyword = findViewById(R.id.searchKeyword);
            String key = searchKeyword.getText().toString();

            Statement statement = connection.createStatement();
            String query = "SELECT PACK_ID,PACK_NAME, PRICE FROM CREATE_PACK WHERE PACK_NAME LIKE '%" + key + "%'";

            ResultSet resultSet = statement.executeQuery(query);
            StringBuffer stringBuffer = new StringBuffer();

            while (resultSet.next()){
                stringBuffer.append(resultSet.getString(1)+","); // 패키지 명
                stringBuffer.append(resultSet.getString(2)+","); // 가격
                stringBuffer.append(resultSet.getString(3)+",");
            }
            String[] newData = stringBuffer.toString().split(","); // 각 행을 담기 위한 배열 ,로 구분

            int idx = 0;

            for(int i = 0; i < newData.length / 3; i++){
                TextView packID = new TextView(this);
                packID.setWidth(60);
                packID.setText(newData[idx]+"\n");
                packID.setTextSize(17);
                packID.setTypeface(Typeface.SERIF,Typeface.BOLD);

                TextView packTitle = new TextView(this);
                packTitle.setWidth(220);
                packTitle.setHeight(150);
                if(newData[idx+1].length() >= 9){
                    packTitle.setText(newData[idx+1].replace(newData[idx+1].substring(9),"..."));
                }else{
                    packTitle.setText(newData[idx+1] + "\n");
                }

                packTitle.setTextSize(17);
                packTitle.setTypeface(Typeface.SERIF, Typeface.BOLD);

                TextView packPrice = new TextView(this);
                packPrice.setWidth(130);
                packPrice.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                packPrice.setText(newData[idx+2]+"원\n");
                packPrice.setTextSize(18);
                packPrice.setTypeface(Typeface.SERIF,Typeface.BOLD);


                TableRow newRow = new TableRow(getApplicationContext());
                newRow.addView(packID);
                newRow.addView(packTitle);
                newRow.addView(packPrice);

                newRow.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(PackageActivity.this, PackDetailActivity.class);

                        intent.putExtra("title", packTitle.getText().toString());
                        intent.putExtra("price", packPrice.getText().toString());
                        intent.putExtra("id", packID.getText().toString());
                        startActivity(intent);
                    }
                });

                TableLayout table = findViewById(R.id.tableLayout);
                table.addView(newRow);

                idx += 3;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
