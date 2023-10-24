package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickedBtn1(View view){
        TextView tv = findViewById(R.id.text1); // text1
        TextView tv1 = findViewById(R.id.text2); // text2

        int x;
        x = Integer.parseInt(tv1.getText().toString()); // text2 -> 문자열

        tv.setText(String.valueOf(x));
    }
}