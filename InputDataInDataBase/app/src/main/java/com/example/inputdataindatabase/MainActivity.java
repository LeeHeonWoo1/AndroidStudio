package com.example.inputdataindatabase;

import androidx.appcompat.app.AppCompatActivity;

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
        TextView cusID = findViewById(R.id.cusID);
        TextView cusName = findViewById(R.id.cusName);
        TextView cusPhone = findViewById(R.id.phone);
        TextView cusBirth = findViewById(R.id.birth);
        TextView result = findViewById(R.id.result);
        Spinner subject = findViewById(R.id.subjects);

        String id = cusID.getText().toString();
        String name = cusName.getText().toString();
        String phone = cusPhone.getText().toString();
        String birth = cusBirth.getText().toString();
        String query = "";

        switch (subject.getSelectedItem().toString()){
            case "입력":
                query = "INSERT INTO MART_CUS(CUS_ID, CUS_NAME, TEL, BIRTH) VALUES('"+id+"','"+name+
                        "','"+phone+"','"+birth+"')";
                break;
            case "전체 조회":
                query = "SELECT * FROM MART_CUS ORDER BY CUS_ID";
                break;
            case "삭제":
                query = "DELETE FROM MART_CUS WHERE CUS_ID="+id;
                break;
            case "수정":
                id = cusID.getText().toString();
                name = cusName.getText().toString();
                phone = cusPhone.getText().toString();
                birth = cusBirth.getText().toString();

                query = "UPDATE MART_CUS SET CUS_NAME='"+name+"',TEL='"+phone+"',BIRTH='"+birth+"'"+" WHERE CUS_ID="+id;
                break;
        }

        try {
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            StringBuffer stringBuffer = new StringBuffer();
            ResultSet resultSet = statement.executeQuery(query);

            if (! subject.getSelectedItem().toString().equals("전체 조회"))
                result.setText(subject.getSelectedItem().toString() + " 완료 !");
            else {
                while (resultSet.next()) {
                    stringBuffer.append(resultSet.getString(1) + "  ");
                    stringBuffer.append(resultSet.getString(2) + "  ");
                    stringBuffer.append(resultSet.getString(3) + "  ");
                    stringBuffer.append(resultSet.getString(4) + "  ");
                    stringBuffer.append("\n");
                }
                result.setText(String.format("%-10s", stringBuffer.toString()));
            }

        }catch (Exception e){
            result.setText(e.toString());
        }
    }
}