package com.example.tatproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tatproject.ForRecyclerView.RecyclerViewAdapter;
import com.example.tatproject.ForRecyclerView.SingleItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class ComplainOrQA extends AppCompatActivity {
    private static final String DRIVER = "oracle.jdbc.OracleDriver";

    private static final String URL = "jdbc:oracle:thin:@192.168.30.4:1521:xe";
    private static final String USERNAME = "c##group";
    private static final String PASSWORD = "1234";
    private Connection connection;
    private String username;
    private String writer;
    private String title;
    private String writeDate;
    private RecyclerViewAdapter adapter;
    ArrayList<SingleItem> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_complain);

        Intent fromMain = getIntent();
        this.username = fromMain.getStringExtra("username");

        try{
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TITLE, WRITE_DATE, USER_WRITER FROM QA");
            StringBuffer stringBuffer = new StringBuffer();

            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1 )+ ",");
                stringBuffer.append(resultSet.getString(2 )+ ",");
                stringBuffer.append(resultSet.getString(3 )+ ",");
            }
            int idx = 0;
            String[] stringArr = stringBuffer.toString().split(",");

            for (int i = 0; i < stringArr.length / 3; i++) {
                list.add(new SingleItem(stringArr[idx], stringArr[idx + 1], stringArr[idx + 2], R.drawable.lock_icon));
                idx += 3;
            }
//          recyclerView, adapter연결
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            adapter = new RecyclerViewAdapter(list, this);
            recyclerView.setAdapter(adapter);

            stringBuffer.setLength(0);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
