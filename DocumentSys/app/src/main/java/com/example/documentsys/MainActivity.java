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

public class MainActivity extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;

    @Override // 굳이 필요하진 않지만 오버라이딩 중이라는걸 명시적으로 선언만 한다.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 보안정책 관련
        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);
    }

    public void onClickedLogin(View view){
        TextView userID = findViewById(R.id.userID);
        TextView userPassword = findViewById(R.id.userPassword);

        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT USER_PW, USER_NAME, GRADE FROM DOCUMENTSYS WHERE USER_ID='"+userID.getText().toString()+"'");
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            StringBuffer stringBuffer3 = new StringBuffer();

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1));
                stringBuffer2.append(resultSet.getString(2));
                stringBuffer3.append(resultSet.getString(3));
            }

            if (userPassword.getText().toString().equals(stringBuffer.toString())){
                Toast.makeText(getApplicationContext(), "로그인 성공 :)", Toast.LENGTH_SHORT).show();

                Intent writeDocument = new Intent(this, MenuList.class);
                writeDocument.putExtra("userName", stringBuffer2.toString());
                writeDocument.putExtra("userGrade", stringBuffer3.toString());
                startActivity(writeDocument);
            }else{
                Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호를 다시 확인하세요.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickedToSignUp(View view){
        Intent toSignUp = new Intent(this, SignUp.class);

        startActivity(toSignUp);
    }
}
