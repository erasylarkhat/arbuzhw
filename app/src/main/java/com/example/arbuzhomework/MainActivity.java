package com.example.arbuzhomework;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    static final String ACCESS_MESSAGE="ACCESS_MESSAGE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btn = findViewById(R.id.btn_choose_rowcol);

        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent intent = result.getData();
                            assert intent != null;
                            String accessMessage = intent.getStringExtra(ACCESS_MESSAGE);
                            System.out.println(accessMessage);
                        }
                        else{
                            System.out.println("Ошибка доступа");
                        }
                    }
                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartForResult.launch(new Intent(MainActivity.this, ChoosingWindow.class));
            }
        });


        Button btn_order = findViewById(R.id.btn_order);
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderingActivity.class);
                startActivity(intent);
            }
        });

    }
}