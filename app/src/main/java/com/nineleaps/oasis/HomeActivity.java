package com.nineleaps.oasis;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private EditText category;
    private EditText date;
    private ImageView ivCalender;
    private ImageView ivTimePicker;
    private CalendarView calenders;
    private TimePicker timePicker;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        category = findViewById(R.id.category);
        ivCalender = findViewById(R.id.iv_calender);
//        calenders = findViewById(R.id.calender);
//        calenders = new CalendarView(this);
//        timePicker = findViewById(R.id.iv_time_picker);

        date = findViewById(R.id.date);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner", "onItemSelected: " + parent.getItemAtPosition(position));
                category.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> categoryList = new ArrayList<String>();
        categoryList.add("E-Waste");
        categoryList.add("Plastic Waste");
        categoryList.add("Paper Waste");
        categoryList.add("Category");
        HintAdapter hintAdapter = new HintAdapter(this, android.R.layout.simple_list_item_1, categoryList);
        spin.setAdapter(hintAdapter);
        // show hint
        spin.setSelection(hintAdapter.getCount());


        ivCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CalendarView view = new CalendarView(HomeActivity.this);
//                setContentView(view);
                Log.e("Image", "onClick: ");
                calenders = new CalendarView(HomeActivity.this);
                calenders.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        calenders.getDate();
                        date.setText(year + "-" + month + "-" + dayOfMonth);
                        Log.e("date", "onSelectedDayChange: " + year + "-" + month + "-" + dayOfMonth);
                    }
                });
            }
        });


    }

}


