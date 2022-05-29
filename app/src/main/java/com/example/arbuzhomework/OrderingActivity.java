package com.example.arbuzhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderingActivity extends AppCompatActivity {

    SharedPreferences orderpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);

        Button btnorder = findViewById(R.id.btn_finish);
        EditText etphone = findViewById(R.id.edittextphone);
        EditText etaddress = findViewById(R.id.edittext1);

        EditText etDate = findViewById(R.id.edittextdate);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        OrderingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + day;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etphone.getText().toString();
                String datestr = etDate.getText().toString();
                String[] arr = datestr.split("-"); // yyyy-MM-dd;
                int year = Integer.parseInt(arr[0]);
                int month = Integer.parseInt(arr[1]);
                int day = Integer.parseInt(arr[2]);


                if(etaddress.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(OrderingActivity.this, "Fill all forms!!!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if(!isValidPhone(s)){
                    Toast toast = Toast.makeText(OrderingActivity.this, "Invalid phone", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(!isValidDate(year, month, day)){
                    Toast toast = Toast.makeText(OrderingActivity.this, "Invalid date", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    orderpref = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);
                    SharedPreferences.Editor editor = orderpref.edit();
                    editor.putString("Phone",etphone.getText().toString());
                    editor.apply();

                    Toast toast = Toast.makeText(OrderingActivity.this, "Successful", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(OrderingActivity.this, LastActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    boolean isValidDate(int year, int month, int day){


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        try{
            cal.setTime(sdf.parse(sdf.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_MONTH, 9);
        String dateAfter = sdf.format(cal.getTime()); // 2022-06-06
        String currentDate = sdf.format(new Date()); // 2022-05-28
        String orderDate = year+"-"+month+"-"+day;  // 2022-05-30

        try {
            Date order = new SimpleDateFormat("yyyy-MM-dd").parse(orderDate);
            Date aft = new SimpleDateFormat("yyyy-MM-dd").parse(dateAfter);

            Date current = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
            if (order.compareTo(aft) > 0 || current.compareTo(order)>0) {
                return false;
            } else if (order.compareTo(aft) < 0) {
                return true;
            } else if (order.compareTo(aft) == 0) {
                return true;
            } else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean isValidPhone(String s){
        Pattern pattern  = Pattern.compile("^((\\+?77)([0-9]{9}))$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

}