package com.example.inputdataindatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InputNewData extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;
    static int userID = 105;
    int newCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_new_data_layout);

        // 보안정책 관련
        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);
    }

    public void onClickedBtn(View view){
        TextView userName = findViewById(R.id.userName);
        TextView userBirth = findViewById(R.id.userBirth);
        TextView userPhone = findViewById(R.id.userPhone);

        String username = userName.getText().toString();
        String birth = userBirth.getText().toString();
        String phone = userPhone.getText().toString();

        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String query = "INSERT INTO MART_CUS(CUS_ID, CUS_NAME, TEL, BIRTH) VALUES(" + userID + ", '" +
                    username +"', '"+ phone + "', '" + birth + "')";
            statement.executeQuery(query);
            userID++;
            newCnt++;
            Toast.makeText(getApplicationContext(), "신규 등록 완료 !", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
//            result.setText(e.toString());
        }
    }

    public void onClickedToMain(View view){
        Intent toMain = new Intent(this, MainActivity.class);

        toMain.putExtra("newCnt", newCnt);
        startActivity(toMain);
    }
}
