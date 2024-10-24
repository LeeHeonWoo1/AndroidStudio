package com.example.inputdataindatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetAllData extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getalldata_layout);

        // 보안정책 관련
        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);
    }

    public void onClickedBtn(View view){
        String query = "SELECT CUS_ID, CUS_NAME, TEL, TO_CHAR(BIRTH, 'YYYY-MM-DD') FROM MART_CUS ORDER BY CUS_ID";
        TextView result = findViewById(R.id.result);

        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            StringBuffer stringBuffer = new StringBuffer();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1) + "  ");
                stringBuffer.append(resultSet.getString(2) + "  ");
                stringBuffer.append(resultSet.getString(3) + "  ");
                stringBuffer.append(resultSet.getString(4) + "  ");
                stringBuffer.append("\n\n");
            }

            result.setText(stringBuffer.toString());
        }catch (Exception e){
            result.setText(e.toString());
        }
    }

    public void onClickedToMain(View view){
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }
}
