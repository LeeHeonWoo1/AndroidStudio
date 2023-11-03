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

        // 메인 Activity로부터 받아온 stringExtra로
        Intent fromMain = getIntent();
        userName = fromMain.getStringExtra("userName");
        userGrade = fromMain.getStringExtra("userGrade");

        // 화면 상단에 메세지를 set한다.
        TextView userNameText = findViewById(R.id.userNameLayout);
        userNameText.setText("환영합니다. " + userName+ " " + userGrade + "님!");
    }

    public void onClickedDocList(View view){
        // 이후 동일하게 화면 상단에 메세지를 띄우기 위해서 계속 넘긴다. (이후에 같은 맥락의 코드는 주석 생략)
        // 문서 목록 Activity로 이동
        Intent toDocList = new Intent(this, DocList.class);
        toDocList.putExtra("userName", userName);
        toDocList.putExtra("userGrade", userGrade);
        toDocList.putExtra("mode", "docList");
        startActivity(toDocList);
    }

    public void onClickedLogout(View view){
        // 상단에 로그아웃 텍스트를 누르면 MainActivity로 갈 수 있게끔.
        Intent toMain = new Intent(this, MainActivity.class);

        startActivity(toMain);
    }

    public void onClickedNewDoc(View view){
        // 문서기안 Activity 이동
        Intent toNewDoc = new Intent(this, NewDoc.class);
        toNewDoc.putExtra("userName", userName);
        toNewDoc.putExtra("userGrade", userGrade);
        startActivity(toNewDoc);
    }

    public void onClickedCheckList(View view){
        // 결재 대기 문서 리스트 Activity 이동
        Intent toCheckList = new Intent(this, DocList.class);
        toCheckList.putExtra("userName", userName);
        toCheckList.putExtra("userGrade", userGrade);
        toCheckList.putExtra("mode", "check");
        startActivity(toCheckList);
    }

    public void onClickedApprove(View view){
        // 만약에 직급이 부대표이거나 대표일 때만
        if (userGrade.equals("부대표") || userGrade.equals("대표")){
            // 결재 Activity로 이동한다.
            Intent toApprove = new Intent(this, Approve.class);
            toApprove.putExtra("userName", userName);
            toApprove.putExtra("userGrade", userGrade);

            startActivity(toApprove);
        }else{ // 그게 아니라면
            // 권한이 없다는 Toast메세지 출력
            Toast.makeText(getApplicationContext(), "결재 권한이 없습니다.", Toast.LENGTH_LONG).show();
        }
    }
}
