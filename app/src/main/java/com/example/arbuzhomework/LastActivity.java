package com.example.arbuzhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LastActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        tv1 = findViewById(R.id.rowcol);
        tv2 = findViewById(R.id.amount);
        tv3 = findViewById(R.id.numbers);
        preferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        tv1.setText("Row and column: "+ preferences.getString("Row", "") + "/" + preferences.getString("Col", ""));
        tv2.setText("Total amount: " + preferences.getInt("Amount", 0));
        tv3.setText("Your phone number: " + preferences.getString("Phone", ""));

    }
}