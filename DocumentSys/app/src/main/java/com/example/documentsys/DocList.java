package com.example.documentsys;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TableLayout;
import android.graphics.Typeface;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DocList extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;
    private String userName;
    private String userGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_list);

        StrictMode.ThreadPolicy threadpolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadpolicy);

        Intent fromMenuList = getIntent();
        userName = fromMenuList.getStringExtra("userName");
        userGrade = fromMenuList.getStringExtra("userGrade");

        TextView userNameText = findViewById(R.id.userNameLayout);
        userNameText.setText("환영합니다. " + userName+ " " + userGrade + "님!");

        try{
            Class.forName(DRIVER); // DRIVER
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DOC_NAME, WRITER, TO_CHAR(WRITE_DATE, 'YYYY-MM-DD') FROM DOCUMENT ORDER BY WRITE_DATE");
            StringBuffer stringBuffer = new StringBuffer();

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1) + ",");
                stringBuffer.append(resultSet.getString(2) + ",");
                stringBuffer.append(resultSet.getString(3) + ",");
            }
            String[] newData = stringBuffer.toString().split(",");

            int idx = 0;
            for (int i = 0; i < newData.length / 3; i++) {
                TextView newTitle = new TextView(getApplicationContext());
                newTitle.setWidth(188);
                if (newData[idx].length() >= 13){
                    newTitle.setText(newData[idx].replace(newData[idx].substring(11), "..."));
                }else{
                    newTitle.setText(newData[idx] + "\n");
                }
                newTitle.setTextSize(17);
                newTitle.setTypeface(Typeface.SERIF, Typeface.BOLD);

                TextView newWriter = new TextView(getApplicationContext());
                newWriter.setWidth(108);
                newWriter.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                newWriter.setText(newData[idx+1] + "\n");
                newWriter.setTextSize(17);
                newWriter.setTypeface(Typeface.SERIF, Typeface.BOLD);

                TextView newWriteDate = new TextView(getApplicationContext());
                newWriteDate.setWidth(117);
                newWriteDate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                newWriteDate.setText(newData[idx+2] + "\n");
                newWriteDate.setTextSize(17);
                newWriteDate.setTypeface(Typeface.SERIF, Typeface.BOLD);

                TableRow newRow = new TableRow(getApplicationContext());
                newRow.addView(newTitle);
                newRow.addView(newWriter);
                newRow.addView(newWriteDate);

                newRow.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent toDetail = new Intent(DocList.this, Details.class);

                        toDetail.putExtra("title", newTitle.getText().toString());
                        toDetail.putExtra("userName", userName);
                        toDetail.putExtra("userGrade", userGrade);
                        startActivity(toDetail);
                    }
                });

                TableLayout table = findViewById(R.id.tableLayout);
                table.addView(newRow);

                idx += 3;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickedLogout(View view){
        Intent toMain = new Intent(this, MainActivity.class);

        startActivity(toMain);
    }
}