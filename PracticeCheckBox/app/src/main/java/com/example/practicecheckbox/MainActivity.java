package com.example.practicecheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    String originReciept = "주문 내역입니다.\n\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView menu1CntView = findViewById(R.id.cb1MenuCnt);
        TextView menu1CntUpBtn = findViewById(R.id.cb1MenuCntUp);
        TextView menu1CntDownBtn = findViewById(R.id.cb1MenuCntDown);
        TextView menu2CntView = findViewById(R.id.cb2MenuCnt);
        TextView menu2CntUpBtn = findViewById(R.id.cb2MenuCntUp);
        TextView menu2CntDownBtn = findViewById(R.id.cb2MenuCntDown);
        TextView menu3CntView = findViewById(R.id.cb3MenuCnt);
        TextView menu3CntUpBtn = findViewById(R.id.cb3MenuCntUp);
        TextView menu3CntDownBtn = findViewById(R.id.cb3MenuCntDown);

        menu1CntView.setVisibility(View.INVISIBLE);
        menu1CntUpBtn.setVisibility(View.INVISIBLE);
        menu1CntDownBtn.setVisibility(View.INVISIBLE);
        menu2CntView.setVisibility(View.INVISIBLE);
        menu2CntUpBtn.setVisibility(View.INVISIBLE);
        menu2CntDownBtn.setVisibility(View.INVISIBLE);
        menu3CntView.setVisibility(View.INVISIBLE);
        menu3CntUpBtn.setVisibility(View.INVISIBLE);
        menu3CntDownBtn.setVisibility(View.INVISIBLE);
    }

    public void onClickedOrder(View view){
        CheckBox menu1 = findViewById(R.id.cb1);
        int menu1Price = Integer.parseInt(menu1.getText().toString().replaceAll("[^0-9]", ""));

        CheckBox menu2 = findViewById(R.id.cb2);
        int menu2Price = Integer.parseInt(menu2.getText().toString().replaceAll("[^0-9]", ""));

        CheckBox menu3 = findViewById(R.id.cb3);
        int menu3Price = Integer.parseInt(menu3.getText().toString().replaceAll("[^0-9]", ""));

        TextView reciept = findViewById(R.id.receipt);

        int totalPrice = 0;
        if (menu1.isChecked()){
            TextView menu1CntView = findViewById(R.id.cb1MenuCnt);
            String idx = menu1.getText().toString();
            String menu1Name = idx.substring(0, idx.indexOf(" "));
            int menu1Cnt = Integer.parseInt(menu1CntView.getText().toString());
            totalPrice += menu1Price*menu1Cnt;

            originReciept += menu1Name + menu1Cnt + "개\n";
        }

        if (menu2.isChecked()){
            TextView menu2CntView = findViewById(R.id.cb2MenuCnt);
            String idx = menu2.getText().toString();
            String menu2Name = idx.substring(0, idx.indexOf(" "));
            int menu2Cnt = Integer.parseInt(menu2CntView.getText().toString());
            totalPrice += menu2Price*menu2Cnt;

            originReciept += menu2Name + menu2Cnt + "개\n";
        }

        if (menu3.isChecked()){
            TextView menu3CntView = findViewById(R.id.cb3MenuCnt);
            String idx = menu3.getText().toString();
            String menu3Name = idx.substring(0, idx.indexOf(" "));
            int menu3Cnt = Integer.parseInt(menu3CntView.getText().toString());
            totalPrice += menu3Price*menu3Cnt;

            originReciept += menu3Name + menu3Cnt + "개\n";
        }

        TextView totalFare = findViewById(R.id.totalFare);
        reciept.setText(originReciept);
        totalFare.setText(String.valueOf(totalPrice) + "원");
        originReciept = "주문 내역입니다.\n\n";
    }

    public void onClickedMenu1(View view){
        TextView menu1CntView = findViewById(R.id.cb1MenuCnt);
        TextView menu1CntUpBtn = findViewById(R.id.cb1MenuCntUp);
        TextView menu1CntDownBtn = findViewById(R.id.cb1MenuCntDown);

        CheckBox cb1 = findViewById(R.id.cb1);
        TextView menu1 = findViewById(R.id.cb1MenuCnt);
        int menu1Cnt = Integer.parseInt(menu1.getText().toString());
        int menu1Price;

        if (cb1.isChecked()) {
            menu1CntView.setVisibility(View.VISIBLE);
            menu1CntUpBtn.setVisibility(View.VISIBLE);
            menu1CntDownBtn.setVisibility(View.VISIBLE);
            menu1Price = Integer.parseInt(cb1.getText().toString().replaceAll("[^0-9]", ""))*menu1Cnt;
        }else{
            menu1CntView.setVisibility(View.INVISIBLE);
            menu1CntUpBtn.setVisibility(View.INVISIBLE);
            menu1CntDownBtn.setVisibility(View.INVISIBLE);
            menu1CntView.setText("0");
        }
    }
    public void onClickedMenu2(View view){
        TextView menu2CntView = findViewById(R.id.cb2MenuCnt);
        TextView menu2CntUpBtn = findViewById(R.id.cb2MenuCntUp);
        TextView menu2CntDownBtn = findViewById(R.id.cb2MenuCntDown);

        CheckBox cb2 = findViewById(R.id.cb2);
        TextView menu2 = findViewById(R.id.cb2MenuCnt);
        int menu1Cnt = Integer.parseInt(menu2.getText().toString());
        int menu2Price;

        if (cb2.isChecked()) {
            menu2CntView.setVisibility(View.VISIBLE);
            menu2CntUpBtn.setVisibility(View.VISIBLE);
            menu2CntDownBtn.setVisibility(View.VISIBLE);
            menu2Price = Integer.parseInt(cb2.getText().toString().replaceAll("[^0-9]", ""))*menu1Cnt;
        }else{
            menu2CntView.setVisibility(View.INVISIBLE);
            menu2CntUpBtn.setVisibility(View.INVISIBLE);
            menu2CntDownBtn.setVisibility(View.INVISIBLE);
            menu2CntView.setText("0");
        }
    }
    public void onClickedMenu3(View view){
        TextView menu1CntView = findViewById(R.id.cb3MenuCnt);
        TextView menu1CntUpBtn = findViewById(R.id.cb3MenuCntUp);
        TextView menu1CntDownBtn = findViewById(R.id.cb3MenuCntDown);

        CheckBox cb1 = findViewById(R.id.cb3);
        TextView menu1 = findViewById(R.id.cb3MenuCnt);
        int menu1Cnt = Integer.parseInt(menu1.getText().toString());
        int menu1Price;

        if (cb1.isChecked()) {
            menu1CntView.setVisibility(View.VISIBLE);
            menu1CntUpBtn.setVisibility(View.VISIBLE);
            menu1CntDownBtn.setVisibility(View.VISIBLE);
            menu1Price = Integer.parseInt(cb1.getText().toString().replaceAll("[^0-9]", ""))*menu1Cnt;
        }else{
            menu1CntView.setVisibility(View.INVISIBLE);
            menu1CntUpBtn.setVisibility(View.INVISIBLE);
            menu1CntDownBtn.setVisibility(View.INVISIBLE);
            menu1CntView.setText("0");
        }
    }

    public void setMenu1CntUp(View view){
        TextView menu = findViewById(R.id.cb1MenuCnt);
        int menuCnt = Integer.parseInt(menu.getText().toString());

        menuCnt++;
        menu.setText(String.valueOf(menuCnt));
    }
    public void setMenu2CntUp(View view){
        TextView menu = findViewById(R.id.cb2MenuCnt);
        int menuCnt = Integer.parseInt(menu.getText().toString());

        menuCnt++;
        menu.setText(String.valueOf(menuCnt));
    }
    public void setMenu3CntUp(View view){
        TextView menu = findViewById(R.id.cb3MenuCnt);
        int menuCnt = Integer.parseInt(menu.getText().toString());

        menuCnt++;
        menu.setText(String.valueOf(menuCnt));
    }
    public void setMenu1CntDown(View view){
        TextView menu = findViewById(R.id.cb1MenuCnt);
        int menuCnt = Integer.parseInt(menu.getText().toString());

        if (! menu.getText().toString().equals("0")){
            menuCnt--;
            menu.setText(String.valueOf(menuCnt));
        }else menu.setText("0");
    }
    public void setMenu2CntDown(View view){
        TextView menu = findViewById(R.id.cb2MenuCnt);
        int menuCnt = Integer.parseInt(menu.getText().toString());

        if (! menu.getText().toString().equals("0")){
            menuCnt--;
            menu.setText(String.valueOf(menuCnt));
        }else menu.setText("0");
    }
    public void setMenu3CntDown(View view){
        TextView menu = findViewById(R.id.cb3MenuCnt);
        int menuCnt = Integer.parseInt(menu.getText().toString());

        if (! menu.getText().toString().equals("0")){
            menuCnt--;
            menu.setText(String.valueOf(menuCnt));
        }else menu.setText("0");
    }
}