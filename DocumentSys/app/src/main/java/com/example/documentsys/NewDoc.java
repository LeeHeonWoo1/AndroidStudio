package com.example.documentsys;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class NewDoc extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;
    private String userName;
    private String userGrade;
    private static int docNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_doc);

        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);

        Intent fromMenuList = getIntent();
        userName = fromMenuList.getStringExtra("userName");
        userGrade = fromMenuList.getStringExtra("userGrade");

        TextView userNameText = findViewById(R.id.userNameLayout);
        userNameText.setText("환영합니다. " + userName + " " + userGrade + "님!");

        try{
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT MAX(DOC_NUMBER) FROM DOCUMENT");
            StringBuffer stringBuffer = new StringBuffer();
            while (resultset.next()){
                stringBuffer.append(resultset.getString(1));
            }

            docNum = Integer.parseInt(stringBuffer.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickedSubmit(View view) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = now.format(formatter);

        TextView newTitle = findViewById(R.id.newDocTitle);
        TextView newComp = findViewById(R.id.newDocComp);
        TextView newMainText = findViewById(R.id.newMainText);

        String title = newTitle.getText().toString();
        String comp = newComp.getText().toString();
        String mainText = newMainText.getText().toString();
        String isApproved = "결재 대기";

        try {
            docNum++;

            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO DOCUMENT(DOC_NAME, ISAPPROVED, WRITE_DATE, WRITER, SENDER, DOC_NUMBER, CONTENT)" +
                    " VALUES ('" + title + "', '" + isApproved + "', '" + today + "', '" + userName + "', '" + comp + "', " +
                    docNum + ", '"+ mainText+"')");

            Toast.makeText(getApplicationContext(), "등록이 완료되었습니다.", Toast.LENGTH_LONG).show();

            Intent intent = getIntent();
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "문제가 발생하였습니다.", Toast.LENGTH_LONG).show();
        }
    }
}