package com.example.connectdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@myIPv4Address:port:xe";
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
    }

    public void onClickedBtn(View view){
        TextView sal = findViewById(R.id.salary);
        TextView fir = findViewById(R.id.firstName);
        TextView last = findViewById(R.id.lastName);

        String firstName = fir.getText().toString();
        String lastName = last.getText().toString();

        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 접속

            Statement statement = connection.createStatement();
            StringBuffer stringBuffer = new StringBuffer(); // 문자열을 보내기 위함
            ResultSet resultSet = statement.executeQuery("SELECT SALARY FROM EMPLOYEES WHERE FIRST_NAME='" +
                    ""+firstName+"' AND LAST_NAME='"+lastName+"'"); // 쿼리문 세팅

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1)); // 2번째 인덱스에 위치한 열을 가져온다.
            }

            sal.setText(stringBuffer.toString());

        }catch (Exception e){
            sal.setText(e.toString());
        }
    }
}