package com.example.quizwithotheractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz1 extends AppCompatActivity {
    int userPoint = 0;
    int submitCnt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz1);

        TextView nextBtn = findViewById(R.id.nextBtn);
        LinearLayout hintArea = findViewById(R.id.hintArea);
        TextView hintText1 = findViewById(R.id.hintText3);
        TextView hintText2 = findViewById(R.id.hintText2);
        TextView hintText3 = findViewById(R.id.hintText1);

        hintArea.setVisibility(View.INVISIBLE);
        nextBtn.setVisibility(View.INVISIBLE);
        hintText1.setVisibility(View.INVISIBLE);
        hintText2.setVisibility(View.INVISIBLE);
        hintText3.setVisibility(View.INVISIBLE);
    }

    public void onClickedSubmit(View view){
        RadioButton rb1 = findViewById(R.id.answer1);
        RadioButton rb2 = findViewById(R.id.answer2);
        RadioButton rb3 = findViewById(R.id.answer3);
        RadioButton rb4 = findViewById(R.id.answer4);

        TextView result = findViewById(R.id.result);
        TextView submitBtn = findViewById(R.id.submit);
        TextView nextBtn = findViewById(R.id.nextBtn);

        if (rb2.isChecked()) {
            if (submitCnt < 2) userPoint += 40;
            else if (submitCnt >= 2 && submitCnt < 4) userPoint += 30;
            else userPoint += 20;

            result.setText(submitCnt + "회 만에 정답!\n현재 점수 : " + userPoint);
            submitBtn.setVisibility(View.INVISIBLE);
            nextBtn.setVisibility(View.VISIBLE);
        }else if (!rb1.isChecked()&&!rb2.isChecked()&&!rb3.isChecked()&&!rb4.isChecked()){
            result.setText("최소 1개 이상 선택되어야 합니다.");
        } else {
            result.setText("다시 한 번 골라볼까요..? ㅜㅜ");
            submitCnt+=1;
        }
    }

    public void onClickedNext(View view){
        Intent quiz2 = new Intent(this, Quiz2.class);

        quiz2.putExtra("userPoint", userPoint);
        startActivity(quiz2);
    }

    public void onClickedHint(View view){
        TextView closeHint = findViewById(R.id.closeHint);
        TextView hint = findViewById(R.id.hint);

        hint.setVisibility(View.INVISIBLE);
        closeHint.setVisibility(View.VISIBLE);

        LinearLayout hintArea = findViewById(R.id.hintArea);
        TextView result = findViewById(R.id.result);

        result.setVisibility(View.INVISIBLE);
        hintArea.setVisibility(View.VISIBLE);
    }

    public void onClickedCloseHint(View view){
        TextView closeHint = findViewById(R.id.closeHint);
        TextView hint = findViewById(R.id.hint);

        hint.setVisibility(View.VISIBLE);
        closeHint.setVisibility(View.INVISIBLE);

        LinearLayout hintArea = findViewById(R.id.hintArea);
        TextView result = findViewById(R.id.result);

        result.setVisibility(View.VISIBLE);
        hintArea.setVisibility(View.INVISIBLE);

        TextView hintText1 = findViewById(R.id.hintText3);
        TextView hintText2 = findViewById(R.id.hintText2);
        TextView hintText3 = findViewById(R.id.hintText1);

        hintText1.setVisibility(View.INVISIBLE);
        hintText2.setVisibility(View.INVISIBLE);
        hintText3.setVisibility(View.INVISIBLE);
    }

    public void onClickedHint1(View view){
        TextView hintText1 = findViewById(R.id.hintText1);
        ImageView hint1 = findViewById(R.id.hint1);
        
        if (userPoint >= 15){
            hint1.setVisibility(View.INVISIBLE);
            hintText1.setVisibility(View.VISIBLE);
            userPoint -= 15;
        }else {
            Toast.makeText(this, "포인트 부족!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickedHint2(View view){
        TextView hintText1 = findViewById(R.id.hintText2);
        ImageView hint1 = findViewById(R.id.hint2);

        if (userPoint >= 15){
            hint1.setVisibility(View.INVISIBLE);
            hintText1.setVisibility(View.VISIBLE);
            userPoint -= 15;
        }else {
            Toast.makeText(this, "포인트 부족!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickedHint3(View view){
        TextView hintText1 = findViewById(R.id.hintText3);
        ImageView hint1 = findViewById(R.id.hint3);

        if (userPoint >= 15){
            hint1.setVisibility(View.INVISIBLE);
            hintText1.setVisibility(View.VISIBLE);
            userPoint -= 15;
        }else {
            Toast.makeText(this, "포인트 부족!", Toast.LENGTH_SHORT).show();
        }
    }
}