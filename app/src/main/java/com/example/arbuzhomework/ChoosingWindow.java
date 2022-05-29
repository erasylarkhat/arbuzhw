package com.example.arbuzhomework;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ChoosingWindow extends Activity {

    SharedPreferences sharedPreferences;
    boolean choosed = false;
    boolean isCorrect = false;
    int amount = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.6));
        setContentView(R.layout.choosingwindow);



        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        Button btn_check = findViewById(R.id.btn_checker);
        EditText colet = findViewById(R.id.colet);
        EditText rowet = findViewById(R.id.rowet);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int column = Integer.parseInt(colet.getText().toString());
                int row = Integer.parseInt(rowet.getText().toString());

                if(column>5 || row > 5){
                    Toast toast = Toast.makeText(ChoosingWindow.this, "Out of range", Toast.LENGTH_SHORT);
                    isCorrect = false;
                    toast.show();
                } else if(column>3 && row>3){
                    Toast toast = Toast.makeText(ChoosingWindow.this, "Not ripe", Toast.LENGTH_SHORT);
                    isCorrect = false;
                    toast.show();
                } else if(column>2 && row>2){
                    Toast toast = Toast.makeText(ChoosingWindow.this, "Already ripped off", Toast.LENGTH_SHORT);
                    isCorrect = false;
                    toast.show();
                } else {
                    choosed = true;
                    Toast toast = Toast.makeText(ChoosingWindow.this, "Great, you have chosen", Toast.LENGTH_SHORT);
                    isCorrect = true;
                    toast.show();
                }

            }
        });

        TextView amounttv = findViewById(R.id.numtv);
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_delete = findViewById(R.id.btn_delete);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arr = amounttv.getText().toString().split(": ");
                int num = Integer.parseInt(arr[1]);

                if(num<3){
                    num++;
                    amounttv.setText("Amount number: " + num);
                }
                amount = num;
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arr = amounttv.getText().toString().split(": ");
                int num = Integer.parseInt(arr[1]);
                if(num>1){
                    num--;
                    amounttv.setText("Amount number: " + num);
                }
                amount = num;
            }
        });

        Button btn_accept = findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCorrect){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Amount",amount);
                    editor.putString("Row", rowet.getText().toString());
                    editor.putString("Col", colet.getText().toString());
                    editor.apply();
                    Intent intent = new Intent(ChoosingWindow.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}
