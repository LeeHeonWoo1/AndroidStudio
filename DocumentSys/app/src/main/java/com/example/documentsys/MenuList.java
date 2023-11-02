package com.example.documentsys;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
        toDocList.putExtra("mode", "docList");
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

    public void onClickedCheckList(View view){
        Intent toCheckList = new Intent(this, DocList.class);
        toCheckList.putExtra("userName", userName);
        toCheckList.putExtra("userGrade", userGrade);
        toCheckList.putExtra("mode", "check");
        startActivity(toCheckList);
    }

    public void onClickedApprove(View view){
        if (userGrade.equals("부대표") || userGrade.equals("대표")){
            Intent toApprove = new Intent(this, Approve.class);
            toApprove.putExtra("userName", userName);
            toApprove.putExtra("userGrade", userGrade);

            startActivity(toApprove);
        }else{
            Toast.makeText(getApplicationContext(), "결재 권한이 없습니다.", Toast.LENGTH_LONG).show();
        }
    }
}
