package com.example.inputdataindatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteData extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_data);

        // 보안정책 관련
        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);

        Button deleteBtn = findViewById(R.id.button3);
        Button checkBtn = findViewById(R.id.button2);
        deleteBtn.setVisibility(View.INVISIBLE);
        checkBtn.setVisibility(View.VISIBLE);
    }

    public void onClickedCheckBtn(View view){
        TextView userName = findViewById(R.id.userName2);
        TextView result = findViewById(R.id.deleteResult);
        String username = userName.getText().toString();

        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String query = "SELECT CUS_ID, CUS_NAME, TEL, TO_CHAR(BIRTH, 'YYYY-MM-DD') FROM MART_CUS WHERE CUS_NAME='"+username+"'";
            ResultSet resultSet = statement.executeQuery(query);
            StringBuffer stringBuffer = new StringBuffer();

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1) + "  ");
                stringBuffer.append(resultSet.getString(2) + "  ");
                stringBuffer.append(resultSet.getString(3) + "  ");
                stringBuffer.append(resultSet.getString(4) + "  ");
            }

            result.setText(stringBuffer.toString() + "\n 삭제 버튼을 누르면 삭제됩니다.\n삭제하시겠습니까?");

            Button deleteBtn = findViewById(R.id.button3);
            Button checkBtn = findViewById(R.id.button2);
            deleteBtn.setVisibility(View.VISIBLE);
            checkBtn.setVisibility(View.INVISIBLE);

        }catch (Exception e){
            result.setText("해당 이름을 가진 회원은 존재하지 않습니다.");
        }
    }

    public void onClickedDeleteBtn(View view){
        TextView userName = findViewById(R.id.userName2);
        TextView result = findViewById(R.id.deleteResult);
        String username = userName.getText().toString();

        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String query = "DELETE FROM MART_CUS WHERE CUS_NAME='"+username+"'";
            statement.executeQuery(query);
            result.setText("삭제가 완료되었습니다.");

        }catch (Exception e){
            result.setText(e.toString());
        }
    }

    public void onClickedToMain(View view){
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }
}
