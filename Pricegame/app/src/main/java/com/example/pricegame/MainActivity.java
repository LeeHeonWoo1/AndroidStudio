package com.example.pricegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random r = new Random();
    ImageView[][] gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickedReset(View view){
        TextView winCnt = findViewById(R.id.winCnt);
        TextView loseCnt = findViewById(R.id.loseCnt);
        ImageView winImg = findViewById(R.id.winImage);
        TextView flagText = findViewById(R.id.flagText);

        winCnt.setText("0");
        loseCnt.setText("0");
        flagText.setText("게임을 재시작합니다. Start 버튼을 누르세요.");

        for (int i = 0; i < gameList.length; i++)
            for (int j = 0; j < gameList[i].length; j++)
                gameList[i][j].setVisibility(View.VISIBLE);

        winImg.setVisibility(View.INVISIBLE);
    }

    public void setGameList(View view){
        ImageView winImg = findViewById(R.id.winImage);
        winImg.setVisibility(View.INVISIBLE);

        ImageView box1 = findViewById(R.id.randBox1);
        ImageView box2 = findViewById(R.id.randBox2);
        ImageView box3 = findViewById(R.id.randBox3);
        ImageView box4 = findViewById(R.id.randBox4);
        ImageView box5 = findViewById(R.id.randBox5);
        ImageView box6 = findViewById(R.id.randBox6);
        ImageView box7 = findViewById(R.id.randBox7);
        ImageView box8 = findViewById(R.id.randBox8);
        ImageView box9 = findViewById(R.id.randBox9);

        ImageView winner1 = findViewById(R.id.winner1);
        ImageView winner2 = findViewById(R.id.winner2);
        ImageView winner3 = findViewById(R.id.winner3);
        ImageView winner4 = findViewById(R.id.winner4);
        ImageView winner5 = findViewById(R.id.winner5);
        ImageView winner6 = findViewById(R.id.winner6);
        ImageView winner7 = findViewById(R.id.winner7);
        ImageView winner8 = findViewById(R.id.winner8);
        ImageView winner9 = findViewById(R.id.winner9);

        ImageView fail1 = findViewById(R.id.fail1);
        ImageView fail2 = findViewById(R.id.fail2);
        ImageView fail3 = findViewById(R.id.fail3);
        ImageView fail4 = findViewById(R.id.fail4);
        ImageView fail5 = findViewById(R.id.fail5);
        ImageView fail6 = findViewById(R.id.fail6);
        ImageView fail7 = findViewById(R.id.fail7);
        ImageView fail8 = findViewById(R.id.fail8);
        ImageView fail9 = findViewById(R.id.fail9);

        gameList = new ImageView[][]{
                {box1, winner1, fail1},
                {box2, winner2, fail2},
                {box3, winner3, fail3},
                {box4, winner4, fail4},
                {box5, winner5, fail5},
                {box6, winner6, fail6},
                {box7, winner7, fail7},
                {box8, winner8, fail8},
                {box9, winner9, fail9}
        };

        int winNum = r.nextInt(9);
        gameList[winNum][2].setVisibility(View.INVISIBLE);

        for (int i = 0; i < gameList.length; i++) {
            if (i == winNum) continue;
            else gameList[i][1].setVisibility(View.INVISIBLE);
        }
    }

    public void clickedBox(View view){
        TextView loseCnt = findViewById(R.id.loseCnt);
        TextView winCnt = findViewById(R.id.winCnt);
        TextView flagText = findViewById(R.id.flagText);
        ImageView winImg = findViewById(R.id.winImage);

        int lose = Integer.parseInt(loseCnt.getText().toString());
        int win = Integer.parseInt(winCnt.getText().toString());

        gameList[0][0].setVisibility(View.INVISIBLE);
        if (gameList[0][1].getVisibility() == View.INVISIBLE){
            lose ++;
            loseCnt.setText(String.valueOf(lose));
            flagText.setText("꽝입니다 :( 다른 상자도 열어볼까요?");
        }else{
            win ++;
            winCnt.setText(String.valueOf(win));
            flagText.setText("축하합니다 ! 보물을 찾으셨네요 :) ");

            for (int i = 0; i < gameList.length; i++)
                for (int j = 0; j < gameList[i].length; j++)
                    gameList[i][j].setVisibility(View.INVISIBLE);

            winImg.setVisibility(View.VISIBLE);
        }
    }
    public void clickedBox2(View view){
        TextView loseCnt = findViewById(R.id.loseCnt);
        TextView winCnt = findViewById(R.id.winCnt);
        TextView flagText = findViewById(R.id.flagText);
        ImageView winImg = findViewById(R.id.winImage);

        int lose = Integer.parseInt(loseCnt.getText().toString());
        int win = Integer.parseInt(winCnt.getText().toString());

        gameList[1][0].setVisibility(View.INVISIBLE);
        if (gameList[1][1].getVisibility() == View.INVISIBLE){
            lose ++;
            loseCnt.setText(String.valueOf(lose));
            flagText.setText("꽝입니다 :( 다른 상자도 열어볼까요?");
        }else{
            win ++;
            winCnt.setText(String.valueOf(win));
            flagText.setText("축하합니다 ! 보물을 찾으셨네요 :) ");


            for (int i = 0; i < gameList.length; i++)
                for (int j = 0; j < gameList[i].length; j++)
                    gameList[i][j].setVisibility(View.INVISIBLE);

            winImg.setVisibility(View.VISIBLE);
        }
    }
    public void clickedBox3(View view){
        TextView loseCnt = findViewById(R.id.loseCnt);
        TextView winCnt = findViewById(R.id.winCnt);
        TextView flagText = findViewById(R.id.flagText);
        ImageView winImg = findViewById(R.id.winImage);

        int lose = Integer.parseInt(loseCnt.getText().toString());
        int win = Integer.parseInt(winCnt.getText().toString());

        gameList[2][0].setVisibility(View.INVISIBLE);
        if (gameList[2][1].getVisibility() == View.INVISIBLE){
            lose ++;
            loseCnt.setText(String.valueOf(lose));
            flagText.setText("꽝입니다 :( 다른 상자도 열어볼까요?");
        }else{
            win ++;
            winCnt.setText(String.valueOf(win));
            flagText.setText("축하합니다 ! 보물을 찾으셨네요 :) ");


            for (int i = 0; i < gameList.length; i++)
                for (int j = 0; j < gameList[i].length; j++)
                    gameList[i][j].setVisibility(View.INVISIBLE);

            winImg.setVisibility(View.VISIBLE);
        }
    }
    public void clickedBox4(View view){
        TextView loseCnt = findViewById(R.id.loseCnt);
        TextView winCnt = findViewById(R.id.winCnt);
        TextView flagText = findViewById(R.id.flagText);
        ImageView winImg = findViewById(R.id.winImage);

        int lose = Integer.parseInt(loseCnt.getText().toString());
        int win = Integer.parseInt(winCnt.getText().toString());

        gameList[3][0].setVisibility(View.INVISIBLE);
        if (gameList[3][1].getVisibility() == View.INVISIBLE){
            lose ++;
            loseCnt.setText(String.valueOf(lose));
            flagText.setText("꽝입니다 :( 다른 상자도 열어볼까요?");
        }else{
            win ++;
            winCnt.setText(String.valueOf(win));
            flagText.setText("축하합니다 ! 보물을 찾으셨네요 :) ");


            for (int i = 0; i < gameList.length; i++)
                for (int j = 0; j < gameList[i].length; j++)
                    gameList[i][j].setVisibility(View.INVISIBLE);

            winImg.setVisibility(View.VISIBLE);
        }
    }
    public void clickedBox5(View view){
        TextView loseCnt = findViewById(R.id.loseCnt);
        TextView winCnt = findViewById(R.id.winCnt);
        TextView flagText = findViewById(R.id.flagText);
        ImageView winImg = findViewById(R.id.winImage);

        int lose = Integer.parseInt(loseCnt.getText().toString());
        int win = Integer.parseInt(winCnt.getText().toString());

        gameList[4][0].setVisibility(View.INVISIBLE);
        if (gameList[4][1].getVisibility() == View.INVISIBLE){
            lose ++;
            loseCnt.setText(String.valueOf(lose));
            flagText.setText("꽝입니다 :( 다른 상자도 열어볼까요?");
        }else{
            win ++;
            winCnt.setText(String.valueOf(win));
            flagText.setText("축하합니다 ! 보물을 찾으셨네요 :) ");


            for (int i = 0; i < gameList.length; i++)
                for (int j = 0; j < gameList[i].length; j++)
                    gameList[i][j].setVisibility(View.INVISIBLE);

            winImg.setVisibility(View.VISIBLE);
        }
    }
    public void clickedBox6(View view){
        TextView loseCnt = findViewById(R.id.loseCnt);
        TextView winCnt = findViewById(R.id.winCnt);
        TextView flagText = findViewById(R.id.flagText);
        ImageView winImg = findViewById(R.id.winImage);

        int lose = Integer.parseInt(loseCnt.getText().toString());
        int win = Integer.parseInt(winCnt.getText().toString());

        gameList[5][0].setVisibility(View.INVISIBLE);
        if (gameList[5][1].getVisibility() == View.INVISIBLE){
            lose ++;
            loseCnt.setText(String.valueOf(lose));
            flagText.setText("꽝입니다 :( 다른 상자도 열어볼까요?");
        }else{
            win ++;
            winCnt.setText(String.valueOf(win));
            flagText.setText("축하합니다 ! 보물을 찾으셨네요 :) ");


            for (int i = 0; i < gameList.length; i++)
                for (int j = 0; j < gameList[i].length; j++)
                    gameList[i][j].setVisibility(View.INVISIBLE);

            winImg.setVisibility(View.VISIBLE);
        }
    }
    public void clickedBox7(View view){
        TextView loseCnt = findViewById(R.id.loseCnt);
        TextView winCnt = findViewById(R.id.winCnt);
        TextView flagText = findViewById(R.id.flagText);
        ImageView winImg = findViewById(R.id.winImage);

        int lose = Integer.parseInt(loseCnt.getText().toString());
        int win = Integer.parseInt(winCnt.getText().toString());

        gameList[6][0].setVisibility(View.INVISIBLE);
        if (gameList[6][1].getVisibility() == View.INVISIBLE){
            lose ++;
            loseCnt.setText(String.valueOf(lose));
            flagText.setText("꽝입니다 :( 다른 상자도 열어볼까요?");
        }else{
            win ++;
            winCnt.setText(String.valueOf(win));
            flagText.setText("축하합니다 ! 보물을 찾으셨네요 :) ");


            for (int i = 0; i < gameList.length; i++)
                for (int j = 0; j < gameList[i].length; j++)
                    gameList[i][j].setVisibility(View.INVISIBLE);

            winImg.setVisibility(View.VISIBLE);
        }
    }
    public void clickedBox8(View view){
        TextView loseCnt = findViewById(R.id.loseCnt);
        TextView winCnt = findViewById(R.id.winCnt);
        TextView flagText = findViewById(R.id.flagText);
        ImageView winImg = findViewById(R.id.winImage);

        int lose = Integer.parseInt(loseCnt.getText().toString());
        int win = Integer.parseInt(winCnt.getText().toString());

        gameList[7][0].setVisibility(View.INVISIBLE);
        if (gameList[7][1].getVisibility() == View.INVISIBLE){
            lose ++;
            loseCnt.setText(String.valueOf(lose));
            flagText.setText("꽝입니다 :( 다른 상자도 열어볼까요?");
        }else{
            win ++;
            winCnt.setText(String.valueOf(win));
            flagText.setText("축하합니다 ! 보물을 찾으셨네요 :) ");


            for (int i = 0; i < gameList.length; i++)
                for (int j = 0; j < gameList[i].length; j++)
                    gameList[i][j].setVisibility(View.INVISIBLE);

            winImg.setVisibility(View.VISIBLE);
        }
    }
    public void clickedBox9(View view){
        TextView loseCnt = findViewById(R.id.loseCnt);
        TextView winCnt = findViewById(R.id.winCnt);
        TextView flagText = findViewById(R.id.flagText);
        ImageView winImg = findViewById(R.id.winImage);

        int lose = Integer.parseInt(loseCnt.getText().toString());
        int win = Integer.parseInt(winCnt.getText().toString());

        gameList[8][0].setVisibility(View.INVISIBLE);
        if (gameList[8][1].getVisibility() == View.INVISIBLE){
            lose ++;
            loseCnt.setText(String.valueOf(lose));
            flagText.setText("꽝입니다 :( 다른 상자도 열어볼까요?");
        }else{
            win ++;
            winCnt.setText(String.valueOf(win));
            flagText.setText("축하합니다 ! 보물을 찾으셨네요 :) ");


            for (int i = 0; i < gameList.length; i++)
                for (int j = 0; j < gameList[i].length; j++)
                    gameList[i][j].setVisibility(View.INVISIBLE);

            winImg.setVisibility(View.VISIBLE);
        }
    }
}