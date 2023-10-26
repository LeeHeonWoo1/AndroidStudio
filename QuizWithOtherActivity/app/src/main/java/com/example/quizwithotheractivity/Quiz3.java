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

public class Quiz3 extends AppCompatActivity {
    int userPoint;
    int submitCnt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz3);

        TextView nextBtn = findViewById(R.id.nextBtn4);
        LinearLayout hintArea = findViewById(R.id.hintArea);
        TextView hintText1 = findViewById(R.id.hintText6);
        TextView hintText2 = findViewById(R.id.hintText7);
        TextView hintText3 = findViewById(R.id.hintText8);

        hintArea.setVisibility(View.INVISIBLE);
        nextBtn.setVisibility(View.INVISIBLE);
        hintText1.setVisibility(View.INVISIBLE);
        hintText2.setVisibility(View.INVISIBLE);
        hintText3.setVisibility(View.INVISIBLE);

        Intent fromQuiz1 = getIntent();
        this.userPoint = fromQuiz1.getIntExtra("userPoint", 0);

        TextView userScore = findViewById(R.id.userScore);
        userScore.setText("현재 점수 : " + userPoint);
    }

    public void onClickedSubmit(View view){
        RadioButton rb1 = findViewById(R.id.answer1);
        RadioButton rb2 = findViewById(R.id.answer2);
        RadioButton rb3 = findViewById(R.id.answer3);
        RadioButton rb4 = findViewById(R.id.answer4);

        TextView result = findViewById(R.id.result4);
        TextView submitBtn = findViewById(R.id.submit4);
        TextView nextBtn = findViewById(R.id.nextBtn4);
        TextView userScore = findViewById(R.id.userScore);

        if (rb4.isChecked()) {
            if (submitCnt < 2) userPoint += 40;
            else if (submitCnt >= 2 && submitCnt < 4) userPoint += 30;
            else userPoint += 20;

            result.setText(submitCnt + "회 만에 정답!");
            userScore.setText("현재 점수 : " + userPoint);
            submitBtn.setVisibility(View.INVISIBLE);
            nextBtn.setVisibility(View.VISIBLE);
        }else if (!rb1.isChecked()&&!rb2.isChecked()&&!rb3.isChecked()&&!rb4.isChecked()){
            result.setText("최소 1개 이상 선택되어야 합니다.");
        } else {
            result.setText("다시 한 번 골라볼까요..? ㅜㅜ");
            submitCnt+=1;
        }

        this.onClickedCloseHint(view);
    }

    public void onClickedNext(View view){
//        Intent quiz3 = new Intent(this, Quiz3.class);
//
//        quiz3.putExtra("userPoint", this.userPoint);
//        startActivity(quiz3);
    }

    public void onClickedHint(View view){
        TextView closeHint = findViewById(R.id.closeHint2);
        TextView hint = findViewById(R.id.hint4);

        hint.setVisibility(View.INVISIBLE);
        closeHint.setVisibility(View.VISIBLE);

        LinearLayout hintArea = findViewById(R.id.hintArea);
        TextView result = findViewById(R.id.result4);

        result.setVisibility(View.INVISIBLE);
        hintArea.setVisibility(View.VISIBLE);
    }

    public void onClickedCloseHint(View view){
        TextView closeHint = findViewById(R.id.closeHint2);
        TextView hint = findViewById(R.id.hint4);

        hint.setVisibility(View.VISIBLE);
        closeHint.setVisibility(View.INVISIBLE);

        LinearLayout hintArea = findViewById(R.id.hintArea);
        TextView result = findViewById(R.id.result4);

        result.setVisibility(View.VISIBLE);
        hintArea.setVisibility(View.INVISIBLE);

        TextView hintText1 = findViewById(R.id.hintText6);
        TextView hintText2 = findViewById(R.id.hintText7);
        TextView hintText3 = findViewById(R.id.hintText8);

        hintText1.setVisibility(View.INVISIBLE);
        hintText2.setVisibility(View.INVISIBLE);
        hintText3.setVisibility(View.INVISIBLE);
    }

    public void onClickedHint1(View view){
        TextView hintText1 = findViewById(R.id.hintText8);
        ImageView hint1 = findViewById(R.id.hint1);
        TextView userScore = findViewById(R.id.userScore);

        if (userPoint >= 15){
            hint1.setVisibility(View.INVISIBLE);
            hintText1.setVisibility(View.VISIBLE);
            userPoint -= 15;

            userScore.setText("현재 점수 : " + userPoint);
        }else {
            Toast.makeText(this, "포인트 부족!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickedHint2(View view){
        TextView hintText1 = findViewById(R.id.hintText7);
        ImageView hint1 = findViewById(R.id.hint2);
        TextView userScore = findViewById(R.id.userScore);

        if (userPoint >= 20){
            hint1.setVisibility(View.INVISIBLE);
            hintText1.setVisibility(View.VISIBLE);
            userPoint -= 20;

            userScore.setText("현재 점수 : " + userPoint);
        }else {
            Toast.makeText(this, "포인트 부족!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickedHint3(View view){
        TextView hintText1 = findViewById(R.id.hintText6);
        ImageView hint1 = findViewById(R.id.hint3);
        TextView userScore = findViewById(R.id.userScore);

        if (userPoint >= 25){
            hint1.setVisibility(View.INVISIBLE);
            hintText1.setVisibility(View.VISIBLE);
            userPoint -= 25;
            userScore.setText("현재 점수 : " + userPoint);
        }else {
            Toast.makeText(this, "포인트 부족!", Toast.LENGTH_SHORT).show();
        }
    }
}