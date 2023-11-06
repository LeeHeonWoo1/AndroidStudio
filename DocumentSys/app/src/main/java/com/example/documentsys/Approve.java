package com.example.documentsys;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Approve extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@IPv4Address:port:xe";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private Connection connection;
    private String userName;
    private String userGrade;
    private static CheckBox[] checkBoxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve);

        Intent fromMenuList = getIntent();
        userName = fromMenuList.getStringExtra("userName");
        userGrade = fromMenuList.getStringExtra("userGrade");

        TextView userNameLayout = findViewById(R.id.userNameLayout);
        userNameLayout.setText("환영합니다. " + userName+ " " + userGrade + "님!");

        try{
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DOC_NAME, WRITER, TO_CHAR(WRITE_DATE, 'YYYY-MM-DD') FROM DOCUMENT WHERE ISAPPROVED='결재 대기' ORDER BY WRITE_DATE");

            StringBuffer stringBuffer = new StringBuffer();

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1) + ",");
                stringBuffer.append(resultSet.getString(2) + ",");
                stringBuffer.append(resultSet.getString(3) + ",");
            }
            String[] newData = stringBuffer.toString().split(",");
            checkBoxList = new CheckBox[newData.length/3];

            int idx = 0;
            for (int i = 0; i < newData.length / 3; i++) {
                CheckBox newTitle = new CheckBox(getApplicationContext());
                newTitle.setWidth(188);
                if (newData[idx].length() >= 10){
                    newTitle.setText(newData[idx].replace(newData[idx].substring(9), "..."));
                }else{
                    newTitle.setText(newData[idx]);
                }
                newTitle.setTextSize(17);
                newTitle.setTypeface(Typeface.SERIF, Typeface.NORMAL);
                checkBoxList[i] = newTitle;

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
                        Intent toDetail = new Intent(Approve.this, Details.class);

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

    public void onClickedApproval(View view){
        try{
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();

            String key;
            for (int i = 0; i < checkBoxList.length; i++) {
                CheckBox checkBox = checkBoxList[i];
                if (checkBox.getText().toString().length() >= 4) {
                    key = checkBox.getText().toString().substring(0, 4);
                }
                else {
                    key = checkBox.getText().toString();
                }

                if (checkBox.isChecked()){
                    statement.executeUpdate("UPDATE DOCUMENT SET ISAPPROVED='결재 완료' WHERE DOC_NAME LIKE '%"+key+"%'");
                }
            }

            Toast.makeText(getApplicationContext(), "결재를 완료했습니다.", Toast.LENGTH_SHORT).show();

            Intent intent = getIntent();
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}
