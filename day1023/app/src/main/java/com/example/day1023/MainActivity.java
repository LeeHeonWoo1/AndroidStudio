package com.example.day1023;

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

    public void onClickedBtn(View view){
        TextView inputX = findViewById(R.id.inputX);
        TextView inputY = findViewById(R.id.inputY);
        TextView result = findViewById(R.id.resultText);

        int x = Integer.parseInt(inputX.getText().toString());
        int y = Integer.parseInt(inputY.getText().toString());
        int res = x + y;

        result.setText(String.valueOf(res));
    }

    public void onClickedBtn2(View view){
        TextView inputX = findViewById(R.id.inputX);
        TextView inputY = findViewById(R.id.inputY);
        TextView result = findViewById(R.id.resultText);

        int x = Integer.parseInt(inputX.getText().toString());
        int y = Integer.parseInt(inputY.getText().toString());
        int res = x - y;

        result.setText(String.valueOf(res));
    }

    public void onClickedBtn3(View view){
        TextView inputX = findViewById(R.id.inputX);
        TextView inputY = findViewById(R.id.inputY);
        TextView result = findViewById(R.id.resultText);

        int x = Integer.parseInt(inputX.getText().toString());
        int y = Integer.parseInt(inputY.getText().toString());
        int res = x * y;

        result.setText(String.valueOf(res));
    }

    public void onClickedBtn4(View view){
        TextView inputX = findViewById(R.id.inputX);
        TextView inputY = findViewById(R.id.inputY);
        TextView result = findViewById(R.id.resultText);

        int x = Integer.parseInt(inputX.getText().toString());
        int y = Integer.parseInt(inputY.getText().toString());
        int res = x / y;

        result.setText(String.valueOf(res));
    }
}