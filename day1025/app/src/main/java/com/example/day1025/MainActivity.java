package com.example.day1025;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Random;

import java.io.IOError;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void quiz(View view){
        RadioGroup rg = findViewById(R.id.radioGroup);
        RadioButton ans1 = findViewById(R.id.ans1);
        RadioButton ans2 = findViewById(R.id.ans2);
        RadioButton ans3 = findViewById(R.id.ans3);
        RadioButton ans4 = findViewById(R.id.ans4);
        RadioButton userAnswer = findViewById(rg.getCheckedRadioButtonId());

        TextView corCnt = findViewById(R.id.corCnt);
        TextView wrongCnt = findViewById(R.id.wrongCnt);
        TextView eq1 = findViewById(R.id.eq1);
        TextView eq2 = findViewById(R.id.eq3);

        int correct = Integer.parseInt(corCnt.getText().toString());
        int wrong = Integer.parseInt(wrongCnt.getText().toString());
        int answer = Integer.parseInt(eq1.getText().toString()) + Integer.parseInt(eq2.getText().toString());

        if (userAnswer.getText().toString().equals(String.valueOf(answer))){
            correct ++;
            corCnt.setText(String.valueOf(correct));
        }else{
            wrong++;
            wrongCnt.setText(String.valueOf(wrong));
        }

        Random r = new Random();
        int newEq1 = r.nextInt(101);
        int newEq2 = r.nextInt(101);
        int newAnswer = newEq1 + newEq2;
        eq1.setText(String.valueOf(newEq1));
        eq2.setText(String.valueOf(newEq2));
        ans1.setText("");
        ans2.setText("");
        ans3.setText("");
        ans4.setText("");

        ans1.setText(String.valueOf(newAnswer - r.nextInt(4)));
        ans2.setText(String.valueOf(newAnswer - r.nextInt(7)));
        ans3.setText(String.valueOf(newAnswer));
        ans4.setText(String.valueOf(newAnswer - r.nextInt(3)));
    }
}