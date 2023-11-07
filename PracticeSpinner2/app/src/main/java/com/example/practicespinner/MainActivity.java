package com.example.practicespinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    String[] items = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spin2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        spin2.setAdapter(adapter);
    }

    public void onClicked(View view){
        Spinner spin = findViewById(R.id.spinner);
        MultiAutoCompleteTextView text2 = findViewById(R.id.text23);

        if (spin.getSelectedItem().toString().contains("1절")){
            text2.setText(R.string.song1);
        }else if (spin.getSelectedItem().toString().contains("2절")){
            text2.setText(R.string.song3);
        }else if (spin.getSelectedItem().toString().contains("후렴")){
            text2.setText(R.string.song2);
        }
    }
}