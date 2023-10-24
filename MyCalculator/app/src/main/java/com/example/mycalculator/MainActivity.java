package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int x;
    int y;
    String operation;
    String process = "";
    String[] history = new String[10];
    int idx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickedClean(View view){
        TextView result = findViewById(R.id.result);
        TextView calcProcess = findViewById(R.id.calcProcess);
        result.setText("");
        calcProcess.setText("");
    }

    public void clickedDot(View view){
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString()+".");
    }

    public void add(View view){
        TextView result = findViewById(R.id.result);
        TextView cal = findViewById(R.id.calcProcess);

        if (! result.getText().toString().contains(".")){
            x = Integer.parseInt(result.getText().toString());
            process += x + " + ";
        } else
            process += result.getText().toString() + " + ";

        operation = "add";

        result.setText("");
        cal.setText(process);
    }
    public void sub(View view){
        TextView result = findViewById(R.id.result);
        TextView cal = findViewById(R.id.calcProcess);

        if (! result.getText().toString().contains(".")){
            x = Integer.parseInt(result.getText().toString());
            process += x + " - ";
        } else
            process += result.getText().toString() + " - ";

        operation = "sub";

        result.setText("");
        cal.setText(process);
    }
    public void mul(View view){
        TextView result = findViewById(R.id.result);
        TextView cal = findViewById(R.id.calcProcess);

        if (! result.getText().toString().contains(".")){
            x = Integer.parseInt(result.getText().toString());
            process += x + " x ";
        } else
            process += result.getText().toString() + " x ";

        operation = "mul";

        result.setText("");
        cal.setText(process);
    }
    public void div(View view){
        TextView result = findViewById(R.id.result);
        TextView cal = findViewById(R.id.calcProcess);

        if (! result.getText().toString().contains(".")){
            x = Integer.parseInt(result.getText().toString());
            process += x + " ÷ ";
        } else
            process += result.getText().toString() + " ÷ ";

        operation = "div";

        result.setText("");
        cal.setText(process);
    }

    public void clickedEqual(View view){
        TextView result = findViewById(R.id.result);
        TextView calcProcess = findViewById(R.id.calcProcess);

        if (! result.getText().toString().contains(".")){
            y = Integer.parseInt(result.getText().toString());
            process += y;
        }else process += result.getText().toString();

        calcProcess.setText(process);
        history[idx] = process;
        idx++;

        switch (operation){
            case "add":
                String[] numArr = process.replace(" ", "").replaceAll("[^0-9-.]", ",").split(",");
                float resf = 0.0f;
                int resi = 0;

                for(String i : numArr){
                    if(! i.contains(".")) resi += Integer.parseInt(i);
                    else resf += Float.parseFloat(i);
                }

                if (process.contains(".")) result.setText(String.valueOf((float)resi + resf));
                else result.setText(String.valueOf(resi));
                break;
            case "sub":
                String[] numArr2 = process.replace(" ", "").replaceAll("[^0-9.]", ",").split(",");
                int res2 = Integer.parseInt(numArr2[0]);
                for (int i = 1; i < numArr2.length; i++)
                    res2 -= Integer.parseInt(numArr2[i]);

                result.setText(String.valueOf(res2));
                break;
            case "mul":
                String[] numArr3 = process.replace(" ", "").replaceAll("[^0-9-.]", ",").split(",");
                int res3 = 1;
                for(String i : numArr3)
                    res3 *= Integer.parseInt(i);

                result.setText(String.valueOf(res3));
                break;
            case "div":
                String[] numArr4 = process.replace(" ", "").replaceAll("[^0-9.]", ",").split(",");
                int res4 = Integer.parseInt(numArr4[0]);
                for (int i = 1; i < numArr4.length; i++)
                    res4 /= Integer.parseInt(numArr4[i]);

                result.setText(String.valueOf(res4));
                break;
        }

        x = 0;
        y = 0;
        operation = "";
        process = "";
    }

    public void clickedOne(View view){
        TextView one = findViewById(R.id.one);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + one.getText().toString());
    }
    public void clickedTwo(View view){
        TextView two = findViewById(R.id.two);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + two.getText().toString());
    }
    public void clickedThree(View view){
        TextView three = findViewById(R.id.three);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + three.getText().toString());
    }
    public void clickedFour(View view){
        TextView four = findViewById(R.id.four);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + four.getText().toString());
    }
    public void clickedFive(View view){
        TextView five = findViewById(R.id.five);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + five.getText().toString());
    }
    public void clickedSix(View view){
        TextView six = findViewById(R.id.six);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + six.getText().toString());
    }
    public void clickedSeven(View view){
        TextView seven = findViewById(R.id.seven);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + seven.getText().toString());
    }
    public void clickedEight(View view){
        TextView eight = findViewById(R.id.eight);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + eight.getText().toString());
    }
    public void clickedNine(View view){
        TextView nine = findViewById(R.id.nine);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + nine.getText().toString());
    }
    public void clickedZero(View view){
        TextView zero = findViewById(R.id.zero);
        TextView result = findViewById(R.id.result);
        result.setText(result.getText().toString() + zero.getText().toString());
    }
}