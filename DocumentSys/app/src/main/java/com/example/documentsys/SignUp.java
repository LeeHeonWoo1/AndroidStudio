package com.example.documentsys;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignUp extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);
    }

    public void onClickedSignUp(View view) {
        TextView username = findViewById(R.id.userName);
        TextView userID = findViewById(R.id.userID);
        TextView userPassword1 = findViewById(R.id.userPassword);
        TextView userPassword2 = findViewById(R.id.userPassword2);
        TextView userRank = findViewById(R.id.userRank);

        String name = username.getText().toString();
        String id = userID.getText().toString();
        String pw1 = userPassword1.getText().toString();
        String pw2 = userPassword2.getText().toString();
        String rank = userRank.getText().toString();

        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT USER_NAME FROM DOCUMENTSYS";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            StringBuffer stringBuffer = new StringBuffer();

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1) + ",");
            }

            String[] nameList = stringBuffer.toString().split(",");
            List<String> namelist = new ArrayList<>(Arrays.asList(nameList));

            if (namelist.contains(name)){
                Toast.makeText(getApplicationContext(), "이미 등록된 사원입니다. 로그인을 진행해주세요.", Toast.LENGTH_LONG).show();
                Intent toMain = new Intent(this, MainActivity.class);

                startActivity(toMain);
            }else{
                if (pw1.equals(pw2)) {
                    String query2 = "INSERT INTO DOCUMENTSYS(USER_ID, USER_PW, GRADE, USER_NAME) VALUES(" +
                            "'" + id + "', '" + pw1 + "', '" + rank + "', '" + name + "')";
                    Statement statement2 = connection.createStatement();
                    statement2.executeQuery(query2);

                    Toast.makeText(getApplicationContext(), "등록 성공. 로그인 화면으로 이동합니다.", Toast.LENGTH_LONG).show();
                    Intent toMain = new Intent(this, MainActivity.class);

                    startActivity(toMain);
                } else {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
