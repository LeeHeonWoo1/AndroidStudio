package com.example.documentsys;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;

public class MenuList extends AppCompatActivity {
    private String userName;
    private String userGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);

        Intent fromMain = getIntent();
        userName = fromMain.getStringExtra("userName");
        userGrade = fromMain.getStringExtra("userGrade");

        TextView userNameText = findViewById(R.id.userNameLayout);
        userNameText.setText("환영합니다. " + userName+ " " + userGrade + "님!");
    }

    public void onClickedDocList(View view){
        Intent toDocList = new Intent(this, DocList.class);
        toDocList.putExtra("userName", userName);
        toDocList.putExtra("userGrade", userGrade);
        startActivity(toDocList);
    }

    public void onClickedLogout(View view){
        Intent toMain = new Intent(this, MainActivity.class);

        startActivity(toMain);
    }

    public void onClickedNewDoc(View view){
        Intent toNewDoc = new Intent(this, NewDoc.class);
        toNewDoc.putExtra("userName", userName);
        toNewDoc.putExtra("userGrade", userGrade);
        startActivity(toNewDoc);
    }
}
