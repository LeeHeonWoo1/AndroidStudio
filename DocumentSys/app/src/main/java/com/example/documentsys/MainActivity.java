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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 보안정책 관련
        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);
    }

    public void onClickedLogin(View view){
        // 사용자가 입력하는 ID와 password
        TextView userID = findViewById(R.id.userID);
        TextView userPassword = findViewById(R.id.userPassword);

        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            // ID가 일치하는 행의 password와 사용자의 이름, 직급을 가져온다.
            ResultSet resultSet = statement.executeQuery("SELECT USER_PW, USER_NAME, GRADE FROM DOCUMENTSYS WHERE USER_ID='"+userID.getText().toString()+"'");
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            StringBuffer stringBuffer3 = new StringBuffer();

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1));
                stringBuffer2.append(resultSet.getString(2));
                stringBuffer3.append(resultSet.getString(3));
            }
            // 만약 DB에 등재된 PASSWORD와 일치하다면
            if (userPassword.getText().toString().equals(stringBuffer.toString())){
                // Toast 메세지를 띄우고
                Toast.makeText(getApplicationContext(), "로그인 성공 :)", Toast.LENGTH_SHORT).show();

                // 메뉴 리스트 액티비티로 전환하되, stringExtra로 유저의 이름과 직급을 넘긴다.
                Intent writeDocument = new Intent(this, MenuList.class);
                writeDocument.putExtra("userName", stringBuffer2.toString());
                writeDocument.putExtra("userGrade", stringBuffer3.toString());
                startActivity(writeDocument);
            }else{ // 로그인에 실패하는 경우는
                // 실패 Toast 메세지를 띄운다.
                Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호를 다시 확인하세요.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 사원 등록 액티비티 전환 메소드
    public void onClickedToSignUp(View view){
        Intent toSignUp = new Intent(this, SignUp.class);

        startActivity(toSignUp);
    }
}
