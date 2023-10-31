package com.example.inputdataindatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 보안정책 관련
        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);

        TextView totalCnt = findViewById(R.id.totalCnt);
        TextView newTotalCnt = findViewById(R.id.newCnt);

        Intent fromInputNewData = getIntent();
        int newCnt = fromInputNewData.getIntExtra("newCnt", 0);
        newTotalCnt.setText(String.valueOf(newCnt));

        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MART_CUS");
            StringBuffer stringBuffer = new StringBuffer();

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1) + " ");
                stringBuffer.append(resultSet.getString(2) + " ");
                stringBuffer.append(resultSet.getString(3) + " ");
                stringBuffer.append(resultSet.getString(4) + " ");
                stringBuffer.append("\n");
            }

            totalCnt.setText(String.valueOf(stringBuffer.toString().split("\n").length));
        }catch (Exception e){
            totalCnt.setText(e.toString());
        }
    }

    public void onClickedGetAllData(View view){
        Intent getAll = new Intent(this, GetAllData.class);
        startActivity(getAll);
    }
    public void onClickedInputNewData(View view){
        Intent inputNewData = new Intent(this, InputNewData.class);
        startActivity(inputNewData);
    }
    public void onClickedDeleteData(View view){
        Intent deleteData = new Intent(this, DeleteData.class);
        startActivity(deleteData);
    }
    public void onClickedModifyData(View view){
        Intent modifyData = new Intent(this, ModifyData.class);
        startActivity(modifyData);
    }
}