package com.example.musicquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz1 extends AppCompatActivity {
    MediaPlayer mp;
    int userPoint = 0;
    int submitCnt = 1;
    String quizType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz1);

        Intent fromMain = getIntent();
        quizType = fromMain.getStringExtra("quizType");
        TextView nextBtn = findViewById(R.id.nextBtn);
        LinearLayout hintArea = findViewById(R.id.hintArea);
        TextView hintText1 = findViewById(R.id.hintText3);
        TextView hintText2 = findViewById(R.id.hintText2);
        TextView hintText3 = findViewById(R.id.hintText1);
        TextView question1 = findViewById(R.id.question1);
        ImageButton imgBtn = findViewById(R.id.question1Play);

        hintArea.setVisibility(View.INVISIBLE);
        nextBtn.setVisibility(View.INVISIBLE);
        hintText1.setVisibility(View.INVISIBLE);
        hintText2.setVisibility(View.INVISIBLE);
        hintText3.setVisibility(View.INVISIBLE);

        if (quizType.contains("초성")){
            question1.setText("ㅎ ㅂ ㄷㄱㄱㅁ ㄷ ㅂ ㅁㅇ ㅈㅈㅁ ㅈㄱ ㄷ ㄱㄲㅇ ㅇㅇ ㄲ ㅂㅇ ㅅㅅ ㅇㄴㅈㄹ ㅇㄴㅇ ㄴ ㅁ ㅁㄷ ㅂㅇㅈㄹ");
            imgBtn.setVisibility(View.INVISIBLE);

            RadioButton answer1 = findViewById(R.id.answer1);
            RadioButton answer2 = findViewById(R.id.answer2);
            RadioButton answer3 = findViewById(R.id.answer3);
            RadioButton answer4 = findViewById(R.id.answer4);

            answer1.setText("잘지내요");
            answer2.setText("몰라요");
            answer3.setText("Either Way");
            answer4.setText("Not Shy");
        }else if (quizType.contains("간주")){
            question1.setVisibility(View.INVISIBLE);
            imgBtn.setVisibility(View.VISIBLE);
            imgBtn.setOnClickListener(new View.OnClickListener(){ // Click Event listener를 생성하고
                @Override
                public void onClick(View view){
                    mp = MediaPlayer.create(Quiz1.this, R.raw.chili); // 클릭 시 재생할 파일을 지정한다.
                    mp.start();
                }
            });
        }
    }

    @Override // media player의 경우 메모리 소모가 심하기 때문에 남발하면 안된다.
    protected void onDestroy(){ // 따라서 제대로 종료되지 않았을 경우를 대비하여
        super.onDestroy(); // onDestroy를 가져와서
        if (mp!=null){ // 비어있지 않은 경우
            mp.release();
            mp = null; // 비운다.
        }
    }

    public void onClickedSubmit(View view){
        RadioButton rb1 = findViewById(R.id.answer1);
        RadioButton rb2 = findViewById(R.id.answer2);
        RadioButton rb3 = findViewById(R.id.answer3);
        RadioButton rb4 = findViewById(R.id.answer4);

        TextView result = findViewById(R.id.result);
        TextView submitBtn = findViewById(R.id.submit);
        TextView nextBtn = findViewById(R.id.nextBtn);

        if ((quizType.contains("초성") ? rb2.isChecked() : rb1.isChecked())) {
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

        this.onClickedCloseHint(view);
    }

    public void onClickedNext(View view){
        Intent quiz2 = new Intent(this, Quiz2.class);

        quiz2.putExtra("userPoint", userPoint);
        quiz2.putExtra("quizType", quizType);
        startActivity(quiz2);
    }

    public void onClickedHint(View view){
        TextView closeHint = findViewById(R.id.closeHint);
        TextView hint = findViewById(R.id.hint);
        TextView hintText1 = findViewById(R.id.hintText3);
        TextView hintText2 = findViewById(R.id.hintText2);
        TextView hintText3 = findViewById(R.id.hintText1);

        hint.setVisibility(View.INVISIBLE);
        closeHint.setVisibility(View.VISIBLE);

        LinearLayout hintArea = findViewById(R.id.hintArea);
        TextView result = findViewById(R.id.result);

        result.setVisibility(View.INVISIBLE);
        hintArea.setVisibility(View.VISIBLE);

        if (quizType.contains("간주")){
            hintText1.setText("빨간 슈트");
            hintText2.setText("MMM");
            hintText3.setText("마리아");
        }
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
        ImageView hint1 = findViewById(R.id.hintImg1);

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
        ImageView hint1 = findViewById(R.id.hintImg2);

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
        ImageView hint1 = findViewById(R.id.hintImg3);

        if (userPoint >= 15){
            hint1.setVisibility(View.INVISIBLE);
            hintText1.setVisibility(View.VISIBLE);
            userPoint -= 15;
        }else {
            Toast.makeText(this, "포인트 부족!", Toast.LENGTH_SHORT).show();
        }
    }
}
