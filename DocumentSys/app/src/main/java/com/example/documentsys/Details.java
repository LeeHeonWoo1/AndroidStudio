package com.example.documentsys;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Details extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;
    private String userName;
    private String userGrade;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);

        TextView docNum = findViewById(R.id.docNumber);
        TextView isApproved = findViewById(R.id.isApproved);
        TextView mainContent = findViewById(R.id.mainContent);
        TextView writer = findViewById(R.id.onWriter);
        TextView writeDate = findViewById(R.id.onWriteDate);
        TextView sender = findViewById(R.id.sender);

        Intent fromDocList = getIntent();
        userName = fromDocList.getStringExtra("userName");
        userGrade = fromDocList.getStringExtra("userGrade");
        title = fromDocList.getStringExtra("title");

        TextView userNameText = findViewById(R.id.userNameLayout);
        userNameText.setText("환영합니다. " + userName + " " + userGrade + "님!");

        TextView signLayout = findViewById(R.id.signLayout);
        ImageView signImg = findViewById(R.id.signMark);

        try{
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String key = title.substring(0, 4);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DOCUMENT WHERE DOC_NAME LIKE '%"+key+"%'");
            StringBuffer approved = new StringBuffer();
            StringBuffer docnum = new StringBuffer();
            StringBuffer writeDateBuffer = new StringBuffer();
            StringBuffer senderBuffer = new StringBuffer();
            StringBuffer writerBuffer = new StringBuffer();
            StringBuffer mainBuffer = new StringBuffer();

            while(resultSet.next()){
                mainBuffer.append("\n" + resultSet.getString(1) + "\n\n");
                approved.append(resultSet.getString(2));
                writeDateBuffer.append(resultSet.getString(3));
                writerBuffer.append(resultSet.getString(4));
                senderBuffer.append(resultSet.getString(5));
                docnum.append(resultSet.getString(6));
                mainBuffer.append(resultSet.getString(7));
            }

            docNum.setText(docnum.toString());
            isApproved.setText(approved.toString());
            mainContent.setText(mainBuffer.toString());
            writer.setText(writerBuffer.toString());
            writeDate.setText(writeDateBuffer.toString());
            sender.setText(senderBuffer.toString());

            if (isApproved.getText().toString().equals("결재 완료")){
                signImg.setVisibility(View.VISIBLE);
            }else{
                signImg.setVisibility(View.INVISIBLE);
            }

            docnum.delete(0, -1);
            approved.delete(0, -1);
            mainBuffer.delete(0, -1);
            writerBuffer.delete(0, -1);
            writeDateBuffer.delete(0, -1);
            senderBuffer.delete(0, -1);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    public void onClickedDelete(View view){
//        TextView mainContent = findViewById(R.id.mainContent);
//
//        try{
//            Class.forName(DRIVER);
//            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//            String query;
//
//            if (title.contains("...")){
//                String key = title.substring(0, title.indexOf("..."));
//                query = "DELETE FROM DOCUMENT WHERE DOC_NAME LIKE '%"+key+"%'";
//            }else {
//                query = "DELETE FROM DOCUMENT WHERE DOC_NAME='" + title + "'";
//            }
//
//            Statement statement = connection.createStatement();
//            statement.executeQuery(query);
//
//            Toast.makeText(getApplicationContext(), "삭제가 완료되었습니다.", Toast.LENGTH_LONG).show();
//        }catch (Exception e){
//            mainContent.setText(e.toString());
//        }
//    }
}