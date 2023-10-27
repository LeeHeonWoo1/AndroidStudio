package com.example.musicquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectQuizType(View view){
        Spinner quizTypes = findViewById(R.id.quizTypes);
        TextView quizType = findViewById(R.id.textView2);
        TextView explain = findViewById(R.id.textView3);

        quizType.setText(quizTypes.getSelectedItem().toString());

        if (quizTypes.getSelectedItem().toString().contains("초성")){
            explain.setText("퀴즈 규칙\n\n1. 후렴구의 초성이 제공됩니다\n2. 4지선다 입니다.\n" +
                    "3. 사용할 수 있는 힌트는 3개입니다.\n4. 각각 15, 20, 25포인트를 소모합니다.\n" +
                    "5. 퀴즈를 한번에 맞추면 40점, 세 번내에 맞추면 30점, 나머지는 20점을 획득합니다");
        }else if (quizTypes.getSelectedItem().toString().contains("간주")){
            explain.setText("퀴즈 규칙\n\n1. 노래 초반 1초를 들려드립니다.\n2. 4지선다 입니다.\n" +
                    "3. 사용할 수 있는 힌트는 3개입니다.\n4. 각각 15, 20, 25포인트를 소모합니다.\n" +
                    "5. 퀴즈를 한번에 맞추면 40점, 세 번내에 맞추면 30점, 나머지는 20점을 획득합니다");
        }
    }

    public void onClickedStart(View view){
        Intent nextAct = new Intent(this, Quiz1.class);
        Spinner quizTypes = findViewById(R.id.quizTypes);

        nextAct.putExtra("quizType", quizTypes.getSelectedItem().toString());
        startActivity(nextAct);
    }
}